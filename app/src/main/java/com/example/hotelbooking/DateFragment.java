package com.example.hotelbooking;

import static android.widget.ArrayAdapter.*;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.content.PeriodicSync;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DateFragment extends Fragment {
    EditText edcheckin, edcheckout;
    TextView txtduration, txtnoofrooms, txtroonno, txtadultno, txtchildno, txtadult, txtchild;
    Button btnadd, btnsubtract, btnadd2, btnsubtract2, btnadd3, btnsubtract3, selectrooms;
    Calendar mycalender;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        mycalender = Calendar.getInstance();
        final int year = mycalender.get(Calendar.YEAR);
        final int month = mycalender.get(Calendar.MONTH);
        final int day = mycalender.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        edcheckin = view.findViewById(R.id.edcheckin);
        edcheckout = view.findViewById(R.id.edcheckout);
        txtduration = view.findViewById(R.id.txtduration);
        txtnoofrooms = view.findViewById(R.id.txtnoofroom);
        txtroonno = view.findViewById(R.id.txtroomno);
        txtadultno = view.findViewById(R.id.txtadultno);
        txtchildno = view.findViewById(R.id.txtchildno);
        txtadult = view.findViewById(R.id.txtadult);
        txtchild = view.findViewById(R.id.txtchild);
        btnadd = view.findViewById(R.id.btnadd);
        btnsubtract = view.findViewById(R.id.btnsubtract);
        btnadd2 = view.findViewById(R.id.btnadd2);
        btnsubtract2 = view.findViewById(R.id.btnsubtract2);
        btnadd3 = view.findViewById(R.id.btnadd3);
        btnsubtract3 = view.findViewById(R.id.btnsubtract3);
        selectrooms = view.findViewById(R.id.selectRooms);
        txtroonno.setText("1");
        txtadultno.setText("1");
        txtchildno.setText("1");
        txtadult.setText("1");
        txtchild.setText("1");
        txtnoofrooms.setText("1");


        edcheckin.setOnClickListener(new View.OnClickListener() {

            @Override
          public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String checkinDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                edcheckin.setText(checkinDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
                                         }
                                     });



        edcheckout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String checkoutDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                edcheckout.setText(checkoutDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        edcheckout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                String dateStr1 = edcheckin.getText().toString();
                String dateStr2 = edcheckout.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date1 = sdf.parse(dateStr1);
                    Date date2 = sdf.parse(dateStr2);
                    long diffInMs = date2.getTime() - date1.getTime();
                    long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMs);
                    txtduration.setText(diffInDays + " nights");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            });



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval = txtroonno.getText().toString();
                int val = Integer.parseInt(currentval);
                val++;
                txtroonno.setText(String.valueOf(val));
                txtnoofrooms.setText(String.valueOf(val));
            }
        });



        btnsubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval = txtroonno.getText().toString();
                int val = Integer.parseInt(currentval);
                val--;
                txtroonno.setText(String.valueOf(val));
                txtnoofrooms.setText(String.valueOf(val));

            }
        });

        btnadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval2 = txtadultno.getText().toString();
                int val2 = Integer.parseInt(currentval2);
                val2++;
                txtadultno.setText(String.valueOf(val2));
                txtadult.setText(String.valueOf(val2));
            }
        });

        btnsubtract2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval2 = txtadultno.getText().toString();
                int val2 = Integer.parseInt(currentval2);
                val2--;
                txtadultno.setText(String.valueOf(val2));
                txtadult.setText(String.valueOf(val2));

            }
        });

        btnadd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval3 = txtchildno.getText().toString();
                int val3 = Integer.parseInt(currentval3);
                val3++;
                txtchildno.setText(String.valueOf(val3));
                txtchild.setText(String.valueOf(val3));
            }
        });

        btnsubtract3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval3 = txtchildno.getText().toString();
                int val3 = Integer.parseInt(currentval3);
                val3--;
                txtchildno.setText(String.valueOf(val3));
                txtchild.setText(String.valueOf(val3));
            }
        });

        selectrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String checkin = edcheckin.getText().toString();
                String checkout = edcheckout.getText().toString();
                int rooms = Integer.parseInt(txtroonno.getText().toString());
                int adults = Integer.parseInt(txtadultno.getText().toString());
                int childs = Integer.parseInt(txtchildno.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putString("checkin", checkin);
                bundle.putString("checkout", checkout);
                bundle.putInt("rooms", rooms);
                bundle.putInt("adults", adults);
                bundle.putInt("childs", childs);

                ContactFragment contactFragment = new ContactFragment();
                contactFragment.setArguments(bundle);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, contactFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });




        return view;

    }
    public static int getDaysDifference(Date sdate, Date edate){
        if(sdate==null||edate==null){
            return 0;
        }

        else {

            return (int) ((edate.getTime() - sdate.getTime()) / (1000 * 60 * 60 * 24));
        }
    }

    public String getCheckin(){
        return edcheckin.getText().toString();
    }
    public String getCheckout(){
        return edcheckout.getText().toString();
    }

    public int getRoomno(){
        return Integer.parseInt(txtroonno.getText().toString());
    }
    public int getAdultno(){
        return Integer.parseInt(txtadultno.getText().toString());
    }
    public int getChildno(){
        return Integer.parseInt(txtchildno.getText().toString());
    }


}