package com.example.hotelbooking;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
    private boolean[] initialCheckBoxState = {false, false, false, false};
    int otherprice = 0;
    int total = 0;
    int bigtotal = 0;
    int roomprice = 0;
    int Duration =0;
    Bundle bundle;


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

        cb_breakfast.setCompoundDrawablePadding(3);
        cb_laundry.setCompoundDrawablePadding(3);
        cb_rentalcar.setCompoundDrawablePadding(3);
        cb_assistant.setCompoundDrawablePadding(3);
        txtbreakfast.setText("100");
        txtlaundry.setText("80");
        txtrentalcar.setText("500");
        txtassistant.setText("200");
        bundle = getArguments();
        if(bundle!=null) {
            Duration = bundle.getInt("duration", 0);
        }






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
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){


                     otherprice += Integer.parseInt(txtbreakfast.getText().toString());
                     total  += Duration * Integer.parseInt(txtbreakfast.getText().toString());
                        Drawable checkmark = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_check_24);
                        checkmark.setTint(getResources().getColor(R.color.custom_color));
                        cb_breakfast.setButtonDrawable(checkmark);

                    }
                    else{
                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_breakfast.setChecked(false);
                        cb_breakfast.setButtonDrawable(R.drawable.custom_checkbox);
                    }


                } else {
                  otherprice -= Integer.parseInt(txtbreakfast.getText().toString());
                  total -= Duration*Integer.parseInt(txtbreakfast.getText().toString());

                    cb_breakfast.setButtonDrawable(R.drawable.custom_checkbox);
                    cb_breakfast.setChecked(false);
                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });

        cb_laundry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){

                   otherprice += Integer.parseInt(txtlaundry.getText().toString());
                   total += Duration*Integer.parseInt(txtlaundry.getText().toString());
                        Drawable checkmark2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_check_24);
                        checkmark2.setTint(getResources().getColor(R.color.custom_color));
                        cb_laundry.setButtonDrawable(checkmark2);

                    }
                    else{
                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_laundry.setChecked(false);
                        cb_laundry.setButtonDrawable(R.drawable.custom_checkbox);
                    }





                } else {
                   otherprice -= Integer.parseInt(txtlaundry.getText().toString());
                   total -= Duration*Integer.parseInt(txtlaundry.getText().toString());

                    cb_laundry.setButtonDrawable(R.drawable.custom_checkbox);
                    cb_laundry.setChecked(false);


                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });

        cb_rentalcar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){

                     otherprice += Integer.parseInt(txtrentalcar.getText().toString());
                     total += Duration* Integer.parseInt(txtrentalcar.getText().toString());
                        Drawable checkmark3 = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_check_24);
                        checkmark3.setTint(getResources().getColor( R.color.custom_color));
                        cb_rentalcar.setButtonDrawable(checkmark3);

                    }
                    else{
                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_rentalcar.setChecked(false);
                        cb_rentalcar.setButtonDrawable(R.drawable.custom_checkbox);
                    }



                } else {
                  otherprice -=  Integer.parseInt(txtrentalcar.getText().toString());
                  total -= Duration* Integer.parseInt(txtrentalcar.getText().toString());

                    cb_rentalcar.setButtonDrawable(R.drawable.custom_checkbox);
                    cb_rentalcar.setChecked(false);
                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });

        cb_assistant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){

                       otherprice += Integer.parseInt(txtassistant.getText().toString());
                       total += Duration*Integer.parseInt(txtassistant.getText().toString());
                        Drawable checkmark4 = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_check_24);
                        checkmark4.setTint(getResources().getColor(R.color.custom_color));
                        cb_assistant.setButtonDrawable(checkmark4);

                    }
                    else{

                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_assistant.setChecked(false);
                        cb_assistant.setButtonDrawable(R.drawable.custom_checkbox);
                    }




                } else {
                  otherprice -= Integer.parseInt(txtassistant.getText().toString());
                  total -= Duration*Integer.parseInt(txtassistant.getText().toString());

                    cb_assistant.setButtonDrawable(R.drawable.custom_checkbox);
                    cb_assistant.setChecked(false);
                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });






        userAdapter.setOnRadioButtonSelectedListener(new UserAdapter.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int position) {
                RoomTypefir rdf = list.get(position);
                String selectText = rdf.getRoom();
                int totalamt = 0;

                if (position != -1){
                    totalamt = getSum();
                    txttotal.setText(String.valueOf(totalamt));

                }
                txt_roomtype.setText(selectText);
                Toast.makeText(getContext(), "Selected: " + selectText, Toast.LENGTH_SHORT).show();
            }
        });

        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();


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






                int selectedPosition = userAdapter.getSelectedposition();
                if (selectedPosition != -1) {
                    RoomTypefir rdf = list.get(selectedPosition);
                    String selectedradiotext = rdf.getRoom();
                    int roomprice = parseInt(txt_roomprice.getText().toString());
                    int netRoomPrice = roomprice*Duration;
                    String roomtax = txt_roomtax.getText().toString();
                    bundle2.putString("selectedradiotext", selectedradiotext);
                    bundle2.putInt("netRoomPrice", netRoomPrice);
                    bundle2.putString("roomtax", roomtax);
                } else {
                    Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                }

                String total = txttotal.getText().toString();
                String otherprice = txt_otherfacility.getText().toString();


                bundle2.putString("otherprice", otherprice);
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

    public int getSum() {
        int sum = 0;
        if(txt_roomprice!=null){
        int price1 = parseInt(txt_roomprice.getText().toString());
        int netroomprice = Duration*price1;

        int percent = parseInt(txt_roomtax.getText().toString());
        int price2 = (netroomprice*percent)/100;
        sum = netroomprice+price2;
        }
        return sum;
    }

    private void handleCheckBoxChecked(CheckBox checkBox) {
        int[] checkedPrices = {Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};
        int checkBoxPrice = getCheckBoxPrice(checkBox);
        checkedPrices[getCheckBoxIndex(checkBox)] = checkBoxPrice;
        otherprice += checkBoxPrice;
        updateTotal();
    }

    private void handleCheckBoxUnchecked(CheckBox checkBox) {

        int[] checkedPrices = {Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};
        int checkBoxPrice = getCheckBoxPrice(checkBox);
        checkedPrices[getCheckBoxIndex(checkBox)] = 0;
        otherprice -= checkBoxPrice;
        updateTotal();
    }

    private int getCheckBoxPrice(CheckBox checkBox) {
        if (checkBox == cb_breakfast) {
            return Integer.parseInt(txtbreakfast.getText().toString());
        } else if (checkBox == cb_laundry) {
            return Integer.parseInt(txtlaundry.getText().toString());
        } else if (checkBox == cb_rentalcar) {
            return Integer.parseInt(txtrentalcar.getText().toString());
        } else if (checkBox == cb_assistant) {
            return Integer.parseInt(txtassistant.getText().toString());
        }
        return 0;
    }

    private int getCheckBoxIndex(CheckBox checkBox) {
        if (checkBox == cb_breakfast) {
            return 0;
        } else if (checkBox == cb_laundry) {
            return 1;
        } else if (checkBox == cb_rentalcar) {
            return 2;
        } else if (checkBox == cb_assistant) {
            return 3;
        }
        return -1;
    }

    public void updateTotal(){
        int[] checkedPrices = {Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};

        int otherprice = getCheckedPricesSum();

        if (cb_breakfast.isChecked()) {
            total += checkedPrices[0] * Duration;
        }
        if (cb_laundry.isChecked()) {
            total += checkedPrices[1] * Duration;
        }
        if (cb_rentalcar.isChecked()) {
            total += checkedPrices[2] * Duration;
        }
        if (cb_assistant.isChecked()) {
            total += checkedPrices[3] * Duration;
        }

        bigtotal = roomprice + total;
        txt_otherfacility.setText(String.valueOf(otherprice));
        txttotal.setText(String.valueOf(bigtotal));






    }

    private int getCheckedPricesSum() {
        int[] checkedPrices = {Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};
        int sum = 0;
        for (int i = 0; i < checkedPrices.length; i++) {
            sum += checkedPrices[i];
        }
        return sum;
    }

}