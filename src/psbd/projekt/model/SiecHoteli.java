/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import psbd.projekt.MainWindow;

/**
 *
 * @author Michal
 */
public class SiecHoteli {

    private ArrayList<Hotel> listaHoteli = new ArrayList<>();
    private static SiecHoteli instance = new SiecHoteli();
    private MainWindow parentFrame;
    private Map<Integer, String> mapStandard = new HashMap<Integer, String>();

    public static SiecHoteli getInstance() {
        return instance;
    }
    
    private SiecHoteli(){
        mapStandard.put(1, "Budżetowy");
        mapStandard.put(2, "Standard");
        mapStandard.put(3, "Lux");
        mapStandard.put(4, "Exclusive");
    }
    
    public String getStandard(int key){
        return mapStandard.get(key);
    }
    
    public void clear() {
        listaHoteli.clear();
    }
    
    public void setParent(MainWindow parentFrame) {
        this.parentFrame = parentFrame;
    }

    public void addPokoj(int id, int nrPokoju, float cena, short standard, short maxOsob, short liczbaPomieszczen, short liczbaGwiazdek, String nazwa, int idHotelu, int id_rezerwacji, String status) {
        int iloscHoteli = listaHoteli.size();

        for (int i = 0; i < iloscHoteli; ++i) {
            if (listaHoteli.get(i).getIdRezerwacji() == id_rezerwacji) {
                listaHoteli.get(i).addPokojDoHotelu(id, nrPokoju, cena, standard, maxOsob, liczbaPomieszczen);
                return;
            }
        }
        Hotel hotel = new Hotel(liczbaGwiazdek, nazwa, idHotelu, id_rezerwacji, status, parentFrame);
        hotel.addPokojDoHotelu(id, nrPokoju, cena, standard, maxOsob, liczbaPomieszczen);
        listaHoteli.add(hotel);
    }

    public Hotel[] getHotele() {
        int size = listaHoteli.size();
        Hotel[] hotele = new Hotel[size];

        for (int i = 0; i < size; ++i) {
            hotele[i] = listaHoteli.get(i);
        }

        return hotele;
    }

}
