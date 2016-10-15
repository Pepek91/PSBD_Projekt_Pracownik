/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psbd.projekt.classes;

/**
 *
 * @author Michal
 */
public class Pracownik {

    private int id = 0;
    private String imie = null;
    private String nazwisko = null;
    private String e_mail = null;
    private int uprawnienia;

    private static Pracownik instane = new Pracownik();

    Pracownik() {

    }
    


    public void setDane(int id, String e_mail, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.e_mail = e_mail;
    }

    public void setDaneFull(int id, String imie, String nazwisko, String e_mail, int uprawnienia ) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.e_mail = e_mail;
        this.uprawnienia = uprawnienia;
    }

    public void clearUser() {
        this.id = 0;
        this.imie = null;
        this.nazwisko = null;
        this.e_mail = null;
        uprawnienia =0;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public int getId() {
        return id;
    }

    public void setImieNazwisko(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public static Pracownik getInstance() {
        return instane;
    }

    public boolean isZalogowany() {
        if(id == 0){
            return false;
        }else{
            return true;
        }
    }

    public int getUpr() {
        return uprawnienia;
    }
}
