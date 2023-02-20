package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThankuActivity extends AppCompatActivity {
    private TextView txt_fname, txt_lname, txt_email, txt_phone, txt_address, txt_roomtype, txt_checkin, txt_checkout, txt_roono, txt_adults, txt_children, txt_netamount, txt_discount, txt_extra, txt_extracharge, txt_promo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanku);
        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_email = findViewById(R.id.txt_email);
        txt_phone = findViewById(R.id.txt_phone);
        txt_address = findViewById(R.id.txt_address);
        txt_roomtype = findViewById(R.id.txt_roomtype);
        txt_roono = findViewById(R.id.txt_Roomno);
        txt_adults = findViewById(R.id.txt_adults);
        txt_children = findViewById(R.id.txt_children);
        txt_checkin = findViewById(R.id.txt_checkin);
        txt_checkout = findViewById(R.id.txt_checkout);
        txt_netamount = findViewById(R.id.txt_netamount);
        txt_extra = findViewById(R.id.txt_extra);
        txt_extracharge = findViewById(R.id.txt_extracharge);
        txt_promo = findViewById(R.id.txt_promo);
        txt_discount = findViewById(R.id.txt_discount);


        Intent intent = getIntent();
       String Fname= intent.getStringExtra("f_name");
        String Lname=intent.getStringExtra("l_name");
       String Email= intent.getStringExtra("e_mail");
       String Mob = intent.getStringExtra("mob");
        String Add =intent.getStringExtra("add");
        String Indate=intent.getStringExtra("indate");
        String Outdate=intent.getStringExtra("outdate");
        String Netpay = intent.getStringExtra("netpayable");
       String Roomtype=  intent.getStringExtra("room_type");
       String Discount= intent.getStringExtra("discount");
        int Roomno= intent.getIntExtra("room_no",0);
       int Adultno= intent.getIntExtra("adult_no",0);
        int Childno=intent.getIntExtra("child_no",0);
       String Extracharge= intent.getStringExtra("extra_charge");
        String Extrafacility =intent.getStringExtra("extra_facility");
        String Promo =intent.getStringExtra("promo");

        txt_fname.setText(Fname);
        txt_lname.setText(Lname);
        txt_email.setText(Email);
        txt_phone.setText(Mob);
        txt_address.setText(Add);
        txt_checkin.setText(Indate);
        txt_checkout.setText(Outdate);
        txt_roomtype.setText(Roomtype);
        txt_roono.setText(String.valueOf(Roomno));
        txt_adults.setText(String.valueOf(Adultno));
        txt_children.setText(String.valueOf(Childno));
        txt_promo.setText(Promo);
        txt_extracharge.setText(Extracharge);
        txt_discount.setText(Discount);
        txt_extra.setText(Extrafacility);
        txt_netamount.setText(Netpay);



    }
}