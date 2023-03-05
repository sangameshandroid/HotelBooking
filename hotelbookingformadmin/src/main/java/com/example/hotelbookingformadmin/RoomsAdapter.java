package com.example.hotelbookingformadmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder> {
private List<RoomTypefir> dataList;






public RoomsAdapter(List<RoomTypefir> dataList){
this.dataList=dataList;
}


    @NonNull
    @Override
    public RoomsAdapter.RoomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_holder, parent, false);

        return new RoomsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsAdapter.RoomsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        RoomTypefir rdf = dataList.get(position);
        holder.txt_Room.setText(rdf.getRoom());
        holder.txt_Cost.setText(rdf.getCost());
        holder.txt_Tax.setText(rdf.getTax());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent = new Intent(holder.itemView.getContext(), UpdateRoomActivity.class);
                intent.putExtra("psition",position);
                intent.putExtra("key", dataList.get(position).getKey());
                intent.putExtra("room", dataList.get(position).getRoom());
                intent.putExtra("cost", dataList.get(position).getCost());
                intent.putExtra("tax", dataList.get(position).getTax());
                if(holder.itemView.getContext() instanceof RoomManagement){
                  ((RoomManagement) holder.itemView.getContext() ).updateLauncher.launch(intent);

                 }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class RoomsViewHolder extends RecyclerView.ViewHolder{
        TextView txt_Room, txt_Cost, txt_Tax;


        public RoomsViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_Room = itemView.findViewById(R.id.txt_Room);
            txt_Cost = itemView.findViewById(R.id.txt_Cost);
            txt_Tax = itemView.findViewById(R.id.txt_Tax);





        }


    }
}
