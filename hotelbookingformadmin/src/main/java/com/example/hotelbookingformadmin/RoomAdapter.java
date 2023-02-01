package com.example.hotelbookingformadmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    private Context context;
    private List<RoomData> roomDataList;
    private AdapterListener adapterListener;

    public RoomAdapter(Context contex, AdapterListener listener) {
        this.context = context;
        roomDataList = new ArrayList<>();
        this.adapterListener=listener;
    }
    public void addRoom(RoomData roomData){
        roomDataList.add(roomData);
        notifyDataSetChanged();
    }
    public void removeRoom(int position){
        roomDataList.remove(position);
        notifyDataSetChanged();
    }
    public void clearData(){
        roomDataList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RoomData roomData = roomDataList.get(position);
        holder.txtroom.setText(roomData.getRoom());
        holder.txtcost.setText(roomData.getCost());
        holder.txttax.setText(roomData.getTax());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.OnUpdate(roomData);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.OnDelete(roomData.getId(), position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return roomDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtroom, txtcost, txttax;
        private ImageView update, delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtroom = itemView.findViewById(R.id.txtroom);
            txtcost = itemView.findViewById(R.id.txtcost);
            txttax= itemView.findViewById(R.id.txttax);
            update= itemView.findViewById(R.id.update);
            delete= itemView.findViewById(R.id.delete);

        }
    }
}
