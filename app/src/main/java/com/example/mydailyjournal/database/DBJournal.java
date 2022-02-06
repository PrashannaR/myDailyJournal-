package com.example.mydailyjournal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mydailyjournal.model.Model;


public class DBJournal extends SQLiteOpenHelper {


    public DBJournal(Context context) {
        super(context, "journal", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE journalTable (ID INT PRIMARY KEY, " +
                "title TEXT," +
                "body TEXT," +
                "date TEXT," +
                "time TEXT)";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i >= i1)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS journalTable");
        onCreate(sqLiteDatabase);

    }

    public long addJournal(Model model){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", model.getTitle());
        contentValues.put("body", model.getBody());
        contentValues.put("date", model.getBody());
        contentValues.put("time", model.getTime());

        long ID = sqLiteDatabase.insert("journalTable", null, contentValues);
        Log.d("aayo", "ID: " + ID);
        return ID;
    }
}
