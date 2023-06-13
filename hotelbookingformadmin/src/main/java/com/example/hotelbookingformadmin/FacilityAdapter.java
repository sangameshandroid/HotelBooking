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


public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder> {
    List<ExtraFacility> facilityList;

    public FacilityAdapter(List<ExtraFacility> facilityList){
        this.facilityList=facilityList;
    }

    @NonNull
    @Override
    public FacilityAdapter.FacilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facility_row, parent, false);
        return new FacilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityAdapter.FacilityViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ExtraFacility ef = facilityList.get(position);
        holder.txt_extrafacilty.setText(ef.getExtrafacility());
        holder.txt_extracharge.setText(ef.getExtracharge());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), FacilityUpdateActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("Key", facilityList.get(position).getKey());
                intent.putExtra("extrafacility", facilityList.get(position).getExtrafacility());
                intent.putExtra("extracharge", facilityList.get(position).getExtracharge());
                if(holder.itemView.getContext() instanceof ExtraFaciltyActivity){
                    ((ExtraFaciltyActivity)holder.itemView.getContext()).updateLauncher.launch(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return facilityList.size();
    }

    public class FacilityViewHolder extends RecyclerView.ViewHolder{
        TextView txt_extrafacilty, txt_extracharge;

        public FacilityViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_extrafacilty = itemView.findViewById(R.id.txt_Extrafacilty);
            txt_extracharge = itemView.findViewById(R.id.txt_extracharge);
        }
    }
}
