package com.example.hotelbookingformadmin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NameFilterActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private RecyclerView name_recycler;
    private AutoCompleteTextView msearchbar;
    private NameFilterAdapter nameFilterAdapter;
    private  List<UserData2> userData2List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_filter);
        name_recycler = findViewById(R.id.name_recycler);
        msearchbar = findViewById(R.id.msearchbar);

        final List<String> optionslist = new ArrayList<>();
        userData2List = new ArrayList<>();

        nameFilterAdapter =new NameFilterAdapter(userData2List);
        name_recycler.setAdapter(nameFilterAdapter);
        name_recycler.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference("UserData2");

        mDatabase.orderByChild("fname").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot usersnapshot:snapshot.getChildren()
                     ) {
                    String Firstname = usersnapshot.child("fname").getValue(String.class);
                    optionslist.add(Firstname);


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(NameFilterActivity.this, android.R.layout.simple_list_item_1, optionslist);
                msearchbar.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled", error.toException());

            }
        });



        msearchbar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String searchQuery = parent.getItemAtPosition(position).toString();
                Query query = mDatabase.orderByChild("fname").startAt(searchQuery).endAt(searchQuery + "\uf8ff");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        userData2List.clear();
                        for (DataSnapshot usersnap:snapshot.getChildren()
                        ) {
                            UserData2 userData2 = usersnap.getValue(UserData2.class);

                            if(userData2!=null){
                                userData2List.add(userData2);

                            }

                        }

                        nameFilterAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "onCancelled", error.toException());

                    }
                });

            }
        });


    }
}