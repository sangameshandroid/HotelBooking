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
    List<UserData> userData;

    public BookingFilterAdapter(List<UserData> userData){
        this.userData=userData;

    }
    @NonNull
    @Override
    public BookingFilterAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roomfilter_layout, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingFilterAdapter.RoomViewHolder holder, int position) {
        UserData ud1 = userData.get(position);
        holder.txt_rooms1.setText(ud1.getRoomtype());
        holder.txt_checkindate1.setText(ud1.getCheckin());
        holder.txt_checkoutdate1.setText(ud1.getChechout());
        holder.txt_netpay1.setText(ud1.getNetpayable());

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
     public class RoomViewHolder extends RecyclerView.ViewHolder{
        TextView txt_rooms1, txt_checkindate1, txt_checkoutdate1, txt_netpay1;

         public RoomViewHolder(@NonNull View itemView) {
             super(itemView);
             txt_rooms1 = itemView.findViewById(R.id.txt_rooms1);
             txt_checkindate1 = itemView.findViewById(R.id.txt_checkindate1);
             txt_checkoutdate1 = itemView.findViewById(R.id.txt_checkoutdate1);
             txt_netpay1 = itemView.findViewById(R.id.txt_netpay1);
         }
     }
}
