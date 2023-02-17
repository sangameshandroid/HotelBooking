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
    TextView txt_checkin, txt_checkout, txt_roomno, txt_adults, txt_childrens, txt_extra, txt_discount, txt_netamont, txt_promo, txt_extracharge, txt_roomtype, txt_fname, txt_lname, txt_phone, txt_address, txt_email;
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
        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_address = findViewById(R.id.txt_address);
        txt_email = findViewById(R.id.txt_email);
        txt_phone = findViewById(R.id.txt_phone);

        btn_paynow =findViewById(R.id.btn_paynow);


        Intent intent = getIntent();
        String Fname = intent.getStringExtra("firstname");
        String Lname = intent.getStringExtra("lastname");
        String Email = intent.getStringExtra("email1");
        String Address = intent.getStringExtra("address1");
        String Phone = intent.getStringExtra("phone1");
        String checkin = intent.getStringExtra("checkindate1");
        String checkout = intent.getStringExtra("checkoutdate1");
        int rooms = intent.getIntExtra("room2",0);
        int adults = intent.getIntExtra("adult2",0);
        int childs = intent.getIntExtra("child2",0);
        String selectedradiotext = intent.getStringExtra("selectedradiotext");
        String selectedradio = intent.getStringExtra("selectedRadiobutton");
        String applieddiscount = intent.getStringExtra("selecteddiscount");
        String extrafacility = intent.getStringExtra("selectedextracharge");
        String selectedcheckbox = intent.getStringExtra("selectedcheckbox");
        String nettotal = intent.getStringExtra("netamount");
        txt_fname.setText(Fname);
        txt_lname.setText(Lname);
        txt_address.setText(Address);
        txt_email.setText(Email);
        txt_phone.setText(Phone);
        txt_checkin.setText(checkin);
        txt_checkout.setText(checkout);
        txt_roomtype.setText(selectedradiotext);
        txt_roomno.setText(String.valueOf(rooms));
        txt_adults.setText(String.valueOf(adults));
        txt_childrens.setText(String.valueOf(childs));
        txt_discount.setText(applieddiscount);
        txt_promo.setText(selectedradio);
        txt_extracharge.setText(extrafacility + "/-");
        txt_extra.setText(selectedcheckbox);
        txt_netamont.setText(nettotal + "/-");

        db = FirebaseDatabase.getInstance().getReference();


        btn_paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LastActivity.this, ThankuActivity.class);

                String f_name = txt_fname.getText().toString();
                String l_name = txt_lname.getText().toString();
                String e_mail = txt_email.getText().toString();
                String add = txt_address.getText().toString();
                String mob = txt_phone.getText().toString();
                String indate = txt_checkin.getText().toString();
                String outdate = txt_checkout.getText().toString();
                String room_type = txt_roomtype.getText().toString();
                String netpayable = txt_netamont.getText().toString();
                UserData2 userData2 = new UserData2(f_name, l_name, e_mail, mob, add, room_type, indate, outdate, netpayable);
                db.child("UserData2").push().setValue(userData2);
                Toast.makeText(LastActivity.this, "Your Booking is Sucessfull", Toast.LENGTH_SHORT).show();

                int room_no = Integer.parseInt(txt_roomno.getText().toString());
                int adult_no = Integer.parseInt(txt_roomno.getText().toString());
                int child_no = Integer.parseInt(txt_childrens.getText().toString());
                String discount = txt_discount.getText().toString();
                String promo = txt_promo.getText().toString();
                String extra_charge = txt_extracharge.getText().toString();
                String extra_facility = txt_extra.getText().toString();

                intent1.putExtra("f_name", f_name);
                intent1.putExtra("l_name", l_name);
                intent1.putExtra("e_mail", e_mail);
                intent1.putExtra("add", add);
                intent1.putExtra("mob", mob);
                intent1.putExtra("indate", indate);
                intent1.putExtra("outdate", outdate);
                intent1.putExtra("room_type", room_type);
                intent1.putExtra("netpayable", netpayable);
                intent1.putExtra("discount", discount);
                intent1.putExtra("room_no", room_no);
                intent1.putExtra("promo", promo);
                intent1.putExtra("adult_no", adult_no);
                intent1.putExtra("child_no", child_no);
                intent1.putExtra("extra_charge", extra_charge);
                intent1.putExtra("extra_facility", extra_facility);
                startActivity(intent1);




            }
        });




    }
}