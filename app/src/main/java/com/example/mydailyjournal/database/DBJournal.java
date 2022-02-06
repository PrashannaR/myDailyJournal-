package com.example.mydailyjournal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBJournal extends SQLiteOpenHelper {


    public DBJournal(Context context) {
        super(context, "journal", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE journalTable (ID INT PRIMARY KEY AUTOINCREMENT, " +
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
}
