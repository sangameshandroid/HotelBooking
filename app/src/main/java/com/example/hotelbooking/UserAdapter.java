package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<RoomTypefir> list;
    private SelectRoomFragment fragment;
    private int selectedposition = -1;
    private OnRadioButtonSelectedListener listener;
    public int getSelectedposition() {
        return selectedposition;
    }

    public UserAdapter(Context context, SelectRoomFragment fragment) {
        this.context = context;
        this.fragment = fragment;
        list = new ArrayList<>();

    }

    public void setData(List<RoomTypefir> list) {
        this.list = list;
        notifyDataSetChanged();

    }


    public interface OnRadioButtonSelectedListener {
        void onRadioButtonSelected(int position);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RoomTypefir rdf = list.get(position);
        holder.radio_room.setText(rdf.getRoom());
        holder.txt_cost.setText(rdf.getCost());
        holder.txt_tax.setText(rdf.getTax());
        holder.radio_room.setChecked(position == selectedposition);

        holder.radio_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Rcost = holder.txt_cost.getText().toString();
                String Rtax = holder.txt_tax.getText().toString();
                if (fragment != null && fragment.isAdded() && fragment.getActivity() != null) {
                    fragment.setTextToSecondTextView(Rcost, Rtax);
                }
                selectedposition = position;
                listener.onRadioButtonSelected(position);
                notifyDataSetChanged();


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_cost, txt_tax;
        private RadioGroup radioGroup;
        private RadioButton radio_room;
        private SelectRoomFragment fragment;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_cost = itemView.findViewById(R.id.txt_cost);
            txt_tax = itemView.findViewById(R.id.txt_tax);
            radioGroup = itemView.findViewById(R.id.radiogroup);
            radio_room = itemView.findViewById(R.id.radio_room);
            itemView.setBackgroundResource(R.drawable.cardview_background);


        }
    }

    public void setOnRadioButtonSelectedListener(OnRadioButtonSelectedListener listener) {
        this.listener = listener;


    }
}
