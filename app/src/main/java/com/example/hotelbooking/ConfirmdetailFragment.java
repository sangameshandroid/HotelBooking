package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ConfirmdetailFragment extends Fragment {
    private EditText edit_roomprice, edit_roomtax, edit_extrafacility, edit_discount, edit_discountamt, edit_total, edit_grandtotal;
    private Spinner promospinner;
    private Button btn_payment;
    Bundle bundle;





    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirmdetail, container, false);
        promospinner = view.findViewById(R.id.promospinner);
        edit_total = view.findViewById(R.id.edit_total);
        edit_discount = view.findViewById(R.id.edit_discount);
        edit_discountamt = view.findViewById(R.id.edit_discountamt);
        edit_grandtotal = view.findViewById(R.id.edit_grandtotal);
        edit_roomprice = view.findViewById(R.id.edit_roomprice);
        edit_extrafacility = view.findViewById(R.id.edit_extrafacility);
        edit_roomtax = view.findViewById(R.id.edit_roomtax);
        btn_payment = view.findViewById(R.id.btn_payment);
        edit_extrafacility.setEnabled(false);
        edit_roomtax.setEnabled(false);
        edit_roomprice.setEnabled(false);
        edit_grandtotal.setEnabled(false);
        edit_total.setEnabled(false);
        edit_discountamt.setEnabled(false);
        edit_discount.setEnabled(false);


        bundle = getArguments();
        if(bundle!=null) {

            String Extracharge = bundle.getString("extracharge");
            String total = bundle.getString("total");
            int Roomprice = bundle.getInt("roomPrice",0);
            String Roomtax = bundle.getString("roomTax");

            edit_roomprice.setText(String.valueOf(Roomprice));
            edit_roomtax.setText(Roomtax);
            edit_extrafacility.setText(Extracharge);
            edit_total.setText(total);
        }
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.PromoCodes, R.layout.spinner_codes);
        arrayAdapter.setDropDownViewResource(R.layout.my_spinner_dropdown);
        promospinner.setAdapter(arrayAdapter);

        promospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String discountcode = parent.getItemAtPosition(position).toString();
                int discountpercent = getPercentageDiscount(discountcode);
                edit_discount.setText(String.valueOf(discountpercent));

                String total = edit_total.getText().toString();
                if(!total.isEmpty()){
                    double totalamt = Double.parseDouble(total);
                    double netamt = totalamt *(100-discountpercent)/100;
                    edit_grandtotal.setText(String.valueOf(netamt));
                    edit_discountamt.setText(String.valueOf((totalamt*discountpercent)/100));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ThankuActivity.class );
                String checkin=  bundle.getString("checkindate2");
                String checkout=bundle.getString("checkoutdate2");
                int rooms =bundle.getInt("room2",0);
                int adults =bundle.getInt("adult2",0);
                int childs =bundle.getInt("child2",0);
                String first = bundle.getString("fname");
                String last = bundle.getString("lname");
                String phone = bundle.getString("phone");
                String email = bundle.getString("email");
                String address = bundle.getString("address");
                String extracharge = bundle.getString("extracharge");
                String selectedradio = bundle.getString("selectedradio");
                String discount = edit_discount.getText().toString();
                String promo = promospinner.getSelectedItem().toString();
                String total = edit_grandtotal.getText().toString();
                String roomprice = edit_roomprice.getText().toString();
                String tax = edit_roomtax.getText().toString();

                if(checkin==null && checkout == null && rooms==0 && adults==0 && childs==0){
                    Toast.makeText(getActivity(), "value is null", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("discount", discount);
                intent.putExtra("promo", promo);
                intent.putExtra("total", total);
                intent.putExtra("roomprice", roomprice);
                intent.putExtra("tax", tax);
                intent.putExtra("firstname", first);
                intent.putExtra("lastname", last);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("address", address);
                intent.putExtra("rooms", rooms);
                intent.putExtra("adults", adults);
                intent.putExtra("childs", childs);
                intent.putExtra("selectedradio", selectedradio );
                intent.putExtra("extracharge", extracharge);
                intent.putExtra("checkin", checkin);
                intent.putExtra("checkout", checkout);
                startActivity(intent);


            }
        });




        return view;
    }

    private int getPercentageDiscount(String discountcode){
        int discountpercent=0;
        switch(discountcode){
            case "NEWUSER25":
                discountpercent=25;
                break;
            case "CANVAS20":
                discountpercent=20;
                break;
            case "VALENTINE30":
                discountpercent=30;
                break;
            case "HAPPYSTAY40":
                discountpercent=40;
                break;
        }
        return discountpercent;


    }
}