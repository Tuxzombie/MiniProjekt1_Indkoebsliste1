package com.example.tuxzo.miniprojekt1_indkoebsliste;

/**
 * Created by PWM on 21-09-17.
 */

public class Vare {
    private String name;
    private double normalPris;
    private int mængde;

    public static final Vare[] varer = {
            new Vare("Leverpostysch", 14.95, 5),
            new Vare("Makreal", 14.95, 5),
            new Vare("Gammel Ole", 14.95, 5)
    };

    // ================================================================================
    // CONSTRUCTOR:
    // ================================================================================

    public Vare(String name, double normalPris, int mængde) {
        this.name = name;
        this.normalPris = normalPris;
        this.mængde = mængde;
    }

    // ================================================================================
    // GETTERS AND SETTERS
    // ================================================================================

    public double getNormalPris() {
        return normalPris;
    }

    public void setNormalPris(double normalPris) {
        this.normalPris = normalPris;
    }

    public int getMængde() {
        return mængde;
    }

    public void setMængde(int mængde) {
        this.mængde = mængde;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
