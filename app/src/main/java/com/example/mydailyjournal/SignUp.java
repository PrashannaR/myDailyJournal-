package com.example.mydailyjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydailyjournal.database.DBHelperLogin;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    Button btnSignUp;
    TextView click;
    TextInputLayout nameInputLayout, emailInputLayout, passwordInputLayout, rePasswordInputLayout;
    //database
    DBHelperLogin dbHelperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.btnSignUp);

        click = findViewById(R.id.click);

        nameInputLayout = findViewById(R.id.nameInputLayout);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        rePasswordInputLayout = findViewById(R.id.rePasswordInputLayout);

        //database
        dbHelperLogin = new DBHelperLogin(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get Text from text fields
                String name = nameInputLayout.getEditText().getText().toString();
                String email = emailInputLayout.getEditText().getText().toString();
                String pw = passwordInputLayout.getEditText().getText().toString();
                String rePw = rePasswordInputLayout.getEditText().getText().toString();

                boolean validate = check(name, email, pw, rePw);
                if (validate){
                    if (pw.equals(rePw)){
                        Boolean checkUser = dbHelperLogin.checkUser(email);
                         if (checkUser == false){
                             Boolean add = dbHelperLogin.addData(name, email, pw);
                             if (add == true){
                                 Intent intent = new Intent(SignUp.this, HomeScreen.class);
                                 startActivity(intent);
                                 finish();
                             }else {
                                 Toast.makeText(SignUp.this, "An error occurred", Toast.LENGTH_SHORT).show();
                             }
                         } else {
                             Toast.makeText(SignUp.this, "User Already Exists\n Please Login To Continue", Toast.LENGTH_SHORT).show();
                         }
                    }else {
                        Toast.makeText(SignUp.this, "Please Enter The Same Password as above", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //from login to signup
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
                finish();
            }
        });
    }

    //check validation
    private boolean check(String name, String email, String pw, String rePw) {
        if(name.length() == 0){
            nameInputLayout.requestFocus();
            nameInputLayout.setError("Enter Your Name");
            return false;
        }
        else if(email.length() == 0){
            emailInputLayout.requestFocus();
            emailInputLayout.setError("Enter Your Email");
            return false;
        }
        else if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            emailInputLayout.requestFocus();
            emailInputLayout.setError("Enter Valid Email Address");
            return false;
        }
        else if (pw.length() == 0){
            passwordInputLayout.requestFocus();
            passwordInputLayout.setError("Enter Your Desired Password");
            return false;
        }
        else if (!rePw.equals(pw)){
            rePasswordInputLayout.requestFocus();
            rePasswordInputLayout.setError("Enter The Same Password As Above");
        }
        else {
            return true;
        }


        return false;
    }
}