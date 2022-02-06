package com.example.mydailyjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    Button btnLogin;
    TextView click;
    TextInputLayout emailInputLayout, passwordInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);

        click = findViewById(R.id.click);

        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInputLayout.getEditText().getText().toString();
                String pw = passwordInputLayout.getEditText().getText().toString();

                boolean validate = check(email, pw);

                if(validate){
                    Toast.makeText(Login.this, "This works", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Nope", Toast.LENGTH_SHORT).show();
                }

            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });
    }

    private boolean check(String email, String pw) {
        if(email.length() == 0){
            emailInputLayout.requestFocus();
            emailInputLayout.setError("Enter Your Email Address");
            return false;
        }
        else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            emailInputLayout.requestFocus();
            emailInputLayout.setError("Enter Valid Email Address");
            return false;
        }
        else if(pw.length() == 0){
            passwordInputLayout.requestFocus();
            passwordInputLayout.setError("Enter Your Password");
            return false;
        }
        return true;
    }
}