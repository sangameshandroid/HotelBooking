package com.example.hotelbooking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmdetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmdetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConfirmdetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmdetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmdetailFragment newInstance(String param1, String param2) {
        ConfirmdetailFragment fragment = new ConfirmdetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirmdetail, container, false);
        Bundle bundle = getArguments();
        String checkin=  bundle.getString("checkindate1");
        String checkout=bundle.getString("checkoutdate1");
        int rooms =bundle.getInt("room2");
        int adults =bundle.getInt("adult2");
        int childs =bundle.getInt("child2");
        String first = bundle.getString("firstname");
        String last = bundle.getString("lastname");
        String phone = bundle.getString("phone1");
        String email = bundle.getString("email1");
        String address = bundle.getString("address1");
        String selectedradio = bundle.getString("selectedradiobutton");
        String selecteddiscount = bundle.getString("selecteddiscount");
        String selectedcheckbox = bundle.getString("selectedcheckbox");
        String selectedextracharge = bundle.getString("selectedextracharge");
        String netamt = bundle.getString("netamount");
        String selectedradiotext = bundle.getString("selectedradiotext");


        return view;
    }
}