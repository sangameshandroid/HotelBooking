package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFragment extends Fragment {
    TextView   edbooking;
    TextView edcheckin, edcheckout;

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
        edbooking = view.findViewById(R.id.edbooking);
        edbooking.setFocusable(false);
        edbooking.setEnabled(false);
        txtroonno.setText("0");
        txtadultno.setText("0");
        txtchildno.setText("0");
        txtadult.setText("0");
        txtchild.setText("0");
        txtnoofrooms.setText("0");

        Calendar calendar = Calendar.getInstance();String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());edbooking.setText(currentDate);


        edbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String bookingDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                edbooking.setText(bookingDate);

                            }
                        }, year, month, day);
                datePickerDialog.show();

            }
        });


        edcheckin.setOnClickListener(new View.OnClickListener() {

            @Override
          public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
              calendar.setTimeInMillis(System.currentTimeMillis());
              DatePicker datePicker = new DatePicker(getContext());
              datePicker.setMinDate(calendar.getTimeInMillis());

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String checkinDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                edcheckin.setText(checkinDate);
                            }
                        },     calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
                datePickerDialog.getDatePicker().setMinDate(datePicker.getMinDate());
                datePickerDialog.show();
                                         }
                                     });



        edcheckout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());

                DatePicker datePicker = new DatePicker(getContext());
                datePicker.setMinDate(calendar.getTimeInMillis());

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String checkoutDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                edcheckout.setText(checkoutDate);
                            }
                        }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
                datePickerDialog.getDatePicker().setMinDate(datePicker.getMinDate());
                datePickerDialog.show();


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
                    if(date1.equals(date2)){
                        txtduration.setText("1" + " night");
                    }else{
                        long diffInMs = date2.getTime() - date1.getTime();
                        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMs);
                        txtduration.setText(diffInDays + " nights");

                    }

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
                String booking = edbooking.getText().toString();


                int duration = 0;
                String str = txtduration.getText().toString();
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(str);
                if(matcher.find()){
                    duration = Integer.parseInt(matcher.group());
                }

                int rooms = Integer.parseInt(txtroonno.getText().toString());
                int adults = Integer.parseInt(txtadultno.getText().toString());
                int childs = Integer.parseInt(txtchildno.getText().toString());



                Bundle bundle = new Bundle();
                bundle.putString("checkin", checkin);
                bundle.putString("checkout", checkout);
                bundle.putInt("rooms", rooms);
                bundle.putInt("adults", adults);
                bundle.putInt("duration", duration);
                bundle.putInt("childs", childs);


                    SelectRoomFragment selectRoomFragment = new SelectRoomFragment();
                    selectRoomFragment.setArguments(bundle);

                 TabLayout tabLayout = getActivity().findViewById(R.id.tablayout);
                 tabLayout.getTabAt(1).select();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout, selectRoomFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();







            }
        });




        return view;

    }

   

   /* private boolean validFields(String checkin, String checkout, String booking) {
        if (checkin.length()==0){
            edcheckin.requestFocus();
            edcheckin.setError("Field Not be empty");
            return false;
        } else if (checkout.length()==0){
            edcheckout.requestFocus();
            edcheckout.setError("Field not be empty");
            return false;
        } else if (booking.length()==0){
            edbooking.requestFocus();
            edbooking.setError("Field not be empty");
            return false;
        }
        return true;
    }*/

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