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

public class PromoAdapter2 extends RecyclerView.Adapter<PromoAdapter2.PromoViewHolder> {
    List<PromoDatabase> promoList;

    public PromoAdapter2 (List<PromoDatabase>promoList){
        this.promoList=promoList;
    }


    @NonNull
    @Override
    public PromoAdapter2.PromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_row, parent, false);
        return new PromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter2.PromoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PromoDatabase pd = promoList.get(position);
        holder.txt_promoid.setText(pd.getPromoid());
        holder.txt_promocode.setText(pd.getPromocode());
        holder.txt_promodiscount.setText(pd.getPromodiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), PromoUpdateActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("Key", promoList.get(position).getKey());
                intent.putExtra("promoid", promoList.get(position).getPromoid());
                intent.putExtra("promocode", promoList.get(position).getPromocode());
                intent.putExtra("promodiscount", promoList.get(position).getPromodiscount());
                if(holder.itemView.getContext() instanceof PromoActivity){
                    ((PromoActivity)holder.itemView.getContext()).updateLauncher.launch(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }

    public class PromoViewHolder extends RecyclerView.ViewHolder{
        TextView txt_promoid, txt_promocode, txt_promodiscount;

        public PromoViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_promoid = itemView.findViewById(R.id.txt_promoid);
            txt_promocode = itemView.findViewById(R.id.txt_promocode);
            txt_promodiscount = itemView.findViewById(R.id.txt_promodiscount);
        }
    }
}
