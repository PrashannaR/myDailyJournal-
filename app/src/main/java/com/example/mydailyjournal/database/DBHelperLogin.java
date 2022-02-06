package com.example.mydailyjournal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperLogin extends SQLiteOpenHelper {
    public static final String DBName = "Login.db";


    public DBHelperLogin( Context context) {
        super(context, "Login.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(name TEXT, email TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");

    }

    public boolean addData(String name, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long res = MyDB.insert("users", null, contentValues);

        if (res == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean checkUser(String email){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("SELECT * FROM users where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkEmailAndPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users where email = ? and password = ?", new String[] {email, password});
        if (cursor.getCount()> 0){
            return true;
        }else {
            return false;
        }
    }
}
