package com.example.hotelbookingformadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistermessageActivity extends AppCompatActivity {
    Button btn_loginback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registermessage);
        btn_loginback = findViewById(R.id.btn_loginback);


        btn_loginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistermessageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}