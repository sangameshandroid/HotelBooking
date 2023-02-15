package com.example.hotelbookingformadmin;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminMainActivity extends AppCompatActivity {
Button btnroom, btnpromo, btn_Bookings, btn_Bookingrooms;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_admin_main);
       btnroom = findViewById(R.id.btnroom);
       btnpromo = findViewById(R.id.btnpromo);
       btn_Bookings = findViewById(R.id.btn_Bookings);
       btn_Bookingrooms = findViewById(R.id.btn_Bookingrooms);
       btnroom.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(AdminMainActivity.this, RoomManagement.class);
               startActivity(intent);
           }
       });

       btnpromo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(AdminMainActivity.this, PromoActivity.class);
               startActivity(intent);

           }
       });

       btn_Bookings.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(AdminMainActivity.this, BookingActivity.class);
               startActivity(intent);
           }
       });

       btn_Bookingrooms.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(AdminMainActivity.this, FilterRoomActivity.class);
               startActivity(intent);
           }
       });



    }
}
