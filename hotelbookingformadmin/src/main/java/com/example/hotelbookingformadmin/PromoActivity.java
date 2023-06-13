package com.example.hotelbookingformadmin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PromoActivity extends AppCompatActivity  {
    EditText edpromoid, edpromocode, edpromodiscount;
    Button btnaddpromo;
    RecyclerView promorecycler;
    private DatabaseReference dref;
    private List<PromoDatabase> promoList;
    private PromoAdapter2 promoAdapter2;
    private PromoDatabase pd;
    ActivityResultLauncher<Intent> updateLauncher;
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

        promoList = new ArrayList<>();
        promoAdapter2 = new PromoAdapter2(promoList);


       /* promoAdapter = new PromoAdapter(this, this);
        promorecycler.setAdapter(promoAdapter);
        promorecycler.setLayoutManager(new LinearLayoutManager(this));*/

        dref = FirebaseDatabase.getInstance().getReference("PromoDatabase");


        updateLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Get the updated data from the Intent
                        String key = result.getData().getStringExtra("Key");


                        String promoid = result.getData().getStringExtra("promoid");
                        String promocode = result.getData().getStringExtra("promocode");
                        String promodiscount = result.getData().getStringExtra("promodiscount");

                        // Update the data in your RecyclerView adapter
                        for (int i = 0; i < promoList.size(); i++) {
                            if (promoList.get(i).getKey().equals(key)) {
                                promoList.get(i).setPromoid(promoid);
                                promoList.get(i).setPromocode(promocode);
                                promoList.get(i).setPromodiscount(promodiscount);
                                promoAdapter2.notifyItemChanged(i);
                                break;
                            }
                        }
                    }
                    else if(result.getResultCode()==6){
                        Intent data = result.getData();
                        if(data!=null && data.hasExtra("position")){
                            int pos = data.getIntExtra("position", -1);
                            if(pos!=-1){
                                promoList.remove(pos);
                                promoAdapter2.notifyItemRemoved(pos);
                                Toast.makeText(this, "Room has been deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
        promorecycler.setAdapter(promoAdapter2);


        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                promoList = new ArrayList<>();
                for (DataSnapshot dsnap:snapshot.getChildren()
                     ) {
                    pd = dsnap.getValue(PromoDatabase.class);

                     pd.setKey(dsnap.getKey());
                     promoList.add(pd);


                }
                promoAdapter2 = new PromoAdapter2(promoList);
                promorecycler.setAdapter(promoAdapter2);
                promorecycler.setLayoutManager(new LinearLayoutManager(PromoActivity.this));
                promoAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnaddpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String promoid = edpromoid.getText().toString();
                String promocode = edpromocode.getText().toString();
                String promodiscount = edpromodiscount.getText().toString();
                PromoDatabase pd = new PromoDatabase(promoid, promocode, promodiscount);
                dref.push().setValue(pd);
                promoAdapter2.notifyDataSetChanged();

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