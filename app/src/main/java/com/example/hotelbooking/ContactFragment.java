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
import android.widget.Toast;

public class ContactFragment extends Fragment {
    EditText editfirstname, editlastname, editemail, editaddress, editphone, editpostcode;
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
        editpostcode = view.findViewById(R.id.editpostcode);



        btn_selectroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sendDataBundle = new Bundle();
                Bundle bundle = getArguments();
                String fname = editfirstname.getText().toString();
                String lname = editlastname.getText().toString();
                String email = editemail.getText().toString();
                String address = editaddress.getText().toString();
                String phone = editphone.getText().toString();
                String postcode = editpostcode.getText().toString();




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




                    sendDataBundle.putString("fname", fname);
                    sendDataBundle.putString("lname", lname);
                    sendDataBundle.putString("email", email);
                    sendDataBundle.putString("address", address);
                    sendDataBundle.putString("phone", phone);

                    boolean check = validinfo(fname,lname, email,address,phone,postcode);
                    if (check == true){
                        SelectRoomFragment selectRoomFragment = new SelectRoomFragment();
                        selectRoomFragment.setArguments(sendDataBundle);
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.framelayout, selectRoomFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    } else {
                        Toast.makeText(getContext(), "Enter all Field", Toast.LENGTH_SHORT).show();
                    }






            }
        });

        return view;
    }

    private boolean validinfo(String fname, String lname, String email, String address, String phone, String postcode) {
        if (fname.length()==0){
            editfirstname.requestFocus();
            editfirstname.setError("Enter in the field");
            return false;
        } else if (lname.length()==0){
            editlastname.requestFocus();
            editlastname.setError("Enter in the field");
            return false;
        } else if (email.length()==0){
            editemail.requestFocus();
            editemail.setError("Enter in the field");
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            editemail.requestFocus();
            editemail.setError("Enter Valid Email");
            return  false;
        } else if (address.length()==0){
            editaddress.requestFocus();
            editaddress.setError("Enter in the Field");
            return false;
        } else if (postcode.length()<6){
            editpostcode.requestFocus();
            editpostcode.setError("Enter Valid Email");
            return false;
        } else if (phone.length()<10){
            editphone.requestFocus();
            editphone.setError("Enter Valid Mobile Number");
            return false;
        }
        return true;
    }


}