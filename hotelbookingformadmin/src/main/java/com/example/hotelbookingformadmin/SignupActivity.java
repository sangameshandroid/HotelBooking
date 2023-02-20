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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
   private TextInputEditText edit_firstname, edit_lastname, edit_phone, edit_email, edit_password, edit_conpassword;
    private Button btn_signup;
    private FirebaseAuth mAuth;
    boolean passVisible;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edit_firstname = findViewById(R.id.edit_firstname);
        edit_lastname = findViewById(R.id.edit_lastname);
        edit_phone = findViewById(R.id.edit_phone);
        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        edit_conpassword = findViewById(R.id.edit_conpassword);
        btn_signup = findViewById(R.id.btn_signup);
        progressBar = findViewById(R.id.progressbar);



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

        edit_conpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right = 2;
                if(event.getAction()== MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edit_conpassword.getRight()-edit_conpassword.getCompoundDrawables()[right].getBounds().width()){
                        int selection = edit_conpassword.getSelectionEnd();
                        if(passVisible){
                            edit_conpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            edit_conpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible=false;
                        }
                        else{
                            edit_conpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            edit_conpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        edit_conpassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = edit_email.getText().toString();
                String password = edit_password.getText().toString();
                String conpassword = edit_conpassword.getText().toString();
                String firstname = edit_firstname.getText().toString();
                String lastname = edit_lastname.getText().toString();
                String phone = edit_phone.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignupActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(conpassword)){
                    Toast.makeText(SignupActivity.this, "Password do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String Userid = user.getUid();
                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Admin").child(Userid);
                            userRef.child("Firstname").setValue(firstname);
                            userRef.child("Lastname").setValue(lastname);
                            userRef.child("Phone").setValue(phone);
                            Intent intent = new Intent(SignupActivity.this, RegistermessageActivity.class);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Failed to create Account", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


    }
}