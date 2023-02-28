package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class SelectRoomFragment extends Fragment {
    private UserAdapter userAdapter;
    RecyclerView recyclerView;
    DatabaseReference db;
    List<RoomTypefir> list;
    TextView txtroom_price;
    TextView txtroom_tax;
    TextView txtbreakfast;
    TextView txtlaundry;
    TextView txtrentalcar;
    TextView txtassistant;
    TextView txttotal;
    Button btn_add, btn_applypromo, btn_booknow;
    CheckBox cb_breakfast, cb_laundry, cb_rentalcar, cb_assistant;
    BigDecimal sum=BigDecimal.ZERO;
    RadioGroup radiogrouppromo1, radiogrouppromo2, radiogrouppromo3;
    TextView txtpromo1, txtpromo2, txtpromo3, txtnettotal;
    CardView cardview1, cardview2, cardview3;







    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_room, container, false);
        recyclerView = view.findViewById(R.id.recycler_room);
        txtroom_price = view.findViewById(R.id.txtroom_price);
        txtroom_tax = view.findViewById(R.id.txtroom_tax);
        btn_add = view.findViewById(R.id.btn_add);
        cb_breakfast =view.findViewById(R.id.cb_breakfast);
        cb_laundry =view.findViewById(R.id.cb_laundry);
        cb_rentalcar =view.findViewById(R.id.cb_rentalcar);
        cb_assistant =view.findViewById(R.id.cb_assistant);
        txtbreakfast = view.findViewById(R.id.txtbreakfast);
        txtlaundry = view.findViewById(R.id.txtlaundry);
        txtrentalcar = view.findViewById(R.id.txtrentalcar);
        txtassistant = view.findViewById(R.id.txtassistant);
        txttotal = view.findViewById(R.id.txttotal);


        radiogrouppromo1 = view.findViewById(R.id.Radiogrouppromo1);
        radiogrouppromo2 = view.findViewById(R.id.Radiogrouppromo2);
        radiogrouppromo3 = view.findViewById(R.id.Radiogrouppromo3);



        txtpromo1 = view.findViewById(R.id.txtpromo1);
        txtpromo2 = view.findViewById(R.id.txtpromo2);
        txtpromo3 = view.findViewById(R.id.txtpromo3);
        txtnettotal = view.findViewById(R.id.txtnettotal);

        btn_applypromo = view.findViewById(R.id.btn_applypromo);
        btn_booknow = view.findViewById(R.id.btn_Booknow);

        cardview1 = view.findViewById(R.id.cardview1);
        cardview2 = view.findViewById(R.id.cardview2);
        cardview3 = view.findViewById(R.id.cardview3);

        cardview1.setBackgroundResource(R.drawable.cardview_background);
        cardview2.setBackgroundResource(R.drawable.cardview_background);
        cardview3.setBackgroundResource(R.drawable.cardview_background);




        userAdapter = new UserAdapter(getContext(),this);

        list = new ArrayList<>();


        db = FirebaseDatabase.getInstance().getReference("RoomDatabase");

        db.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (DataSnapshot snap:task.getResult().getChildren()) {
                    RoomTypefir rdf = snap.getValue(RoomTypefir.class);
                    list.add(rdf);
                    userAdapter.setData(list);
                    recyclerView.setAdapter(userAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    userAdapter.notifyDataSetChanged();

                }


            }
        });

        cb_breakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtbreakfast.setText("100");
                    sum = sum.add(new BigDecimal(txtbreakfast.getText().toString()));

                }else{
                    txtbreakfast.setText("0");
                    sum = sum.subtract(new BigDecimal(txtbreakfast.getText().toString()));
                }



            }
        });

        cb_laundry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtlaundry.setText("80");
                    sum = sum.add(new BigDecimal(txtlaundry.getText().toString()));

                }else{
                    txtlaundry.setText("0");
                    sum = sum.subtract(new BigDecimal(txtlaundry.getText().toString()));

                }



            }
        });

        cb_rentalcar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtrentalcar.setText("500");
                    sum = sum.add(new BigDecimal(txtrentalcar.getText().toString()));

                }else{
                    txtrentalcar.setText("0");
                    sum = sum.subtract(new BigDecimal(txtrentalcar.getText().toString()));
                }



            }
        });

        cb_assistant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtassistant.setText("200");
                    sum = sum.add(new BigDecimal(txtassistant.getText().toString()));



                }else{
                    txtassistant.setText("0");
                    sum = sum.subtract(new BigDecimal(txtassistant.getText().toString()));
                }



            }
        });




        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price1 = Integer.parseInt(txtroom_price.getText().toString());
                int percent = Integer.parseInt(txtroom_tax.getText().toString());
                int price2 = price1 * percent / 100;

                BigDecimal total = sum.add(new BigDecimal(price1)).add(new BigDecimal(price2));
                txttotal.setText(total.toString());



            }
        });

        btn_applypromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double discount1=0;
                double discount2=0;
                double discount3=0;
                int selectedId1 = radiogrouppromo1.getCheckedRadioButtonId();
                RadioButton selectedRadioButton1 = view.findViewById(selectedId1);

                int selectedId2 = radiogrouppromo2.getCheckedRadioButtonId();
                RadioButton selectedRadioButton2 = view.findViewById(selectedId2);

                int selectedId3 = radiogrouppromo3.getCheckedRadioButtonId();
                RadioButton selectedRadioButton3 = view.findViewById(selectedId3);

                String totalamount = txttotal.getText().toString();
                double Totalamt = Double.parseDouble(totalamount);
                if(selectedId1!=-1) {


                    if (selectedRadioButton1.getId() == R.id.btn_radio1) {
                        String discountPercentageString = txtpromo1.getText().toString();
                        discount1 = Double.parseDouble(discountPercentageString);
                    }
                }
                if(selectedId2!=-1) {
                    if (selectedRadioButton2.getId() == R.id.btn_radio2) {
                        String discountPercentageString = txtpromo2.getText().toString();
                        discount2 = Double.parseDouble(discountPercentageString);
                    }
                }

                if(selectedId3!=-1) {
                    if (selectedRadioButton3.getId() == R.id.btn_radio3) {
                        String discountPercentageString = txtpromo3.getText().toString();
                        discount3 = Double.parseDouble(discountPercentageString);
                    }
                }

                double netAmount = Totalamt - (Totalamt * (discount1 + discount2 + discount3) / 100);

                txtnettotal.setText(String.valueOf(netAmount));
            }
        });


        userAdapter.setOnRadioButtonSelectedListener(new UserAdapter.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int position) {
                RoomTypefir rdf = list.get(position);
                String selectText = rdf.getRoom();
                Toast.makeText(getContext(), "Selected: " + selectText, Toast.LENGTH_SHORT).show();
            }
        });

        btn_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Bundle bundle2 = new Bundle();

              Bundle bundle = getArguments();
              if(bundle!=null){
                 String checkindate = bundle.getString("checkindate");
                  String checkoutdate = bundle.getString("checkoutdate");
                  int room1= bundle.getInt("room1");
                  int adult1=bundle.getInt("adult1");
                  int child1=bundle.getInt("child1");
                  String firstname = bundle.getString("fname");
                  String lasttname = bundle.getString("lname");
                  String email = bundle.getString("email");
                  String address = bundle.getString("address");
                  String phone = bundle.getString("phone");

                  bundle2.putString("checkindate1", checkindate);
                  bundle2.putString("checkoutdate1", checkoutdate);
                  bundle2.putInt("room2", room1);
                  bundle2.putInt("adult2", adult1);
                  bundle2.putInt("child2", child1);
                  bundle2.putString("firstname", firstname);
                  bundle2.putString("lastname", lasttname);
                  bundle2.putString("email1", email);
                  bundle2.putString("address1", address);
                  bundle2.putString("phone1", phone);
              }



                int selectedid1 = radiogrouppromo1.getCheckedRadioButtonId();
                RadioButton selectedradio1 = view.findViewById(selectedid1);

                int selectedid2 = radiogrouppromo2.getCheckedRadioButtonId();
                RadioButton selectedradio2 = view.findViewById(selectedid2);

                int selectedid3 = radiogrouppromo3.getCheckedRadioButtonId();
                RadioButton selectedradio3 = view.findViewById(selectedid3);

                String selectedradiobuttontext = "";
                String selecteddiscount = "";



                if(selectedid1!=-1){

                    if(selectedradio1.getId() == R.id.btn_radio1){
                        selectedradiobuttontext = selectedradio1.getText().toString();
                        selecteddiscount = txtpromo1.getText().toString();

                    }
                }

                if(selectedid2!=-1){

                    if(selectedradio2.getId() == R.id.btn_radio2){
                         selectedradiobuttontext = selectedradio2.getText().toString();
                        selecteddiscount = txtpromo2.getText().toString();

                    }
                }

                if(selectedid3!=-1){

                    if(selectedradio3.getId() == R.id.btn_radio3){
                        selectedradiobuttontext = selectedradio3.getText().toString();
                        selecteddiscount = txtpromo3.getText().toString();

                    }
                }

                String selectedcheckboxtext="";
                String selectedextracharge = "";
                if(cb_breakfast.isChecked()){
                    selectedcheckboxtext = cb_breakfast.getText().toString();
                    selectedextracharge = txtbreakfast.getText().toString();
                }
                if(cb_laundry.isChecked()){
                    selectedcheckboxtext = cb_laundry.getText().toString();
                    selectedextracharge = txtlaundry.getText().toString();
                }
                if(cb_rentalcar.isChecked()){
                    selectedcheckboxtext = cb_rentalcar.getText().toString();
                    selectedextracharge = txtrentalcar.getText().toString();
                }
                if(cb_assistant.isChecked()){
                    selectedcheckboxtext = cb_assistant.getText().toString();
                    selectedextracharge = txtassistant.getText().toString();
                }

                int selectedPosition = userAdapter.getSelectedposition();
                if (selectedPosition != -1) {
                    RoomTypefir rdf = list.get(selectedPosition);
                    String selectedradiotext = rdf.getRoom();
                    bundle2.putString("selectedradiotext", selectedradiotext);
                } else {
                    Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                }

               String netamt= txtnettotal.getText().toString();


                bundle2.putString("selectedRadiobutton", selectedradiobuttontext);
                bundle2.putString("selecteddiscount", selecteddiscount);
                bundle2.putString("selectedcheckbox", selectedcheckboxtext);
                bundle2.putString("selectedextracharge", selectedextracharge);
                bundle2.putString("netamount", netamt);
                ConfirmdetailFragment confirmdetailFragment = new ConfirmdetailFragment();
                confirmdetailFragment.setArguments(bundle2);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, confirmdetailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();



            }
        });





























        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void setTextToSecondTextView(String rprice, String rtax){
        if( txtroom_price != null && txtroom_tax != null) {
            txtroom_price.setText(rprice);
            txtroom_tax.setText(rtax);
        }
    }



}