package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
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
import com.google.android.material.tabs.TabLayout;
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
    TextView txt_roomprice;
    TextView txt_roomtype;
    TextView txt_roomtax;
    TextView txtbreakfast;
    TextView txtlaundry;
    TextView txtrentalcar;
    TextView txtassistant;
    TextView txttotal, txt_otherfacility;
    Button btn_details;
    CheckBox cb_breakfast, cb_laundry, cb_rentalcar, cb_assistant;
    BigDecimal sum = BigDecimal.ZERO;
    BigDecimal total = BigDecimal.ZERO;
    int price1 = 0, price2=0, percent = 0;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_room, container, false);
        recyclerView = view.findViewById(R.id.recycler_room);
        txt_roomprice = view.findViewById(R.id.txt_roomprice);
        txt_roomtax = view.findViewById(R.id.txt_roomtax);
        txt_otherfacility = view.findViewById(R.id.txt_otherfacility);

        cb_breakfast = view.findViewById(R.id.cb_breakfast);
        btn_details= view.findViewById(R.id.btn_details);
        cb_laundry = view.findViewById(R.id.cb_laundry);
        cb_rentalcar = view.findViewById(R.id.cb_rentalcar);
        cb_assistant = view.findViewById(R.id.cb_assistant);
        txtbreakfast = view.findViewById(R.id.txtbreakfast);
        txtlaundry = view.findViewById(R.id.txtlaundry);
        txt_roomtype = view.findViewById(R.id.txt_roomtype);
        txtrentalcar = view.findViewById(R.id.txtrentalcar);
        txtassistant = view.findViewById(R.id.txtassistant);
        txttotal = view.findViewById(R.id.txttotal);





        userAdapter = new UserAdapter(getContext(), this);

        list = new ArrayList<>();


        db = FirebaseDatabase.getInstance().getReference("RoomDatabase");

        db.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (DataSnapshot snap : task.getResult().getChildren()) {
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
                if (isChecked) {
                    txtbreakfast.setText("100");
                    txt_otherfacility.setText(cb_breakfast.getText().toString());

                    price1 = Integer.parseInt(txt_roomprice.getText().toString());
                    percent = Integer.parseInt(txt_roomtax.getText().toString());
                    price2 = price1 * percent / 100;
                    sum = sum.add(new BigDecimal(txtbreakfast.getText().toString()).add(new BigDecimal(price1).add(new BigDecimal(price2))));
                    txttotal.setText(sum.toString());

                } else {
                    txtbreakfast.setText("0");
                    sum = sum.subtract(new BigDecimal(txtbreakfast.getText().toString()));
                }


            }
        });

        cb_laundry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtlaundry.setText("80");
                    txt_otherfacility.setText(cb_laundry.getText().toString());
                    price1 = Integer.parseInt(txt_roomprice.getText().toString());
                    percent = Integer.parseInt(txt_roomtax.getText().toString());
                    price2 = price1 * percent / 100;
                    sum = sum.add(new BigDecimal(txtlaundry.getText().toString()).add(new BigDecimal(price1).add(new BigDecimal(price2))));
                    txttotal.setText(sum.toString());

                } else {
                    txtlaundry.setText("0");
                    sum = sum.subtract(new BigDecimal(txtlaundry.getText().toString()));

                }


            }
        });

        cb_rentalcar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtrentalcar.setText("500");
                    txt_otherfacility.setText(cb_rentalcar.getText().toString());
                    price1 = Integer.parseInt(txt_roomprice.getText().toString());
                    percent = Integer.parseInt(txt_roomtax.getText().toString());
                    price2 = price1 * percent / 100;
                    sum = sum.add(new BigDecimal(txtrentalcar.getText().toString()).add(new BigDecimal(price1).add(new BigDecimal(price2))));
                    txttotal.setText(sum.toString());

                } else {
                    txtrentalcar.setText("0");
                    sum = sum.subtract(new BigDecimal(txtrentalcar.getText().toString()));
                }


            }
        });

        cb_assistant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtassistant.setText("200");
                    txt_otherfacility.setText(cb_assistant.getText().toString());
                    price1 = Integer.parseInt(txt_roomprice.getText().toString());
                    percent = Integer.parseInt(txt_roomtax.getText().toString());
                    price2 = price1 * percent / 100;
                    sum = sum.add(new BigDecimal(txtassistant.getText().toString()).add(new BigDecimal(price1).add(new BigDecimal(price2))));
                    txttotal.setText(sum.toString());


                } else {
                    txtassistant.setText("0");
                    sum = sum.subtract(new BigDecimal(txtassistant.getText().toString()));
                }


            }
        });






        userAdapter.setOnRadioButtonSelectedListener(new UserAdapter.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int position) {
                RoomTypefir rdf = list.get(position);
                String selectText = rdf.getRoom();
                txt_roomtype.setText(selectText);
                Toast.makeText(getContext(), "Selected: " + selectText, Toast.LENGTH_SHORT).show();
            }
        });

        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();

                Bundle bundle = getArguments();
                if (bundle != null) {
                    String checkindate = bundle.getString("checkin");
                    String checkoutdate = bundle.getString("checkout");
                    int room1 = bundle.getInt("rooms",0);
                    int adult1 = bundle.getInt("adults",0);
                    int child1 = bundle.getInt("childs",0);

                    if(checkindate==null && checkoutdate==null && room1==0 && adult1==0 && child1==0){
                        Toast.makeText(getActivity(), "value is null", Toast.LENGTH_SHORT).show();
                    }
                    bundle2.putString("checkindate1", checkindate);
                    bundle2.putString("checkoutdate1", checkoutdate);
                    bundle2.putInt("room1", room1);
                    bundle2.putInt("adult1", adult1);
                    bundle2.putInt("child1", child1);




                }





                String selectedcheckboxtext = "";
                String selectedextracharge = "";
                if (cb_breakfast.isChecked()) {
                    selectedcheckboxtext = cb_breakfast.getText().toString();
                    selectedextracharge = txtbreakfast.getText().toString();
                }
                if (cb_laundry.isChecked()) {
                    selectedcheckboxtext = cb_laundry.getText().toString();
                    selectedextracharge = txtlaundry.getText().toString();
                }
                if (cb_rentalcar.isChecked()) {
                    selectedcheckboxtext = cb_rentalcar.getText().toString();
                    selectedextracharge = txtrentalcar.getText().toString();
                }
                if (cb_assistant.isChecked()) {
                    selectedcheckboxtext = cb_assistant.getText().toString();
                    selectedextracharge = txtassistant.getText().toString();
                }

                int selectedPosition = userAdapter.getSelectedposition();
                if (selectedPosition != -1) {
                    RoomTypefir rdf = list.get(selectedPosition);
                    String selectedradiotext = rdf.getRoom();
                    String roomprice = txt_roomprice.getText().toString();
                    String roomtax = txt_roomtax.getText().toString();
                    bundle2.putString("selectedradiotext", selectedradiotext);
                    bundle2.putString("roomprice", roomprice);
                    bundle2.putString("roomtax", roomtax);
                } else {
                    Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                }

                String total = txttotal.getText().toString();


                bundle2.putString("selectedcheckbox", selectedcheckboxtext);
                bundle2.putString("selectedextracharge", selectedextracharge);
                bundle2.putString("total", total);
                ContactFragment contactFragment = new ContactFragment();
                contactFragment.setArguments(bundle2);

                TabLayout tabLayout = getActivity().findViewById(R.id.tablayout);
                tabLayout.getTabAt(2).select();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, contactFragment);
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


    public void setTextToSecondTextView(String rprice, String rtax) {
        if (txt_roomprice != null && txt_roomtax != null) {
            txt_roomprice.setText(rprice);
            txt_roomtax.setText(rtax);
        }
    }


}