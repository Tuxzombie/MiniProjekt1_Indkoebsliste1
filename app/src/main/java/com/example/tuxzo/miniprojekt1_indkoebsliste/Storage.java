package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by tuxzo on 21-09-2017.
 */

public class Storage {

    private static  ArrayList<Butik> butikker = new ArrayList<>();

    public static ArrayList<Butik> createButikker()
    {


        butikker.add(new Butik("Rema 100", "Holme Byvej 15, 8270 Højbjerg", "http://www.rema1000.dk/"));
        butikker.add(new Butik("SuperBrugsen", "Kridthøjvej 3, 8270 Højbjerg", "http://superbrugsen.dk/"));
        butikker.add(new Butik("Fakta", "Ringvej Syd 84, 8260 Viby", "http://www.fakta.dk/"));
        butikker.add(new Butik("LIDL", "Christian X's Vej 112, 8260 Viby", "http://www.LIDL.dk/"));

        return butikker;
    }

    public static Butik getButik(int p)
    {
        return butikker.get(p);
    }
}
