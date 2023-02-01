package com.example.hotelbookingformadmin;

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

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.MyViewHolder> {
    private Context context;
    private List<PromoData> promoList;
    private PromoAdapterListener promoAdapterListener;
    public PromoAdapter(Context context, PromoAdapterListener listener){
        this.context=context;
        this.promoAdapterListener=listener;
        promoList = new ArrayList<>();

    }
    public void addPromo(PromoData promoData){
        promoList.add(promoData);
        notifyDataSetChanged();
    }

    public void removePromo(int position){
        promoList.remove(position);
        notifyDataSetChanged();
    }

    public void clearPromo(){
        promoList.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PromoData promoData = promoList.get(position);
        holder.txtpromoid.setText(promoData.getPromoid());
        holder.txtpromocode.setText(promoData.getPromocode());
        holder.txtpromodiscount.setText(promoData.getPromodiscount());
        holder.imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promoAdapterListener.PromoUpdate(promoData);

            }
        });

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promoAdapterListener.PromoDelete(promoData.getId(), position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtpromoid, txtpromocode, txtpromodiscount;
        private ImageView imgupdate, imgdelete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtpromoid = itemView.findViewById(R.id.txtpromoid);
            txtpromocode = itemView.findViewById(R.id.txtpromocode);
            txtpromodiscount = itemView.findViewById(R.id.txtpromodiscount);
            imgupdate = itemView.findViewById(R.id.promoupdate);
            imgdelete = itemView.findViewById(R.id.promodelete);
        }
    }
}
