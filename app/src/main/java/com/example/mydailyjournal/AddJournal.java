package com.example.mydailyjournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mydailyjournal.database.DBJournal;
import com.example.mydailyjournal.model.Model;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;


public class AddJournal extends AppCompatActivity {

    TextInputLayout titleInputLayout, bodyInputLayout;
    Button btnSave;
    Calendar calendar;
    String date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        titleInputLayout = findViewById(R.id.titleInputLayout);
        bodyInputLayout = findViewById(R.id.bodyInputLayout);

        btnSave = findViewById(R.id.btnSave);

        //get date and time
        calendar = Calendar.getInstance();
        date = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        time = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInputLayout.getEditText().getText().toString();
                String body = bodyInputLayout.getEditText().getText().toString();

                Model model = new Model(title, body, date, time);
                DBJournal dbJournal = new DBJournal(AddJournal.this);
                dbJournal.addJournal(model);
                Toast.makeText(AddJournal.this, "Journal Successfully Saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddJournal.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });



    }

    private String pad(int i) {
        if (i < 10){
            return "0"+i;
        }else {
            return String.valueOf(i);
        }
    }
}