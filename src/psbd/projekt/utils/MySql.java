/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import psbd.projekt.classes.Pracownik;
import psbd.projekt.model.OblozenieKlasa;
import psbd.projekt.model.SiecHoteli;

/**
 *
 * @author Michal
 */
public class MySql {

    private static MySql instane = new MySql();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs = null;

    MySql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String user = "root";
            String password = "projekt";
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost/psbd?useUnicode=true&characterEncoding=utf-8", user, password);
            System.out.println("MySQL connection OK");;
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection();
            JOptionPane.showMessageDialog(null, "Brak połączenia z bazą danych! Program zostanie zamknięty!");
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return con;
    }

    // You need to close the resultSet
    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
        System.out.println("MySQL connection closed!");
    }

    public void wyszukajRezerwacje(int numerRezerwacji) {
        SiecHoteli siec = SiecHoteli.getInstance();
        StringBuilder sb = new StringBuilder("SELECT DISTINCT p.*, h.*, r.*, pr.* ");
        sb.append("FROM rezerwacje AS r ");
        sb.append("INNER JOIN pokoje_rezerwacje AS pr ON pr.id_rezerwacji = r.id_rezerwacji ");
        sb.append("INNER JOIN pokoje AS p ON p.id_pokoju = pr.id_pokoju ");
        sb.append("INNER JOIN hotele AS h ON p.id_hotelu = h.id_hotelu ");
        if (numerRezerwacji != 0) {
            sb.append("WHERE id_rezewacji =? ");
        }

//        sb.append("AND p.id_pokoju NOT IN ");
//        sb.append("(SELECT pr2.id_pokoju FROM pokoje_rezerwacje AS pr2 \n" +
//"INNER JOIN rezerwacje AS r2 ON r2.id_rezerwacji = pr2.id_rezerwacji\n" +
//"WHERE \n" +
//"((r2.data_rozpoczecia >= ? AND r2.data_rozpoczecia<?) OR (r2.data_zakonczenia >? AND r2.data_zakonczenia<=?) OR (r2.data_zakonczenia >? AND r2.data_zakonczenia<?))"
//                + " AND status !='nieaktywna')");
        try {
            ps = con.prepareStatement(sb.toString());
            if (numerRezerwacji != 0) {
                ps.setInt(1, numerRezerwacji);
            }
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                siec.addPokoj(rs.getInt("id_pokoju"), rs.getInt("numer_pokoju"), rs.getInt("p.cena"),
                        rs.getShort("standard"), rs.getShort("liczba_osob"), rs.getShort("liczba_pomieszczen"),
                        rs.getShort("liczba_gwiazdek"), rs.getString("nazwa"), rs.getInt("id_hotelu"),
                        rs.getInt("id_rezerwacji"), rs.getString("status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static MySql getInstance() {
        return instane;
    }

    private void dodajPokojDoRezeracji(Object[] object, int id) {
        try {
            ps = con
                    .prepareStatement("INSERT INTO pokoje_rezerwacje VALUES (?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setInt(2, (int) object[0]);
            ps.setFloat(3, (float) object[2]);
            ps.setInt(4, (int) object[1]);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<ArrayList<Object>> getNiezrealizowane() {
        ArrayList<ArrayList<Object>> rezerwacje = new ArrayList<ArrayList<Object>>();

        StringBuilder sb = new StringBuilder("SELECT DISTINCT p.*, h.*, r.*, pr.*");
        sb.append("FROM pokoje AS p ");
        sb.append("INNER JOIN hotele AS h ON p.id_hotelu = h.id_hotelu ");
        sb.append("INNER JOIN pokoje_rezerwacje AS pr ON p.id_pokoju = pr.id_pokoju ");
        sb.append("INNER JOIN rezerwacje AS r ON r.id_rezerwacji = pr.id_rezerwacji ");
        sb.append("WHERE r.id_klienta =? AND status ='aktywna'");
        int index = 0;
        try {
            ps = con.prepareStatement(sb.toString());
            ps.setInt(++index, Pracownik.getInstance().getId());
            System.out.println(ps);//michal ppk
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<Object> pokoj = new ArrayList<Object>();
                pokoj.add(rs.getInt("id_rezerwacji"));
                pokoj.add(rs.getString("nazwa"));
                pokoj.add(rs.getInt("standard"));
                pokoj.add(rs.getInt("liczba_gwiazdek"));
                pokoj.add(rs.getString("id_hotelu"));
                rezerwacje.add(pokoj);
            }
        } catch (SQLException ex) {
        }
        return rezerwacje;
    }

    public void zmienStatus(String status, int id_rezerwacji) {
        try {
            ps = con
                    .prepareStatement("UPDATE rezerwacje SET status = ? WHERE id_rezerwacji =?");
            ps.setString(1, status);
            ps.setInt(2, id_rezerwacji);
            int ile = ps.executeUpdate();
            if (ile > 0) {
                JOptionPane.showMessageDialog(null, "Udało się poprawnie zmienić status!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> pobierzHotele() {
        ArrayList<String> hotele = new ArrayList<>();
        try {
            ps = con
                    .prepareStatement("SELECT nazwa FROM hotele");
            rs = ps.executeQuery();
            while (rs.next()) {
                hotele.add(rs.getString("nazwa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotele;
    }

    public void raportOblozenie(Date dataOd, Date dataDo, ArrayList<String> hotele, int standard, int goscie, String sort) {
        OblozenieKlasa oblozenie = OblozenieKlasa.getInstance();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        StringBuilder sbH = new StringBuilder("(");

        int sizeHotele = hotele.size();
        for (int i = 0; i < sizeHotele; ++i) {
            sbH.append("?,");
        }
        sbH.replace(sbH.length() - 1, sbH.length(), ")");

        sb2.append("SELECT COUNT(DISTINCT r.id_rezerwacji) ");
        sb2.append("FROM pokoje AS p1 ");
        sb2.append("LEFT JOIN pokoje_rezerwacje AS pr ON pr.id_pokoju = p1.id_pokoju ");
        sb2.append("LEFT JOIN rezerwacje AS r ON r.id_rezerwacji = pr.id_rezerwacji ");
        sb2.append("WHERE p1.id_pokoju = p.id_pokoju ");
        sb2.append("AND \n"
                + "((r.data_rozpoczecia >= ? AND r.data_rozpoczecia<?) OR (r.data_zakonczenia >? AND r.data_zakonczenia<=?) OR (r.data_zakonczenia >? AND r.data_zakonczenia<?))"
                + " AND status !='nieaktywna' ");

        sb2.append("GROUP BY p1.standard, p1.liczba_osob, p1.id_hotelu ");

        sb.append("SELECT DISTINCT p.standard, p.liczba_osob, h.id_hotelu, h.nazwa, h.liczba_gwiazdek, (" + sb2.toString() + ") AS ile_rezerwacji ");
        sb.append("FROM pokoje AS p ");
        sb.append("INNER JOIN hotele AS h ON p.id_hotelu = h.id_hotelu ");
        sb.append("WHERE h.nazwa IN " + sbH.toString() + " ");

        if (goscie != 0) {
            sb.append("AND p.liczba_osob =? ");
        }
        if (standard != 0) {
            sb.append("AND p.standard =? ");
        }

        if (sort != null) {
            sb.append("ORDER BY " + sort);
        }

        int index = 0;
        try {
            ps = con.prepareStatement(sb.toString());
            ps.setDate(++index, dataOd);
            ps.setDate(++index, dataDo);
            ps.setDate(++index, dataOd);
            ps.setDate(++index, dataDo);
            ps.setDate(++index, dataDo);
            ps.setDate(++index, dataOd);

            for (int i = 0; i < sizeHotele; ++i) {
                ps.setString(++index, hotele.get(i));
            }

            if (goscie != 0) {
                ps.setInt(++index, goscie);
            }
            if (standard != 0) {
                ps.setInt(++index, standard);
            }

            System.out.println(ps);
            rs = ps.executeQuery();
            while(rs.next()){
//                p.standard, p.liczba_osob, h.id_hotelu, h.nazwa, h.liczba_gwiazdek, (" + sb2.toString() + ") AS ile_rezerwacji
                oblozenie.addTyp(rs.getInt("id_hotelu"), rs.getString("nazwa"), rs.getInt("liczba_gwiazdek"), rs.getInt("standard"), rs.getInt("liczba_osob"), rs.getInt("ile_rezerwacji"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
