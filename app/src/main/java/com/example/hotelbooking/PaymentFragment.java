package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    private EditText editcardno, editcvv, editcardname, editexpiry;
    Button btn_pay;







    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        editcardno = view.findViewById(R.id.editcardno);
        editcvv = view.findViewById(R.id.editcvv);
        editcardname = view.findViewById(R.id.editcardname);
        editexpiry = view.findViewById(R.id.editexpiry);
        btn_pay = view.findViewById(R.id.btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardno = editcardno.getText().toString();
                String cardcvv = editcvv.getText().toString();
                String cardname = editcardname.getText().toString();
                String cardexpiry = editexpiry.getText().toString();


                boolean checkcard = validCard(cardno, cardcvv, cardname, cardexpiry);
                if (checkcard == true){
                    Intent intent = new Intent(getActivity(),LastActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "EnterAll fields", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;

    }

    private boolean validCard(String cardno, String cardcvv, String cardname, String cardexpiry) {
        if (cardno.length()<16){
            editcardno.requestFocus();
            editcardno.setError("Enter valid card number");
            return false;
        } else  if (cardcvv.length()<3){
            editcvv.requestFocus();
            editcvv.setError("Enter Valid CVV");
            return false;
        } else if (cardname.length()==0){
            editcardname.requestFocus();
            editcardname.setError("Enter correct name On card");
            return false;
        } else if (cardexpiry.length()==0){
            editexpiry.requestFocus();
            editexpiry.setError("Enter Expiry date");
            return false;
        }
        return true;
    }


}