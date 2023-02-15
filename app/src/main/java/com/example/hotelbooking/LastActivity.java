package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LastActivity extends AppCompatActivity {
    TextView txt_checkin, txt_checkout, txt_roomno, txt_adults, txt_childrens, txt_extra, txt_discount, txt_netamont, txt_promo, txt_extracharge, txt_roomtype;
    Button btn_paynow;
    DatabaseReference db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        txt_checkin = findViewById(R.id.txt_checkin);
        txt_checkout = findViewById(R.id.txt_checkout);
        txt_roomno = findViewById(R.id.txt_Roomno);
        txt_adults = findViewById(R.id.txt_adults);
        txt_childrens = findViewById(R.id.txt_children);
        txt_extra = findViewById(R.id.txt_extra);
        txt_discount = findViewById(R.id.txt_discount);
        txt_netamont = findViewById(R.id.txt_netamount);
        txt_extracharge = findViewById(R.id.txt_extracharge);
        txt_promo = findViewById(R.id.txt_promo);
        txt_roomtype = findViewById(R.id.txt_roomtype);

        btn_paynow =findViewById(R.id.btn_paynow);


        Intent intent = getIntent();
        String checkin = intent.getStringExtra("checkindate");
        String checkout = intent.getStringExtra("checkoutdate");
        int rooms = intent.getIntExtra("roomsno",0);
        int adults = intent.getIntExtra("adultsno",0);
        int childs = intent.getIntExtra("childsno",0);
        String selectedradiotext = intent.getStringExtra("selectedradiotext");
        String selectedradio = intent.getStringExtra("selectedRadiobutton");
        String applieddiscount = intent.getStringExtra("selecteddiscount");
        String extrafacility = intent.getStringExtra("selectedextracharge");
        String selectedcheckbox = intent.getStringExtra("selectedcheckbox");
        String nettotal = intent.getStringExtra("netamount");

        txt_checkin.setText(checkin);
        txt_checkout.setText(checkout);
        txt_roomtype.setText(selectedradiotext);
        txt_roomno.setText(String.valueOf(rooms));
        txt_adults.setText(String.valueOf(adults));
        txt_childrens.setText(String.valueOf(childs));
        txt_discount.setText(applieddiscount);
        txt_promo.setText(selectedradio);
        txt_extracharge.setText(extrafacility);
        txt_extra.setText(selectedcheckbox);
        txt_netamont.setText(nettotal);

        db = FirebaseDatabase.getInstance().getReference();


        btn_paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LastActivity.this, ThankuActivity.class);
                startActivity(intent1);
                String indate = txt_checkin.getText().toString();
                String outdate = txt_checkout.getText().toString();
                String room_type = txt_roomtype.getText().toString();
                String netpayable = txt_netamont.getText().toString();
                UserData userData = new UserData(room_type, indate, outdate, netpayable);
                db.child("UserData").push().setValue(userData);
                Toast.makeText(LastActivity.this, "Your Booking is Sucessfull", Toast.LENGTH_SHORT).show();




            }
        });




    }
}