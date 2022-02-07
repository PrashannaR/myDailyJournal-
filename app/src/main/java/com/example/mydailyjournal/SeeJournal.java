package com.example.mydailyjournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.mydailyjournal.adapter.Adapter;
import com.example.mydailyjournal.database.DBJournal;
import com.example.mydailyjournal.model.Model;

import java.util.List;

public class SeeJournal extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Model> models;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_journal);

        DBJournal dbJournal = new DBJournal(this);
        models = dbJournal.getJournals();

        recyclerView = findViewById(R.id.journalList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,models);

        recyclerView.setAdapter(adapter);
    }
}