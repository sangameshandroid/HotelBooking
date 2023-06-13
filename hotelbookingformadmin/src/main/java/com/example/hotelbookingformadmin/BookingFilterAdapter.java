package com.example.hotelbookingformadmin;

import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingFilterAdapter extends RecyclerView.Adapter<BookingFilterAdapter.RoomViewHolder> {
    List<UserData2> userData2;

    public BookingFilterAdapter(List<UserData2> userData2){
        this.userData2=userData2;

    }
    @NonNull
    @Override
    public BookingFilterAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roomfilter_layout, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingFilterAdapter.RoomViewHolder holder, int position) {
        UserData2 ud2 = userData2.get(position);
        holder.txt_fname.setText(ud2.getFname());
        holder.txt_lname.setText(ud2.getLname());
        holder.txt_email.setText(ud2.getEmail());
        holder.txt_phone.setText(ud2.getMob());
        holder.txt_address.setText(ud2.getAddress());
        holder.txt_rooms1.setText(ud2.getRoom());
        holder.txt_checkindate1.setText(ud2.getCheckin());
        holder.txt_checkoutdate1.setText(ud2.getCheckout());
        holder.txt_netpay1.setText(ud2.getNetpay());

    }

    @Override
    public int getItemCount() {
        return userData2.size();
    }
     public class RoomViewHolder extends RecyclerView.ViewHolder{
        TextView txt_rooms1, txt_checkindate1, txt_checkoutdate1, txt_netpay1, txt_fname, txt_lname, txt_email, txt_phone, txt_address;

         public RoomViewHolder(@NonNull View itemView) {
             super(itemView);
             txt_rooms1 = itemView.findViewById(R.id.txt_rooms1);
             txt_checkindate1 = itemView.findViewById(R.id.txt_checkindate1);
             txt_checkoutdate1 = itemView.findViewById(R.id.txt_checkoutdate1);
             txt_fname = itemView.findViewById(R.id.txt_fname);
             txt_lname = itemView.findViewById(R.id.txt_lname);
             txt_email = itemView.findViewById(R.id.txt_email);
             txt_phone = itemView.findViewById(R.id.txt_phone);
             txt_address = itemView.findViewById(R.id.txt_address);
             txt_netpay1 = itemView.findViewById(R.id.txt_netpay1);
         }
     }
}
