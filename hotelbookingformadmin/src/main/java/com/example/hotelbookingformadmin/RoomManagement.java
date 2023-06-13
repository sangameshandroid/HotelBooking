package com.example.hotelbookingformadmin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomManagement extends AppCompatActivity{


    RecyclerView roomrecycler;
    private RoomsAdapter roomsAdapter;
    private List<RoomTypefir> list;
    private RoomTypefir rdf;
    private RoomTypefir selectedData;


    EditText edroom, edcost, edtax;
    Button btnadd, btnupdate, btndelete;
   private DatabaseReference db;
    ActivityResultLauncher<Intent> updateLauncher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_management);

        edroom = findViewById(R.id.editroom);
        edcost = findViewById(R.id.editcost);
        edtax = findViewById(R.id.edittax);
        btnadd = findViewById(R.id.btnadd);
        roomrecycler = findViewById(R.id.roomrecycler);

        db = FirebaseDatabase.getInstance().getReference("RoomDatabase");
        list = new ArrayList<>();
        roomsAdapter = new RoomsAdapter(list);














        updateLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Get the updated data from the Intent
                        String key = result.getData().getStringExtra("Key");


                        String room = result.getData().getStringExtra("Room");
                        String cost = result.getData().getStringExtra("Cost");
                        String tax = result.getData().getStringExtra("Tax");

                        // Update the data in your RecyclerView adapter
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getKey().equals(key)) {
                                list.get(i).setRoom(room);
                                list.get(i).setCost(cost);
                                list.get(i).setTax(tax);
                                roomsAdapter.notifyItemChanged(i);
                                break;
                            }
                        }
                    }
                    else if(result.getResultCode()==5){
                      Intent data = result.getData();
                      if(data!=null && data.hasExtra("Position")){
                          int pos = data.getIntExtra("Position", -1);
                          if(pos!=-1){
                              list.remove(pos);
                              roomsAdapter.notifyItemRemoved(pos);
                              Toast.makeText(this, "Room has been deleted successfully", Toast.LENGTH_SHORT).show();
                          }
                      }
                    }

                }
        );

        // Set the adapter to your RecyclerView
        roomrecycler.setAdapter(roomsAdapter);






        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot ds:snapshot.getChildren()
                     ) {
                    rdf= ds.getValue(RoomTypefir.class);


                    rdf.setKey(ds.getKey());
                    list.add(rdf);


                }
                roomsAdapter = new RoomsAdapter(list);
                roomrecycler.setAdapter(roomsAdapter);
                roomrecycler.setLayoutManager(new LinearLayoutManager(RoomManagement.this));
                roomsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RoomManagement.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });














        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String room = edroom.getText().toString();
                String cost = edcost.getText().toString();
                String tax = edtax.getText().toString();
                RoomTypefir rdf = new RoomTypefir(room, cost, tax);
                db.push().setValue(rdf);
                list.add(rdf);


                roomsAdapter.notifyDataSetChanged();


                edroom.setText("");
                edcost.setText("");
                edtax.setText("");
                Toast.makeText(RoomManagement.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });





    }
}