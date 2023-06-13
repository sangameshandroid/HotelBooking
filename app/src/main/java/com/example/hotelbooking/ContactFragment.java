package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class ContactFragment extends Fragment {
    EditText editfirstname, editlastname, editemail, editaddress, editphone, editpostcode;
    Button btn_confirmdetail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_contact, container, false);
        editfirstname = view.findViewById(R.id.editfirstname);
        editlastname = view.findViewById(R.id.editlastname);
        editemail = view.findViewById(R.id.editemail);
        editaddress = view.findViewById(R.id.editaddress);
        editphone = view.findViewById(R.id.editphone);
        btn_confirmdetail = view.findViewById(R.id.btn_confirmdetail);
        editpostcode = view.findViewById(R.id.editpostcode);

        Bundle bundle = getArguments();



        btn_confirmdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sendDataBundle = new Bundle();

                String fname = editfirstname.getText().toString();
                String lname = editlastname.getText().toString();
                String email = editemail.getText().toString();
                String address = editaddress.getText().toString();
                String phone = editphone.getText().toString();
                String postcode = editpostcode.getText().toString();






                    if(bundle!=null){
                        String checkin = bundle.getString("checkindate1");
                        String checkout=bundle.getString("checkoutdate1");
                        int room= bundle.getInt("room1",0);
                        int adult=bundle.getInt("adult1",0);
                        int child=bundle.getInt("child1",0);
                        int roomPrice = bundle.getInt("netRoomPrice", 0);
                        String selectedradio = bundle.getString("selectedradiotext");

                        String total = bundle.getString("total");
                        String roomTax = bundle.getString("roomtax");
                        String extracharge = bundle.getString("otherprice");

                        if(checkin==null && checkout==null && room==0 && adult==0 && child==0){
                            Toast.makeText(getActivity(), "value is null", Toast.LENGTH_SHORT).show();
                        }
                        sendDataBundle.putString("extracharge", extracharge);
                        sendDataBundle.putString("roomTax", roomTax);
                        sendDataBundle.putInt("roomPrice", roomPrice);
                        sendDataBundle.putString("selectedradio", selectedradio);
                        sendDataBundle.putString("total", total);
                        sendDataBundle.putString("checkindate2", checkin);
                        sendDataBundle.putString("checkoutdate2", checkout);
                        sendDataBundle.putInt("room2", room);
                        sendDataBundle.putInt("adult2", adult);
                        sendDataBundle.putInt("child2", child);
                    }




                    sendDataBundle.putString("fname", fname);
                    sendDataBundle.putString("lname", lname);
                    sendDataBundle.putString("email", email);
                    sendDataBundle.putString("address", address);
                    sendDataBundle.putString("phone", phone);



                        ConfirmdetailFragment confirmdetailFragment = new ConfirmdetailFragment();
                        confirmdetailFragment.setArguments(sendDataBundle);

                TabLayout tabLayout = getActivity().findViewById(R.id.tablayout);
                tabLayout.getTabAt(3).select();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.framelayout, confirmdetailFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();











            }
        });

        return view;
    }






}