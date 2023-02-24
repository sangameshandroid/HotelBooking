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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateRoomActivity extends AppCompatActivity {
    private EditText edit_room2, edit_cost2,edit_tax2;
    private Button btnupdate, btndelete;
    DatabaseReference databaseRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_room);
        edit_room2 = findViewById(R.id.edit_room2);
        edit_cost2 = findViewById(R.id.edit_cost2);
        edit_tax2 = findViewById(R.id.edit_tax2);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);




        Intent intent = getIntent();
        int Position =   intent.getIntExtra("position", -1);
        String Key = intent.getStringExtra("key");
        String room2 = intent.getStringExtra("room");
        String cost2 = intent.getStringExtra("cost");
        String tax2 = intent.getStringExtra("tax");

        databaseRef = FirebaseDatabase.getInstance().getReference("RoomDatabase").child(Key);

        edit_room2.setText(room2);
        edit_cost2.setText(cost2);
        edit_tax2.setText(tax2);


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Room = edit_room2.getText().toString();
                String Cost = edit_cost2.getText().toString();
                String Tax = edit_tax2.getText().toString();
                if(Key!=null) {


                    databaseRef.child("Room").setValue(Room);
                    databaseRef.child("Cost").setValue(Cost);
                    databaseRef.child("Tax").setValue(Tax).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Intent resIntent = new Intent();
                            resIntent.putExtra("Key", Key);
                            resIntent.putExtra("Room", Room);
                            resIntent.putExtra("Cost", Cost);
                            resIntent.putExtra("Tax", Tax);
                            setResult(Activity.RESULT_OK, resIntent);
                            finish();
                        }
                    });
                }else{
                    Toast.makeText(UpdateRoomActivity.this, "Key is null", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("Position", Position);
                            setResult(5,resultIntent);
                            finish();

                        }

                    }
                });

            }
        });


    }
}