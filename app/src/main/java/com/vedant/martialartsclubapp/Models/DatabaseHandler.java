package com.vedant.martialartsclubapp.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MartialArtDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String MARTIAL_ART_TABLE = "MartialArts";
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String PRICE_KEY = "price";
    public static final String COLOR_KEY = "color";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // doubtful
    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {

        String createDatabaseSQL = "create table " + MARTIAL_ART_TABLE +
                "( " + ID_KEY + " integer primary key autoincrement" + ", " +
                NAME_KEY + " text" + ", " + PRICE_KEY + " real" + ", " + COLOR_KEY
                + " text" + " )";

        db.execSQL(createDatabaseSQL);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MARTIAL_ART_TABLE);
        onCreate(db);
    }

    public void addMartialArt(@NonNull MartialArt art) {
        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtSQLCommand = "insert into " + MARTIAL_ART_TABLE
                + " values(null, '" + art.getMartialArtName() +
                "', '" + art.getMartialArtPrice() + "', '" + art.getMartialArtColor()
                + "')";
        database.execSQL(addMartialArtSQLCommand);
        database.close();
    }


    public void deleteMartialObjectFromDatabaseByID(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtSQLCommand = "delete from " + MARTIAL_ART_TABLE +
                " where " + ID_KEY + " = " + id;
        database.execSQL(deleteMartialArtSQLCommand);
        database.close();

    }

    public void modifyMartialArtObject(int martialArtId, String martialArtName,
                                       double martialArtPrice, String martialArtColor) {
        SQLiteDatabase database = getWritableDatabase();
        String modifyMartialArtSQLCommand = "update " + MARTIAL_ART_TABLE +
                " set " + NAME_KEY + " = '" + martialArtName + "', " + PRICE_KEY
                + " = '" + martialArtPrice + "', " + COLOR_KEY + " = '" + martialArtColor
                + "' " + "where " + ID_KEY + " = " + martialArtId;

        database.execSQL(modifyMartialArtSQLCommand);
        database.close();
    }

    public ArrayList<MartialArt> returnMartialArtObjects() {
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " + MARTIAL_ART_TABLE;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);
        ArrayList<MartialArt> martialArts = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                MartialArt currentArt = new MartialArt(
                        Integer.parseInt(cursor.getString(0)
                        ), cursor.getString(1),
                        cursor.getDouble(2), cursor.getString(3));

                martialArts.add(currentArt);

            }
        } finally {
            cursor.close();
        }
        database.close();
        return martialArts;
    }


    public MartialArt returnMartialArtObjectById(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sqlQueryCommand = "select * from " + MARTIAL_ART_TABLE +
                " where" + ID_KEY + " = " + id;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);
        MartialArt art = null;
        try {
            if (cursor.moveToFirst()) {
                art = new MartialArt(Integer.parseInt(cursor.getString(0)
                ), cursor.getString(1),
                        cursor.getDouble(2), cursor.getString(3));
            }

        } finally {
            cursor.close();
        }
        database.close();
        return art;
    }


}
