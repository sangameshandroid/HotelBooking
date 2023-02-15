package com.example.hotelbookingformadmin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public  class FilterRoomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner_rooms;
    RecyclerView roomfilter_recycler;
    private BookingFilterAdapter bfd;
    private List<UserData> filtereddata;
    private String selectedRoom = "Standard";
    private DatabaseReference databaseReference;
    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_room);
        spinner_rooms = findViewById(R.id.spinner_rooms);
        roomfilter_recycler = findViewById(R.id.roomfilter_recycler);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_items, new String[]{"Standard ", "Deluxe", "S.Deluxe", "Premium"});
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner_rooms.setAdapter(adapter);
        spinner_rooms.setOnItemSelectedListener(this);
        spinner_rooms.setSelection(0);

        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        filtereddata = new ArrayList<>();
        bfd = new BookingFilterAdapter(filtereddata);
        roomfilter_recycler.setAdapter(bfd);
        roomfilter_recycler.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedRoom = parent.getItemAtPosition(position).toString();

        Query query = databaseReference.orderByChild("roomtype").equalTo(selectedRoom);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                filtereddata.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    userData = snap.getValue(UserData.class);

                    if (userData != null) {
                        filtereddata.add(userData);
                    }
                }

                bfd.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error querying bookings: " + error.getMessage());

            }
        });

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}







