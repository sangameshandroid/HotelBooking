package com.example.hotelbooking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.zip.Inflater;

public class ContactFragment extends Fragment {
    EditText editfirstname, editlastname, editemail, editaddress, editphone;
    Button btn_selectroom;

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
        btn_selectroom = view.findViewById(R.id.btn_selectrooms);



        btn_selectroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sendDataBundle = new Bundle();
                Bundle bundle = getArguments();
                if(bundle!=null){
                   String checkin = bundle.getString("checkin");
                    String checkout=bundle.getString("checkout");
                   int room= bundle.getInt("rooms");
                    int adult=bundle.getInt("adults");
                    int child=bundle.getInt("childs");
                    sendDataBundle.putString("checkindate", checkin);
                    sendDataBundle.putString("checkoutdate", checkout);
                    sendDataBundle.putInt("room1", room);
                    sendDataBundle.putInt("adult1", adult);
                    sendDataBundle.putInt("child1", child);
                }

                String fname = editfirstname.getText().toString();
                String lname = editlastname.getText().toString();
                String email = editemail.getText().toString();
                String address = editaddress.getText().toString();
                String phone = editphone.getText().toString();

                sendDataBundle.putString("fname", fname);
                sendDataBundle.putString("lname", lname);
                sendDataBundle.putString("email", email);
                sendDataBundle.putString("address", address);
                sendDataBundle.putString("phone", phone);

                SelectRoomFragment selectRoomFragment = new SelectRoomFragment();
                selectRoomFragment.setArguments(sendDataBundle);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, selectRoomFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}