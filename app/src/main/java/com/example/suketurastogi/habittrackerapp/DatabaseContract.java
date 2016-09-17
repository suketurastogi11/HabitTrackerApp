package com.example.suketurastogi.habittrackerapp;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String DATABASE_NAME = "habit_tracker.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String INTEGER_TYPE          = " INTEGER";
    private static final String COMMA_SEP          = ",";

    public static final String TABLE_NAME       = "habit";
    public static final String COL_HOURS_OF_SLEEP = "HOURS_OF_SLEEP";
    public static final String COL_BREAKFAST = "BREAKFAST";
    public static final String COL_LUNCH = "LUNCH";
    public static final String COL_DINNER = "DINNER";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DatabaseContract() {}

    public static abstract class Table implements BaseColumns {

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COL_HOURS_OF_SLEEP + INTEGER_TYPE + COMMA_SEP +
                COL_BREAKFAST + TEXT_TYPE + COMMA_SEP +
                COL_LUNCH + TEXT_TYPE + COMMA_SEP +
                COL_DINNER + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
