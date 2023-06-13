package com.example.hotelbookingformadmin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Queue;

public class BookingActivity extends AppCompatActivity {
    EditText ed_checkin, ed_checkout;
    Button btn_showbookings;
    Calendar calender;
    DatePickerDialog.OnDateSetListener indateSetListener, outdateSetListener;
    RecyclerView user_recycler;
    private BookingAdapter bookingAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);

        DatabaseReference bookingref = FirebaseDatabase.getInstance().getReference("UserData2");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());

        ed_checkin = findViewById(R.id.ed_checkin);
        ed_checkout = findViewById(R.id.ed_checkout);
        btn_showbookings = findViewById(R.id.btn_showbookings);

        user_recycler = findViewById(R.id.user_recycler);


        ed_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String checkinDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                ed_checkin.setText(checkinDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });





        ed_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String checkoutDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                ed_checkout.setText(checkoutDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });




        btn_showbookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date checkinDate = null;
                Date checkoutDate = null;
                String instr = null;
                String outstr = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    checkinDate = dateFormat.parse(ed_checkin.getText().toString());
                    checkoutDate = dateFormat.parse(ed_checkout.getText().toString());
                    instr = dateFormat.format(checkinDate);
                    outstr = dateFormat.format(checkoutDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Query query = bookingref.orderByChild("checkin").startAt(instr).endAt(outstr);

                bookingAdapter = new BookingAdapter(new ArrayList<>());
                user_recycler.setAdapter(bookingAdapter);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<UserData2> filteredBookings = new ArrayList<>();
                        for (DataSnapshot bookingSnapshot : snapshot.getChildren()) {
                            UserData2 userData2 = bookingSnapshot.getValue(UserData2.class);
                            if (userData2 != null) {
                                filteredBookings.add(userData2);

                            }

                        }

                        bookingAdapter = new BookingAdapter(filteredBookings);
                        user_recycler.setAdapter(bookingAdapter);
                        user_recycler.setLayoutManager(new LinearLayoutManager(BookingActivity.this));
                        bookingAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Error querying bookings: " + error.getMessage());

                    }
                });

            }
        });



    }
}