package com.example.suketurastogi.habittrackerapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {

    DatabaseHelper habitDb;

    Integer hoursOfSleep;
    String breakfast;
    String lunch;
    String dinner;

    //Initializing Cursor of our Database.
    private Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitDb = new DatabaseHelper(this);

        res = habitDb.getAllData();
    }

    //Call this method to insert a new row with these details.
    public void insert(){
        hoursOfSleep = 8;
        breakfast = "x";
        lunch = "y";
        dinner = "z";

        habitDb.insertData(hoursOfSleep,breakfast,lunch,dinner);
    }

    //Call this method to get all the details of cursor position 1.
    public void query(){

        if (res.getCount() != 0){

            res.moveToPosition(1);

            hoursOfSleep = Integer.parseInt(res.getString(1));
            breakfast = res.getString(2);
            lunch = res.getString(3);
            dinner = res.getString(4);
        }
    }

    //Call this method to update the first row & first column(Hours Of Sleep) value.
    public void update(){

        hoursOfSleep = 6;
        String id = "1";
        habitDb.updateData(id,hoursOfSleep);
    }

    //Call this method to delete all rows one by one by using cursor.
    public void delete(){

        if (res.getCount() != 0){

            while (res.moveToNext()){
                String id = String.valueOf(res.getPosition());
                habitDb.deleteData(id);
            }
        }
    }

    //Call this method to Delete whole table.
    public void deleteTable(){
        habitDb.deleteTable();
    }

    //Call this method to delete whole database.
    public void deleteDatabase(Context ctx) {
        ctx.deleteDatabase(habitDb.getDatabaseName());
    }
}
