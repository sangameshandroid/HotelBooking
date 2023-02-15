package com.example.hotelbookingformadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private List<UserData> userData;



    public BookingAdapter(List<UserData> userData) {
        this.userData = userData;
    }
    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdata, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        UserData ud = userData.get(position);
        holder.txt_rooms.setText(ud.getRoomtype());
        holder.txt_checkindate.setText(ud.getCheckin());
        holder.txt_checkoutdate.setText(ud.getChechout());
        holder.txt_netpay.setText(ud.getNetpayable());

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder{
        TextView txt_rooms, txt_checkindate, txt_checkoutdate, txt_netpay;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_rooms = itemView.findViewById(R.id.txt_rooms);
            txt_checkindate = itemView.findViewById(R.id.txt_checkindate);
            txt_checkoutdate = itemView.findViewById(R.id.txt_checkoutdate);
            txt_netpay = itemView.findViewById(R.id.txt_netpay);

        }
    }
}
