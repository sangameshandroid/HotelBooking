package com.example.hotelbookingformadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class NameFilterAdapter extends RecyclerView.Adapter<NameFilterAdapter.NameViewHolder> {
    private List<UserData2> userData2;


    public NameFilterAdapter(List<UserData2> userdata2){
        this.userData2=userdata2;
    }
    @NonNull
    @Override
    public NameFilterAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.namefilter_row, parent, false);
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameFilterAdapter.NameViewHolder holder, int position) {
        UserData2 ud2 = userData2.get(position);
        if (ud2 != null) {
            holder.txt_firstname.setText(ud2.getFname() != null ? ud2.getFname() : "");
            holder.txt_lastname.setText(ud2.getLname() != null ? ud2.getLname() : "");
            holder.txt_email.setText(ud2.getEmail() != null ? ud2.getEmail() : "");
            holder.txt_phone.setText(ud2.getMob() != null ? ud2.getMob() : "");
            holder.txt_address.setText(ud2.getAddress() != null ? ud2.getAddress() : "");
            holder.txt_rooms2.setText(ud2.getRoom() != null ? ud2.getRoom() : "");
            holder.txt_checkindate2.setText(ud2.getCheckin() != null ? ud2.getCheckin() : "");
            holder.txt_checkoutdate2.setText(ud2.getCheckout() != null ? ud2.getCheckout() : "");
            holder.txt_netpay2.setText(ud2.getNetpay() != null ? ud2.getNetpay() : "");
        }
    }

    @Override
    public int getItemCount() {
        return userData2.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder{
        TextView txt_firstname, txt_lastname, txt_email, txt_phone, txt_address, txt_rooms2, txt_checkindate2, txt_checkoutdate2, txt_netpay2;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_firstname = itemView.findViewById(R.id.txt_firstname);
            txt_lastname = itemView.findViewById(R.id.txt_lastname);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_phone = itemView.findViewById(R.id.txt_phone);
            txt_address = itemView.findViewById(R.id.txt_address);
            txt_rooms2 = itemView.findViewById(R.id.txt_rooms2);
            txt_checkindate2 = itemView.findViewById(R.id.txt_checkindate2);
            txt_checkoutdate2 = itemView.findViewById(R.id.txt_checkoutdate2);
            txt_netpay2 = itemView.findViewById(R.id.txt_netpay2);
        }
    }
}
