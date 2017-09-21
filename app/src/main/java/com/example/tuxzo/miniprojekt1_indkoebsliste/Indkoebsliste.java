package com.example.tuxzo.miniprojekt1_indkoebsliste;

import java.util.ArrayList;

/**
 * Created by eaajejen54 on 21-09-2017.
 */

public class Indkoebsliste {
    private ArrayList<String> vareListe; // TODO skal ændres til Vare
    private String butik; // TODO skal ændres til butik

    public Indkoebsliste(String butik) {
        this.butik = butik;
        // vareListe = new ArrayList<Vare>();
    }

    public String getButik() {
        return butik;
    }

    public void setButik(String butik) {
        this.butik = butik;
    }

    public ArrayList<String> getVareliste() {
        return vareListe;
    }

    public void addVareToVareliste(String v) {
        vareListe.add(v);
    }

}
