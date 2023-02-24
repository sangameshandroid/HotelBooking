package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView txt_signup;
    private Button btn_login;
    private EditText ed_email, ed_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_signup = findViewById(R.id.txt_signup);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_email.getText().toString();
                String userpassword = ed_password.getText().toString();

               boolean checklogin =  validLogin(username,userpassword);
               if (checklogin == true){
                   startActivity(new Intent(LoginActivity.this, MainActivity.class));
               } else {
                   Toast.makeText(LoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
               }
            }
        });

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private boolean validLogin(String username, String userpassword) {
        if (username.length()==0){
            ed_email.requestFocus();
            ed_email.setError("Enter in the field");
            return false;
        } else if (!username.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            ed_email.requestFocus();
            ed_email.setError("enter Valid email");
            return false;
        } else if (userpassword.length()<8){
            ed_password.requestFocus();
            ed_password.setError("Enter valid Password");
            return false;
        }
        return true;
    }
}