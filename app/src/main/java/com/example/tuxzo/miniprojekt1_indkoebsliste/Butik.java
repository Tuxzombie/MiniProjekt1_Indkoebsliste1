package com.example.tuxzo.miniprojekt1_indkoebsliste;

import java.io.Serializable;

/**
 * Created by tuxzo on 21-09-2017.
 */

public class Butik implements Serializable {

    private String name;
    private String adresse;
    private String homepage;

    //TODO: en butik kan have mange varer, opret sammenhæng

    public Butik(String name, String adresse, String homepage) {
        this.name = name;
        this.adresse = adresse;
        this.homepage = homepage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public String toString() {
        return name + '\n' +
                adresse + '\n' +
                homepage;
    }
}
