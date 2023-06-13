package com.example.hotelbookingformadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class PromoUpdateActivity extends AppCompatActivity {
    EditText edit_Promoid, edit_Promocode, edit_Promodiscount;
    Button btn_update, btn_delete;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_update);
        edit_Promoid = findViewById(R.id.edit_Promoid);
        edit_Promocode = findViewById(R.id.edit_Promocode);
        edit_Promodiscount = findViewById(R.id.edit_Promodiscount);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        String Key = intent.getStringExtra("Key");
        String Promoid = intent.getStringExtra("promoid");
        String Promocode = intent.getStringExtra("promocode");
        String Promodiscount = intent.getStringExtra("promodiscount");

        db = FirebaseDatabase.getInstance().getReference("PromoDatabase").child(Key);
        edit_Promoid.setText(Promoid);
        edit_Promocode.setText(Promocode);
        edit_Promodiscount.setText(Promodiscount);




        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String promoid = edit_Promoid.getText().toString();
                String promocode = edit_Promocode.getText().toString();
                String promodiscount = edit_Promodiscount.getText().toString();

                if(Key!=null){
                    db.child("promoid").setValue(promoid);
                    db.child("promocode").setValue(promocode);
                    db.child("promodiscount").setValue(promodiscount).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Intent resIntent = new Intent();
                            resIntent.putExtra("Key", Key);
                            resIntent.putExtra("promoid", promoid);
                            resIntent.putExtra("promocode", promocode);
                            resIntent.putExtra("promodiscount", promodiscount);
                            setResult(Activity.RESULT_OK, resIntent);
                            finish();

                        }
                    });
                }else{
                    Toast.makeText(PromoUpdateActivity.this, "Key is null", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              db.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful()){
                          Intent intent1 = new Intent();
                          intent1.putExtra("position", position);
                          setResult(6, intent1);
                          finish();
                      }
                  }
              });
            }
        });
    }
}