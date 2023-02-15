package com.example.hotelbookingformadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*
public class UpdateActivity extends AppCompatActivity {
    private EditText edroom2,edcost2, edtax2;
    private Button btnupdate;
    private RoomData roomData;
    private RoomDB roomDB;
    private RoomDao roomDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        roomDB = DatabaseAdmin.getInstance(this);
        roomDao = roomDB.getDao();


        edroom2 = findViewById(R.id.editroom2);
        edcost2 = findViewById(R.id.editcost2);
        edtax2 = findViewById(R.id.edittax2);
        btnupdate = findViewById(R.id.btnupdate);


        roomData = (RoomData) getIntent().getSerializableExtra("model");

        edroom2.setText(roomData.getRoom());
        edcost2.setText(roomData.getCost());
        edtax2.setText(roomData.getTax());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomData roomModel = new RoomData(roomData.getId(),edroom2.getText().toString(), edcost2.getText().toString(), edtax2.getText().toString());
                roomDao.update(roomModel);
                finish();
            }
        });

    }
}*/
