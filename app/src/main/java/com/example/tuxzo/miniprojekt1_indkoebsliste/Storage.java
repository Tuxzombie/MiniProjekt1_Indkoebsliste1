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

    public Storage(Context context) {
        db = DatabaseHelper.getInstance(context).getWritableDatabase();
    }

    public static void createButikker() {
        db.delete("BUTIK", null, null);

        addButik(new Butik("Rema 100", "Holme Byvej 15, 8270 Højbjerg", "http://www.rema1000.dk/"));
        addButik(new Butik("SuperBrugsen", "Kridthøjvej 3, 8270 Højbjerg", "http://superbrugsen.dk/"));
        addButik(new Butik("Fakta", "Ringvej Syd 84, 8260 Viby", "http://www.fakta.dk/"));
        addButik(new Butik("LIDL", "Christian X's Vej 112, 8260 Viby", "http://www.LIDL.dk/"));
    }

    public static Cursor getButikker() {
        return db.query("BUTIK", null, null, null, null, null, null);
    }

    public static Butik getButik(int _id) {
        Cursor butikCursor = db.query("BUTIK",
                new String[]{"NAME", "ADRESSE", "HOMEPAGE"},
                "_id=?",
                new String[]{"" + _id},
                null, null, null);
        butikCursor.moveToFirst();
        Butik butik = new Butik(butikCursor.getString(butikCursor.getColumnIndex("NAME")),
                butikCursor.getString(butikCursor.getColumnIndex("ADRESSE")),
                butikCursor.getString(butikCursor.getColumnIndex("HOMEPAGE")));
        return butik;
    }

    public static void addButik(Butik butik) {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.insert("BUTIK", null, butikValues);
    }

    public static void removeButik(int _id) {
        db.delete("BUTIK", "_id =" + _id, null);
    }

    public static void updateButik(int _id, Butik butik) {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.update("BUTIK", butikValues, "_id=?", new String[]{_id + ""});
    }

    //
    public static void createVarer() {
        db.delete("VARE", null, null);
        Cursor cursor = MainActivity.storage.getButikker();
        cursor.moveToFirst();

        int temp = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));

//        //TODO: Kender ik standard construtor så her skal der nok rettes
        addVare(new Vare("Mini Baby Bel", 26.50, 15, temp));
        addVare(new Vare("Agurk", 6.00, 200, temp));
        addVare(new Vare("Kløver Vaseline", 22.95, 200, temp));
        addVare(new Vare("Dansk broccoli", 12.00, 60, temp));
        addVare(new Vare("Rød Spidskål", 18.00, 80, temp));
        addVare(new Vare("Økologiske æg", 27.95, 24, temp));

        cursor.moveToNext();
        temp = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
        addVare(new Vare("Hakket Oksekød", 30.00, 80, temp));
        addVare(new Vare("Lille Grillkasse", 149.00, 45, temp));
        addVare(new Vare("Mustang Marie - Basis hverdagscykel", 1499.00, 15, temp));
        addVare(new Vare("Væksthus", 799.00, 95, temp));
        addVare(new Vare("Cirkeline som slaskedukke", 129.95, 46, temp));

        cursor.moveToNext();
        temp = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
        addVare(new Vare("Singer symaskine - Simple 3223 - Hvid og lilla", 999.00, 5, temp));
        addVare(new Vare("LEGO Star Wars Darth Vader", 249.95, 120, temp));
        addVare(new Vare("LEGO Friends Stephanies hus", 486.00, 45,temp));
        addVare(new Vare("LEGO Elves dragedronningens redning", 649.00, 20,temp));
    }
//
    public static Cursor getVarer(int butiksId)
    {
        return db.query("VARE",null,"BUTIK_ID = ?",new String[] {butiksId + ""},null,null,null);
    }
//
    public static Vare getVare(int _id)
    {
        Cursor vareCursor = db.query("BUTIK",
                new String[]{"NAME", "NORMALPRIS", "MAENGDE", "BUTIK_ID"},
                "_id=?",
                new String[]{""+_id},
                null,null,null);
        vareCursor.moveToFirst();
        Vare vare = new Vare(vareCursor.getString(vareCursor.getColumnIndex("NAME")),
                vareCursor.getDouble(vareCursor.getColumnIndex("NORMALPRIS")),
                vareCursor.getInt(vareCursor.getColumnIndex("MAENGDE")), _id);
       return vare;
    }
//

    public static void addVare(Vare vare) {
        ContentValues vareValues = new ContentValues();
        vareValues.put("NAME", vare.getName());
        vareValues.put("NORMALPRIS", vare.getNormalPris());
        vareValues.put("MAENGDE", vare.getMængde());
        //TODO: Her skal der drøftes hvordan butikID skal hentes
        vareValues.put("BUTIK_ID", vare.getButikId());
        db.insert("VARE", null, vareValues);
    }
//
//    public static void removeVare(int _id)
//    {
//        db.delete("VARE", "_id ="+_id, null);
//    }
//
//    public static void updateVare(int _id, Vare vare)
//    {
//        ContentValues vareValues = new ContentValues();
//        vareValues.put("NAME", vare.getName());
//        vareValues.put("NORMALPRIS", vare.getNormalpris());
//        vareValues.put("MAENGDE", vare.getMængde());
//        db.update("BUTIK", vareValues, "_id=?", new String[]{_id+""});
//    }
//
//    public static void createLister()
//    {
//        db.delete("INDKOEBSLISTE",null,null);
//
////        addListe(new IndkoebsListe());
//    }
//
//    public static Cursor getLister()
//    {
//        return db.query("INDKOEBSLISTE",null,null,null,null,null,null);
//    }
//
//    public static IndkoebsListe getListe(int _id)
//    {
//        IndkoebsListe liste = null;
//        return liste;
//    }
//
//    public static void addListe(Liste liste)
//    {
//
//    }
//
//    public static void removeListe(int _id)
//    {
//        db.delete("INDKOEBSLISTE", "_id ="+_id, null);
//    }
//
//    public static void updateListe(int _id, IndkoebsListe liste)
//    {
//
//    }
}

