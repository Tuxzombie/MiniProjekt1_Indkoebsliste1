package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tuxzo on 21-09-2017.
 */

public class Storage {

    private static SQLiteDatabase db;

    public Storage(Context context)
    {
        db = DatabaseHelper.getInstance(context).getWritableDatabase();
    }

    public static void createButikker()
    {
        db.delete("BUTIK",null,null);

        addButik(new Butik("Rema 100", "Holme Byvej 15, 8270 Højbjerg", "http://www.rema1000.dk/"));
        addButik(new Butik("SuperBrugsen", "Kridthøjvej 3, 8270 Højbjerg", "http://superbrugsen.dk/"));
        addButik(new Butik("Fakta", "Ringvej Syd 84, 8260 Viby", "http://www.fakta.dk/"));
        addButik(new Butik("LIDL", "Christian X's Vej 112, 8260 Viby", "http://www.LIDL.dk/"));
    }

    public static Cursor getButikker()
    {
        return db.query("BUTIK",null,null,null,null,null,null);
    }

    public static Butik getButik(int _id)
    {
        Cursor butikCursor = db.query("BUTIK",
                                    new String[]{"NAME", "ADRESSE", "HOMEPAGE"},
                                    "_id=?",
                                    new String[]{""+_id},
                                    null,null,null);
        butikCursor.moveToFirst();
        Butik butik = new Butik(butikCursor.getString(butikCursor.getColumnIndex("NAME")),
                                butikCursor.getString(butikCursor.getColumnIndex("ADRESSE")),
                                butikCursor.getString(butikCursor.getColumnIndex("HOMEPAGE")));
        return butik;
    }

    public static void addButik(Butik butik)
    {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.insert("BUTIK", null, butikValues);
    }

    public static void removeButik(int _id)
    {
        db.delete("BUTIK", "_id ="+_id, null);
    }

    public static void updateButik(int _id, Butik butik)
    {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.update("BUTIK", butikValues, "_id=?", new String[]{_id+""});
    }

    public static void createVarer()
    {
        db.delete("BUTIK",null,null);

        addButik(new Butik("Rema 100", "Holme Byvej 15, 8270 Højbjerg", "http://www.rema1000.dk/"));
        addButik(new Butik("SuperBrugsen", "Kridthøjvej 3, 8270 Højbjerg", "http://superbrugsen.dk/"));
        addButik(new Butik("Fakta", "Ringvej Syd 84, 8260 Viby", "http://www.fakta.dk/"));
        addButik(new Butik("LIDL", "Christian X's Vej 112, 8260 Viby", "http://www.LIDL.dk/"));
    }

    public static Cursor getVarer()
    {
        return db.query("BUTIK",null,null,null,null,null,null);
    }

    public static Butik getVare(int _id)
    {
        Cursor butikCursor = db.query("BUTIK",
                new String[]{"NAME", "ADRESSE", "HOMEPAGE"},
                "_id=?",
                new String[]{""+_id},
                null,null,null);
        butikCursor.moveToFirst();
        Butik butik = new Butik(butikCursor.getString(butikCursor.getColumnIndex("NAME")),
                butikCursor.getString(butikCursor.getColumnIndex("ADRESSE")),
                butikCursor.getString(butikCursor.getColumnIndex("HOMEPAGE")));
        return butik;
    }

    public static void addVare(Butik butik)
    {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.insert("BUTIK", null, butikValues);
    }

    public static void removeVare(int _id)
    {
        db.delete("BUTIK", "_id ="+_id, null);
    }

    public static void updateVare(int _id, Butik butik)
    {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.update("BUTIK", butikValues, "_id=?", new String[]{_id+""});
    }

    public static void createLister()
    {
        db.delete("BUTIK",null,null);

        addButik(new Butik("Rema 100", "Holme Byvej 15, 8270 Højbjerg", "http://www.rema1000.dk/"));
        addButik(new Butik("SuperBrugsen", "Kridthøjvej 3, 8270 Højbjerg", "http://superbrugsen.dk/"));
        addButik(new Butik("Fakta", "Ringvej Syd 84, 8260 Viby", "http://www.fakta.dk/"));
        addButik(new Butik("LIDL", "Christian X's Vej 112, 8260 Viby", "http://www.LIDL.dk/"));
    }

    public static Cursor getLister()
    {
        return db.query("BUTIK",null,null,null,null,null,null);
    }

    public static Butik getListe(int _id)
    {
        Cursor butikCursor = db.query("BUTIK",
                new String[]{"NAME", "ADRESSE", "HOMEPAGE"},
                "_id=?",
                new String[]{""+_id},
                null,null,null);
        butikCursor.moveToFirst();
        Butik butik = new Butik(butikCursor.getString(butikCursor.getColumnIndex("NAME")),
                butikCursor.getString(butikCursor.getColumnIndex("ADRESSE")),
                butikCursor.getString(butikCursor.getColumnIndex("HOMEPAGE")));
        return butik;
    }

    public static void addListe(Butik butik)
    {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.insert("BUTIK", null, butikValues);
    }

    public static void removeListe(int _id)
    {
        db.delete("BUTIK", "_id ="+_id, null);
    }

    public static void updateListe(int _id, Butik butik)
    {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.update("BUTIK", butikValues, "_id=?", new String[]{_id+""});
    }
}
