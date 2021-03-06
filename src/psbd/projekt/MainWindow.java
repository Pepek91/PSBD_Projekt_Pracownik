/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import psbd.projekt.classes.Pracownik;
import psbd.projekt.dialogs.Konto;
import psbd.projekt.dialogs.LoginDialog;
import psbd.projekt.model.RaportyDialog;
import psbd.projekt.model.SiecHoteli;
import psbd.projekt.panes.ZmienStatus;
import psbd.projekt.utils.MySql;
import psbd.projekt.utils.Utils;

/**
 *
 * @author Michal
 */
public class MainWindow extends javax.swing.JFrame implements WindowListener {

    ZmienStatus zmien = new ZmienStatus();

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        jPanel1.setLayout(new GridLayout(1, 1));
        jPanel1.add(zmien);
        SiecHoteli.getInstance().setParent(this);
        btnRaporty.hide();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblZalogowanyUzytkownik = new javax.swing.JLabel();
        btnZaloguj = new javax.swing.JButton();
        btnWyloguj = new javax.swing.JButton();
        btnEdytuj = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnRaporty = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblZalogowanyUzytkownik.setText("Zalogowany użytkownik:");

        btnZaloguj.setText("Zaloguj");
        btnZaloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZalogujActionPerformed(evt);
            }
        });

        btnWyloguj.setText("Wyloguj");
        btnWyloguj.setEnabled(false);
        btnWyloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWylogujActionPerformed(evt);
            }
        });

        btnEdytuj.setText("Konto");
        btnEdytuj.setEnabled(false);
        btnEdytuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdytujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        btnRaporty.setText("Raporty");
        btnRaporty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaportyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblZalogowanyUzytkownik, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(btnZaloguj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnWyloguj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdytuj)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRaporty)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblZalogowanyUzytkownik)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnZaloguj)
                        .addComponent(btnWyloguj)
                        .addComponent(btnEdytuj)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRaporty))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZalogujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZalogujActionPerformed
        LoginDialog loginDlg = new LoginDialog(this, true);
        loginDlg.setVisible(true);

        if (loginDlg.isSucceeded()) {
            zaloguj();
        }
    }//GEN-LAST:event_btnZalogujActionPerformed

    private void btnWylogujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWylogujActionPerformed
        wyloguj();
    }//GEN-LAST:event_btnWylogujActionPerformed

    private void btnEdytujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdytujActionPerformed

        
        Konto KontoDlg = new Konto(this, true);
        KontoDlg.setVisible(true);
//        int msg = edytujKontoDlg.getReturnCode();
//        switch (msg) {
//            case 1:
//                Pracownik klient = Pracownik.getInstance();
//                String imie = klient.getImie();
//                String nazwisko = klient.getNazwisko();
//                lblZalogowanyUzytkownik.setText("Zalogowany użytkownik: " + klient.getNazwisko() + " " + klient.getImie());
//                break;
//            case 2:
//                wyloguj();
//                break;
//        }

    }//GEN-LAST:event_btnEdytujActionPerformed

    private void btnRaportyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaportyActionPerformed
        RaportyDialog raporty = new RaportyDialog(this, true);
        raporty.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnRaportyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Utils.initMap();
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

//    private void drawHotele() {
//        Hotel[] hotele = SiecHoteli.getInstance().getHotele();
//        int size = hotele.length;
//        p.setLayout(new GridLayout(size, 1));
//        for (int i = 0; i < size; ++i) {
//            hotele[i].drawPokoje();
//            p.add(hotele[i]);
//        }
//        jScrollPane1.setViewportView(p);
//    }

    private void zaloguj() {
        btnWyloguj.setEnabled(true);
        btnEdytuj.setEnabled(true);
        btnZaloguj.setEnabled(false);
        Pracownik pracownik = Pracownik.getInstance();
        lblZalogowanyUzytkownik.setText("Zalogowany użytkownik: " + pracownik.getNazwisko() + " " + pracownik.getImie());
        if(pracownik.getUpr() > 1){
            btnRaporty.show();
        }
    }

    private void wyloguj() {
        btnWyloguj.setEnabled(false);
        btnEdytuj.setEnabled(false);
        btnZaloguj.setEnabled(true);
        Pracownik.getInstance().clearUser();
        lblZalogowanyUzytkownik.setText("Zalogowany użytkownik: -");
        btnRaporty.hide();
        zmien.clear();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdytuj;
    private javax.swing.JButton btnRaporty;
    private javax.swing.JButton btnWyloguj;
    private javax.swing.JButton btnZaloguj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblZalogowanyUzytkownik;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        MySql.getInstance().closeConnection();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //michal ppk init wyszukaj
//    private void initWyszukiwarke() {
//        List<String> dzien = new ArrayList<String>();
//        for (int i = 1; i < 32; i++) {
//            dzien.add("" + i);
//        }
//        List<String> miesiac = new ArrayList<String>();
//        for (int i = 1; i < 13; i++) {
//            miesiac.add("" + i);
//        }
//        int year = Calendar.getInstance().get(Calendar.YEAR);
//
//        List<String> rok = new ArrayList<String>();
//        for (int i = 0; i < 121; i++) {
//            int rok2 = year - i;
//            rok.add("" + rok2);
//        }
//
//        cbDzienDo.setModel(new DefaultComboBoxModel(dzien.toArray()));
//        cbDzienOd.setModel(new DefaultComboBoxModel(dzien.toArray()));
//
//        cbMiesiacDo.setModel(new DefaultComboBoxModel(miesiac.toArray()));
//        cbMiesiacOd.setModel(new DefaultComboBoxModel(miesiac.toArray()));
//
//        cbRokDo.setModel(new DefaultComboBoxModel(rok.toArray()));
//        cbRokOd.setModel(new DefaultComboBoxModel(rok.toArray()));
//    }
}
