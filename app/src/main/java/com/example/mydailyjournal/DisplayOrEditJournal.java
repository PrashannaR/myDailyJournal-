package com.example.mydailyjournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydailyjournal.database.DBJournal;
import com.example.mydailyjournal.model.Model;

public class DisplayOrEditJournal extends AppCompatActivity {
    TextView tvTitle, tvBody;
    Button btnEdit,btnDelete ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_or_edit_journal);

        tvTitle = findViewById(R.id.tvTitle);
        tvBody = findViewById(R.id.tbBody);

        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID", 0);

        DBJournal dbJournal = new DBJournal(this);
        Model model = dbJournal.getJournal(id);

        tvTitle.setText(model.getTitle());
        tvBody.setText(model.getBody());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbJournal.delete(model.getID());
                startActivity(new Intent(getApplicationContext(), SeeJournal.class));

            }
        });






    }
}