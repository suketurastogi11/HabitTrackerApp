package com.example.suketurastogi.habittrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = DatabaseContract.TABLE_NAME;
    public static final String COL_HOURS_OF_SLEEP = DatabaseContract.COL_HOURS_OF_SLEEP;
    public static final String COL_BREAKFAST = DatabaseContract.COL_BREAKFAST;
    public static final String COL_LUNCH = DatabaseContract.COL_LUNCH;
    public static final String COL_DINNER = DatabaseContract.COL_DINNER;

    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatabaseContract.Table.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DatabaseContract.Table.DELETE_TABLE);
        onCreate(db);
    }

    public void insertData(Integer hoursOfSleep, String breakfast, String lunch, String dinner) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_HOURS_OF_SLEEP, hoursOfSleep);
        contentValues.put(COL_BREAKFAST, breakfast);
        contentValues.put(COL_LUNCH, lunch);
        contentValues.put(COL_DINNER, dinner);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public void updateData(String id, Integer hoursOfSleep) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_HOURS_OF_SLEEP, hoursOfSleep);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
    }

    public void deleteData(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    // Call this method when delete the table from the database
    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);

    }

}
