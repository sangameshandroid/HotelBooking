package com.example.hotelbookingformadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
private TextView txt_signup;
 private TextInputEditText ed_email, ed_password;
private Button btn_login;
private FirebaseAuth mAuth;
boolean passVisible;
ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_signup = findViewById(R.id.txt_signup);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);
        progressBar =findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();


        ed_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right = 2;
                if(event.getAction()== MotionEvent.ACTION_UP){
                    if(event.getRawX()>=ed_password.getRight()-ed_password.getCompoundDrawables()[right].getBounds().width()){
                        int selection = ed_password.getSelectionEnd();
                        if(passVisible){
                            ed_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible=false;
                        }
                        else{
                            ed_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        ed_password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });



        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String Email = ed_email.getText().toString();
                String Password = ed_password.getText().toString();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(LoginActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Password)){
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }

                mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){

                            FirebaseUser user = mAuth.getCurrentUser();

                            if(user!=null){
                                Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                                intent.putExtra("email", Email);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Incorrect UserId or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Incorrect UserId or Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });
    }
}