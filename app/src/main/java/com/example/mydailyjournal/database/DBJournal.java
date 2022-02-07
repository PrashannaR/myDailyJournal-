package com.example.mydailyjournal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mydailyjournal.model.Model;

import java.util.ArrayList;
import java.util.List;


public class DBJournal extends SQLiteOpenHelper {


    public DBJournal(Context context) {
        super(context, "journal.db", null, 2);
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

    public long addJournal(Model model){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", model.getTitle());
        contentValues.put("body", model.getBody());
        contentValues.put("date", model.getDate());
        contentValues.put("time", model.getTime());

        long ID = sqLiteDatabase.insert("journalTable", null, contentValues);
        return ID;
    }

    //for single journal
    public Model getJournal(long id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("journalTable", new String[]{"ID","title" ,"body", "date", "time"},"ID=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        return new Model(
                Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }


    //for the list of all the journals
    public List<Model> getJournals(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Model> allJournal = new ArrayList<>();

        String query = "SELECT * FROM journalTable";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Model model = new Model();
                model.setID(cursor.getLong(0));
                model.setTitle(cursor.getString(1));
                model.setBody(cursor.getString(2));
                model.setDate(cursor.getString(3));
                model.setTime(cursor.getString(4));

                allJournal.add(model);

            }while (cursor.moveToNext());
        }

        return allJournal;



    }
    String tableName = "journalTable";
    String idName = "ID";
    public void delete(long id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(tableName,idName+"=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }
}
