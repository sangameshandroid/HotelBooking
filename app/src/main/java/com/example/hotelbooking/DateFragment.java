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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFragment extends Fragment {
    EditText edcheckin, edcheckout;
    TextView txtduration, txtnoofrooms, txtroonno, txtadultno, txtchildno, txtadult, txtchild;
    Button btnadd, btnsubtract, btnadd2, btnsubtract2, btnadd3, btnsubtract3;
    Calendar mycalender;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;



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
        txtroonno.setText("1");
        txtadultno.setText("1");
        txtchildno.setText("1");
        txtadult.setText("1");
        txtchild.setText("1");
        txtnoofrooms.setText("1");


        edcheckin.setOnClickListener(new View.OnClickListener() {

            @Override
          public void onClick(View view) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener1, year, month, day);
           datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                             datePickerDialog.show();
                                         }
                                     });
        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day + "/" + month + "/" + year;
                edcheckin.setText(date);
            }
        };


        edcheckout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day + "/" + month + "/" + year;
                edcheckout.setText(date);
            }
        };

        String sdate = edcheckin.getText().toString();
        String edate = edcheckout.getText().toString();

        try {
            Date date1 = sdf.parse(sdate);
            Date date2 = sdf.parse(edate);

            long startdate = date1.getTime();
            long enddate = date2.getTime();

            if(startdate <= enddate){
                Period period = new Period(startdate, enddate, PeriodType.yearMonthDay());
                    int years = period.getYears();
                    int months = period.getMonths();
                    int days = period.getDays();
                    txtduration.setText(String.valueOf(days));

            }else{
                Toast.makeText(getActivity(), "Checkout date must be higher than checkin date", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

}