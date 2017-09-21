package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by tuxzo on 21-09-2017.
 */

public class Storage {

    private static  ArrayList<Butik> butikker = new ArrayList<>();
    private static SQLiteDatabase db;

    public Storage(Context context)
    {
        db = DatabaseHelper.getInstance(context).getReadableDatabase();
    }

    public static ArrayList<Butik> createButikker()
    {

        addButik(new Butik("Rema 100", "Holme Byvej 15, 8270 Højbjerg", "http://www.rema1000.dk/"));
        addButik(new Butik("SuperBrugsen", "Kridthøjvej 3, 8270 Højbjerg", "http://superbrugsen.dk/"));
        addButik(new Butik("Fakta", "Ringvej Syd 84, 8260 Viby", "http://www.fakta.dk/"));
        addButik(new Butik("LIDL", "Christian X's Vej 112, 8260 Viby", "http://www.LIDL.dk/"));

        return butikker;
    }

    public static Butik getButik(int p)
    {
        return butikker.get(p);
    }

    public static void addButik(Butik butik) {
        ContentValues personValues = new ContentValues();
        personValues.put("NAME", butik.getName());
        personValues.put("ADRESSE", butik.getAdresse());
        personValues.put("HOMEPAGE", butik.getHomepage());
        db.insert("BUTIK", null, personValues);
    }
}
