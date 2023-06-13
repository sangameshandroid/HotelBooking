package com.example.hotelbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity2 extends AppCompatActivity {

    EditText edit_email, edit_password;
    TextView txt_signup;
    Button btn_login;
    FirebaseAuth mAuth;
    ProgressBar mProgressbar;
    boolean passVisible;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        txt_signup = findViewById(R.id.txt_signup);
        btn_login = findViewById(R.id.btn_Login);
        mProgressbar = findViewById(R.id.progressbar);



    mProgressbar.setMax(100);


    edit_password.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int right = 2;
            if(event.getAction()== MotionEvent.ACTION_UP){
                if(event.getRawX()>=edit_password.getRight()-edit_password.getCompoundDrawables()[right].getBounds().width()){
                    int selection = edit_password.getSelectionEnd();
                    if(passVisible){
                        edit_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                        edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passVisible=false;
                    }
                    else{
                        edit_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                        edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passVisible = true;
                    }
                    edit_password.setSelection(selection);
                    return true;
                }
            }
            return false;
        }
    });


        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity2.this, SignupActivity2.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressbar.setVisibility(View.VISIBLE);
              mProgressbar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C9D6CF")));
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int progress = 0;
                    @Override
                    public void run() {
                        mProgressbar.setProgress(progress);
                        progress+=10;
                        if(progress<mProgressbar.getMax()){
                            handler.postDelayed(this, 100);

                        }


                    }
                };
                handler.postDelayed(runnable, 100);
                String email = edit_email.getText().toString();
                String password = edit_password.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity2.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity2.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }


                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressbar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            if(user!=null){
                                Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginActivity2.this, "Invalid User ID or Password", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(LoginActivity2.this, "Invalid User ID or Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });



    }
}