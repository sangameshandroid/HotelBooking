package com.example.hotelbookingformadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class PromoActivity extends AppCompatActivity  {
    EditText edpromoid, edpromocode, edpromodiscount;
    Button btnaddpromo;
    RecyclerView promorecycler;
    /*private PromoDB promoDB;
    private PromoDao promoDao;
    private PromoAdapter promoAdapter;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

       /* promoDB = PromoDB.getInstance(this);
        promoDao = promoDB.getPromoDao();

        promoAdapter = new PromoAdapter(this, this);*/



        edpromoid = findViewById(R.id.editid);
        edpromocode = findViewById(R.id.editcode);
        edpromodiscount = findViewById(R.id.editdiscount);
        btnaddpromo = findViewById(R.id.btnaddpromo);
        promorecycler = findViewById(R.id.promorecycler);

       /* promoAdapter = new PromoAdapter(this, this);
        promorecycler.setAdapter(promoAdapter);
        promorecycler.setLayoutManager(new LinearLayoutManager(this));*/


        btnaddpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String promoid = edpromoid.getText().toString();
                String promocode = edpromocode.getText().toString();
                String promodiscount = edpromodiscount.getText().toString();
                /*PromoData promoData = new PromoData(0,promoid,promocode,promodiscount);
                promoAdapter.addPromo(promoData);
                promoDao.insert(promoData);*/
                edpromoid.setText("");
                edpromocode.setText("");
                edpromodiscount.setText("");
                Toast.makeText(PromoActivity.this, "Promo code added successfully", Toast.LENGTH_SHORT).show();


            }
        });


    }
   /* private void fetchPromoData(){
        promoAdapter.clearPromo();
        List<PromoData> promoList = promoDao.getPromoData();
        for(int i=0;i<promoList.size();i++){
            PromoData promoData = promoList.get(i);
            promoAdapter.addPromo(promoData);
        }
    }*/

   /* @Override
    public void PromoUpdate(PromoData promoData) {
        Intent intent = new Intent(this, UpdateActivity2.class);
        intent.putExtra("mod", promoData);
        startActivity(intent);


    }

    @Override
    public void PromoDelete(int id, int pos) {
        promoDao.delete(id);
        promoAdapter.removePromo(pos);


    }
    protected void onResume() {
        super.onResume();
        fetchPromoData();
    }*/
}