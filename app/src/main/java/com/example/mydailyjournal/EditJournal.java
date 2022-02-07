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
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class EditJournal extends AppCompatActivity {
    TextInputLayout titleInputLayout, bodyInputLayout;
    TextView name;
    Button btnUpdate;
    Calendar calendar;
    String date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        titleInputLayout = findViewById(R.id.titleInputLayout);
        bodyInputLayout = findViewById(R.id.bodyInputLayout);

        name = findViewById(R.id.name);

        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID",0);
        DBJournal dbJournal = new DBJournal(this);
        Model model = dbJournal.getJournal(id);

        titleInputLayout.getEditText().setText(model.getTitle());
        name.setText(model.getTitle());
        bodyInputLayout.getEditText().setText(model.getBody());



        //get date and time
        calendar = Calendar.getInstance();
        date = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        time = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInputLayout.getEditText().getText().toString();
                String body = bodyInputLayout.getEditText().getText().toString();

                model.setTitle(title);
                model.setBody(body);

                int id = dbJournal.edit(model);

                if(id == model.getID()){
                    Toast.makeText(EditJournal.this, "Journal Successfully Updated", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(EditJournal.this, DisplayOrEditJournal.class);
                intent.putExtra("ID", model.getID());
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