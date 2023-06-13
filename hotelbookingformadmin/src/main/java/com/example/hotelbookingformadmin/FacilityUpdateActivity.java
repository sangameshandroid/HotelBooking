package com.example.hotelbookingformadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FacilityUpdateActivity extends AppCompatActivity {
    EditText edit_Extrafacility, edit_Extracharge;
    Button btn_Update, btn_Delete;
    private DatabaseReference dr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_update);
        edit_Extrafacility = findViewById(R.id.edit_Extrafacilty);
        edit_Extracharge = findViewById(R.id.edit_Extracharge);
        btn_Update = findViewById(R.id.btn_Update);
        btn_Delete = findViewById(R.id.btn_Delete);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        String Key = intent.getStringExtra("Key");
        String extrafacility = intent.getStringExtra("extrafacility");
        String extracharge = intent.getStringExtra("extracharge");

        dr = FirebaseDatabase.getInstance().getReference("ExtraFacility").child(Key);

        edit_Extrafacility.setText(extrafacility);
        edit_Extracharge.setText(extracharge);


        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Extrafacility = edit_Extrafacility.getText().toString();
                String Extracharge = edit_Extracharge.getText().toString();

                if(Key!=null){
                     dr.child("extrafacilty").setValue(Extrafacility);
                     dr.child("extracharge").setValue(Extracharge).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void unused) {
                             Intent resIntent = new Intent();
                             resIntent.putExtra("Key", Key);
                             resIntent.putExtra("Extrafacility", Extrafacility);
                             resIntent.putExtra("Extracharge", Extracharge);
                             setResult(Activity.RESULT_OK, resIntent);
                             finish();
                         }
                     });

                }
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dr.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent1 = new Intent();
                            intent1.putExtra("position", position);
                            setResult(7, intent1);
                            finish();
                        }
                    }
                });
            }
        });

    }
}