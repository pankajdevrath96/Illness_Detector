package com.example.macbook.illnessdectector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;

public class DatabaseHelper extends AppCompatActivity{
    Context context;
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        this.context = context;
        db = context.openOrCreateDatabase("userauth",MODE_PRIVATE,null);
        db.execSQL("create table if not exists users(name char,number number, password varchar)");
    }




}
