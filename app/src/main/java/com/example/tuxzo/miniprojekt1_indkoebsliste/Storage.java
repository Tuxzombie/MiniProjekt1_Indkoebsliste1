package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by tuxzo on 21-09-2017.
 */

public class Storage {

    private static SQLiteDatabase db;
    private static Storage sInstance;

    private Storage(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
        createButikker();
        createVarer();
        insertVarerIIndkoebsliste();
    }

    public static synchronized Storage getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Storage(context.getApplicationContext());
        }
        Log.d("DB: ", sInstance.toString());
        return sInstance;
    }

    public void createButikker() {
        db.delete("BUTIK", null, null);

        addButik(new Butik("Rema 100", "Holme Byvej 15, 8270 Højbjerg", "http://www.rema1000.dk/"));
        addButik(new Butik("SuperBrugsen", "Kridthøjvej 3, 8270 Højbjerg", "http://superbrugsen.dk/"));
        addButik(new Butik("Fakta", "Ringvej Syd 84, 8260 Viby", "http://www.fakta.dk/"));
        addButik(new Butik("LIDL", "Christian X's Vej 112, 8260 Viby", "http://www.LIDL.dk/"));
    }

    public Cursor getButikker() {
        return db.query("BUTIK", null, null, null, null, null, null);
    }

    public Butik getButik(int _id) {
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

    public void addButik(Butik butik) {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.insert("BUTIK", null, butikValues);
    }

    public void removeButik(int _id) {
        db.delete("BUTIK", "_id =" + _id, null);

        Cursor cursor = getVarer(_id);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            removeVare(Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id"))));
            cursor.moveToNext();
        }
    }

    public void updateButik(int _id, Butik butik) {
        ContentValues butikValues = new ContentValues();
        butikValues.put("NAME", butik.getName());
        butikValues.put("ADRESSE", butik.getAdresse());
        butikValues.put("HOMEPAGE", butik.getHomepage());
        db.update("BUTIK", butikValues, "_id=?", new String[]{_id + ""});
    }

    //
    public void createVarer() {
        db.delete("VARE", null, null);

        Cursor cursor = getButikker();
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
    public Cursor getVarer(int butiksId)
    {
        return db.query("VARE",null,"BUTIK_ID = ?",new String[] {butiksId + ""},null,null,null);
    }

    public Vare getVare(int _id)
    {
        Cursor vareCursor = db.query("VARE",
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

    public void addVare(Vare vare) {
        ContentValues vareValues = new ContentValues();
        vareValues.put("NAME", vare.getName());
        vareValues.put("NORMALPRIS", vare.getNormalPris());
        vareValues.put("MAENGDE", vare.getMængde());
        //TODO: Her skal der drøftes hvordan butikID skal hentes
        vareValues.put("BUTIK_ID", vare.getButikId());
        db.insert("VARE", null, vareValues);
    }

    public void removeVare(int _id)
    {
        db.delete("VARE", "_id ="+_id, null);
    }
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
    public void addVareTilIndkoebsliste(int id, int antal, int isChecked) {
        ContentValues vareValue = new ContentValues();
        vareValue.put("VARE_ID", id);
        vareValue.put("ANTAL", antal);
        vareValue.put("ISCHECKED", isChecked);
        db.insert("INDKOEBSLISTE", null, vareValue);
    }

    public void insertVarerIIndkoebsliste() {
        db.delete("INDKOEBSLISTE",null,null);

        Cursor cursor = getButikker();
        cursor.moveToFirst();
        int tempButik = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));

        Cursor cursor2 = getVarer(tempButik);
        cursor2.moveToFirst();
        int tempVare = Integer.parseInt(cursor.getString(cursor2.getColumnIndex("_id")));

        addVareTilIndkoebsliste(tempVare, 2, 0);
        addVareTilIndkoebsliste(tempVare, 3, 1);
    }

    public Cursor getVarerIListe() {
        return db.query("INDKOEBSLISTE",null,null,null,null,null,null);
    }

    public void removeVareFraIndkoebsliste(int id) {

        public static void setIsCheckedOfIndkoebsliste(int id, boolean isChecked) {
        int newIsChecked = isChecked ? 0 : 1;

            ContentValues butikValues = new ContentValues();
            butikValues.put("ISCHECKED", newIsChecked);
            db.update("INDKOEBSLISTE", butikValues, "_id=?", new String[]{id + ""});

    }

    public static void removeVareFraIndkoebsliste(int id) {
        db.delete("INDKOEBSLISTE", "VARE_ID =" + id, null);
    }

    public void destroy()
    {
        db.close();
    }
}

