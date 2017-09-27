package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tuxzo on 21-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

//    private static DatabaseHelper sInstance;

    private static final String DB_NAME = "miniprojekt1"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

//    public static synchronized DatabaseHelper getInstance(Context context) {
//          if (sInstance == null) {
//            sInstance = new DatabaseHelper(context.getApplicationContext());
//        }
//        Log.d("DB: ", sInstance.toString());
//        return sInstance;
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("CREATE TABLE BUTIK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADRESSE TEXT, "
                    + "HOMEPAGE TEXT);");

            db.execSQL("CREATE TABLE VARE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "NORMALPRIS REAL, "
                    + "MAENGDE INTEGER, "
                    + "BUTIK_ID INTEGER);");

            db.execSQL("CREATE TABLE INDKOEBSLISTE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "VARE_ID INTEGER, "
                    + "ANTAL INTEGER, "
                    + "ISCHECKED INTEGER);");

    }
}
