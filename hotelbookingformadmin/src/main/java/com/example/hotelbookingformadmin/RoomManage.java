package com.example.hotelbookingformadmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RoomManage extends AppCompatActivity implements AdapterListener {
    private RoomDB roomDB;
    private RoomDao roomDao;
    private RoomAdapter ad;
    RecyclerView rv;
    EditText edroom, edcost, edtax;
    Button btnadd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_manage);
        roomDB = RoomDB.getInstance(this);
        roomDao = roomDB.INSTANCE.getDao();
        edroom = findViewById(R.id.editroom);
        edcost = findViewById(R.id.editcost);
        edtax = findViewById(R.id.edittax);
        btnadd = findViewById(R.id.btnadd);
        rv = findViewById(R.id.roomrecycler);
        ad = new RoomAdapter(this,this );
        rv.setAdapter(ad);
        rv.setLayoutManager(new LinearLayoutManager(this));
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String room = edroom.getText().toString();
                String cost = edcost.getText().toString();
                String tax = edtax.getText().toString();
                RoomData roomData = new RoomData(0, room, cost, tax);
                ad.addRoom(roomData);
                roomDao.insert(roomData);
                edroom.setText("");
                edcost.setText("");
                edtax.setText("");
                Toast.makeText(RoomManage.this, "Added Successfully", Toast.LENGTH_SHORT).show();


            }
        });

    }
    private void fetchData(){
        ad.clearData();
        List<RoomData> roomDataList = roomDao.getAllData();
        for (int i=0;i<roomDataList.size();i++){
            RoomData roomData = roomDataList.get(i);
            ad.addRoom(roomData);
        }
    }

    @Override
    public void OnUpdate(RoomData roomData) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("model", roomData);
        startActivity(intent);


    }

    @Override
    public void OnDelete(int id, int pos) {
        roomDao.delete(id);
        ad.removeRoom(pos);


    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }


}