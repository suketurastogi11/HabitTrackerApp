package com.example.suketurastogi.habittrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Contract extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "habit_tracker.db";
    public static final String TABLE_NAME = "exercise_habits";

    public static final String COL_HOURS_OF_SLEEP = "HOURS_OF_SLEEP";
    public static final String COL_BREAKFAST = "BREAKFAST";
    public static final String COL_LUNCH = "LUNCH";
    public static final String COL_DINNER = "DINNER";

    public Contract(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,HOURS_OF_SLEEP INTEGER,BREAKFAST TEXT,LUNCH TEXT,DINNER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
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

    // this method is call when delete the table from the database
    public void deleteDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
