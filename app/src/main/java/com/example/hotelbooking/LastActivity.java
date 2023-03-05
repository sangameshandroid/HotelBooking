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
    TextView txt_checkin, txt_checkout, txt_roomno, txt_adults, txt_childrens, txt_discount, txt_netamont, txt_promo, txt_extracharge, txt_roomtype, txt_fname, txt_lname, txt_phone, txt_address, txt_email, txt_roomprice, txt_roomtax;
    DatabaseReference db;
    Button btn_again;

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
        txt_discount = findViewById(R.id.txt_discount);
        txt_roomprice = findViewById(R.id.txt_roomprice);
        txt_roomtax = findViewById(R.id.txt_roomtax);
        txt_netamont = findViewById(R.id.txt_netamount);
        txt_extracharge = findViewById(R.id.txt_extracharge);
        txt_promo = findViewById(R.id.txt_promo);
        txt_roomtype = findViewById(R.id.txt_roomtype);
        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_address = findViewById(R.id.txt_address);
        txt_email = findViewById(R.id.txt_email);
        txt_phone = findViewById(R.id.txt_phone);
        btn_again = findViewById(R.id.btn_again);

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LastActivity.this,LoginActivity.class));
            }
        });



        Intent intent = getIntent();
        String Fname = intent.getStringExtra("fname");
        String Lname = intent.getStringExtra("lname");
        String Email = intent.getStringExtra("email");
        String Address = intent.getStringExtra("add");
        String Phone = intent.getStringExtra("mob");
        String checkin = intent.getStringExtra("indate");
        String checkout = intent.getStringExtra("outdate");
        int rooms = intent.getIntExtra("rooms",0);
        int adults = intent.getIntExtra("adults",0);
        int childs = intent.getIntExtra("childs",0);
        String selectedradiotext = intent.getStringExtra("roomtype");
        String applieddiscount = intent.getStringExtra("promo");
        String extrafacilitycharge = intent.getStringExtra("extracharge");
        String nettotal = intent.getStringExtra("total");
        String roomPrice = intent.getStringExtra("roomprice");
        String roomTax = intent.getStringExtra("tax");
        String Discount = intent.getStringExtra("discount");
        txt_fname.setText(Fname);
        txt_lname.setText(Lname);
        txt_address.setText(Address);
        txt_email.setText(Email);
        txt_phone.setText(Phone);
        txt_roomprice.setText(roomPrice);
        txt_roomtax.setText(roomTax);
        txt_checkin.setText(checkin);
        txt_checkout.setText(checkout);
        txt_roomtype.setText(selectedradiotext);
        txt_roomno.setText(String.valueOf(rooms));
        txt_adults.setText(String.valueOf(adults));
        txt_childrens.setText(String.valueOf(childs));
        txt_discount.setText(Discount);
        txt_promo.setText(applieddiscount);
        txt_extracharge.setText(extrafacilitycharge + "/-");
        txt_netamont.setText(nettotal + "/-");

        db = FirebaseDatabase.getInstance().getReference();


      /* btn_paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Bundle bundle = new Bundle();

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
                int adult_no = Integer.parseInt(txt_adults.getText().toString());
                int child_no = Integer.parseInt(txt_childrens.getText().toString());
                String discount = txt_discount.getText().toString();
                String promo = txt_promo.getText().toString();
                String extra_charge = txt_extracharge.getText().toString();
                String extra_facility = txt_extra.getText().toString();

                bundle.putString("f_name", f_name);
                bundle.putString("l_name", l_name);
                bundle.putString("e_mail", e_mail);
                bundle.putString("add", add);
                bundle.putString("mob", mob);
                bundle.putString("indate", indate);
                bundle.putString("outdate", outdate);
                bundle.putString("room_type", room_type);
                bundle.putString("netpayable", netpayable);
                bundle.putString("discount", discount);
                bundle.putInt("room_no", room_no);
                bundle.putString("promo", promo);

                bundle.putInt("adult_no", adult_no);
                bundle.putInt("child_no", child_no);
                bundle.putString("extra_charge", extra_charge);
                bundle.putString("extra_facility", extra_facility);
                PaymentFragment paymentFragment = new PaymentFragment();
                paymentFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, paymentFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();






            }
        });*/




    }
}