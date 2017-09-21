package com.example.tuxzo.miniprojekt1_indkoebsliste;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

/**
 * Created by tuxzo on 20-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    private static final String DB_NAME = "miniprojekt1"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static synchronized DatabaseHelper getInstance(Context context) {
          if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }



    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE BUTIK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADRESSE TEXT, "
                    + "HOMEPAGE TEXT;");

            //TODO: Skal mængde ikke være et antal man har tænkt sig at købe? eller skal det være et antal af dem der er
            db.execSQL("CREATE TABLE VARE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "NORMALPRIS REAL, "
                    + "MAENGDE INTEGER;");

            db.execSQL("CREATE TABLE INDKOEBSLISTE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADRESSE TEXT, "
                    + "HOMEPAGE TEXT;");
        }
        if (oldVersion < 2) {
            //TODO: skal vi bruge version til noget? som ekstra colum eller ligende :)
        }
    }
}
