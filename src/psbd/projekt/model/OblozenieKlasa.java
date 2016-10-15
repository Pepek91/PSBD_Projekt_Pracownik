/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt.model;

import java.util.ArrayList;
import psbd.projekt.panes.HotelObl;

/**
 *
 * @author Michal
 */
public class OblozenieKlasa {

    private static OblozenieKlasa instance = new OblozenieKlasa();
    private ArrayList<HotelObl> hotele = new ArrayList<>();

    public static OblozenieKlasa getInstance() {
        return instance;
    }

    public void addTyp(int id_hotelu, String nazwa, int liczba_gwiazdek, int standard, int liczba_osob, int ile_rezerwacji) {
        int sizeH = hotele.size();
        for (int i = 0; i < sizeH; ++i) {
            if (hotele.get(i).getIdHotelu() == id_hotelu) {
                hotele.get(i).addPokoj(standard, liczba_osob, ile_rezerwacji);
                return;
            }
        }
        HotelObl h = new HotelObl(id_hotelu, nazwa, liczba_osob, null);
        h.addPokoj(standard, liczba_osob, ile_rezerwacji);
        hotele.add(h);
    }

    public void clearAll() {
        hotele.clear();
    }

    public ArrayList<HotelObl> getHotele() {
        return hotele;
    }

}
