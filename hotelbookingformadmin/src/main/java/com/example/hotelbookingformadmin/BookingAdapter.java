package com.example.hotelbookingformadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private List<UserData2> userData2;



    public BookingAdapter(List<UserData2> userData2) {
        this.userData2 = userData2;
    }
    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdata, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        UserData2 ud2 = userData2.get(position);
        holder.txt_Fname.setText(ud2.getFname());
        holder.txt_Lname.setText(ud2.getLname());
        holder.txt_Email.setText(ud2.getEmail());
        holder.txt_Phone.setText(ud2.getMob());
        holder.txt_Address.setText(ud2.getAddress());
        holder.txt_rooms.setText(ud2.getRoom());
        holder.txt_checkindate.setText(ud2.getCheckin());
        holder.txt_checkoutdate.setText(ud2.getCheckout());
        holder.txt_netpay.setText(ud2.getNetpay());

    }

    @Override
    public int getItemCount() {
        return userData2.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder{
        TextView txt_rooms, txt_checkindate, txt_checkoutdate, txt_netpay, txt_Fname, txt_Lname, txt_Email, txt_Phone, txt_Address;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_rooms = itemView.findViewById(R.id.txt_rooms);
            txt_checkindate = itemView.findViewById(R.id.txt_checkindate);
            txt_checkoutdate = itemView.findViewById(R.id.txt_checkoutdate);
            txt_netpay = itemView.findViewById(R.id.txt_netpay);
            txt_Fname = itemView.findViewById(R.id.txt_Fname);
            txt_Lname = itemView.findViewById(R.id.txt_Lname);
            txt_Email = itemView.findViewById(R.id.txt_Email);
            txt_Phone = itemView.findViewById(R.id.txt_Phone);
            txt_Address = itemView.findViewById(R.id.txt_Address);

        }
    }
}
