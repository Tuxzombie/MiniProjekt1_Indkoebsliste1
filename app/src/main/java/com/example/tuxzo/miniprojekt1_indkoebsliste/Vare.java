package com.example.tuxzo.miniprojekt1_indkoebsliste;

/**
 * Created by PWM on 21-09-17.
 */

public class Vare {
    private String name;
    private double normalPris;
    private int mængde;
    private int butikId;

    // ================================================================================
    // CONSTRUCTOR:
    // ================================================================================

    public Vare(String name, double normalPris, int mængde, int butikId) {
        this.name = name;
        this.normalPris = normalPris;
        this.mængde = mængde;
        this.butikId = butikId;
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

    public int getButikId() {
        return butikId;
    }

    public void setButikId(int butikId) {
        this.butikId = butikId;
    }
}
