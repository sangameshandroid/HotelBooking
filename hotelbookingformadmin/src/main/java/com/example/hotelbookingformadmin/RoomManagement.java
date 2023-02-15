package com.example.hotelbookingformadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoomManagement extends AppCompatActivity {


    RecyclerView rv;

    EditText edroom, edcost, edtax;
    Button btnadd;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_management);
        //roomDB = RoomDB.getInstance(this);
        //roomDao = roomDB.getDao();
        edroom = findViewById(R.id.editroom);
        edcost = findViewById(R.id.editcost);
        edtax = findViewById(R.id.edittax);
        btnadd = findViewById(R.id.btnadd);

        db = FirebaseDatabase.getInstance().getReference();


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String room = edroom.getText().toString();
                String cost = edcost.getText().toString();
                String tax = edtax.getText().toString();
                RoomDatafir rdf = new RoomDatafir(room, cost, tax);
                db.child("RoomDatafir").push().setValue(rdf);
               // RoomData roomData = new RoomData(0, room, cost, tax);
                //roomDao.insert(roomData);
                edroom.setText("");
                edcost.setText("");
                edtax.setText("");
                Toast.makeText(RoomManagement.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}