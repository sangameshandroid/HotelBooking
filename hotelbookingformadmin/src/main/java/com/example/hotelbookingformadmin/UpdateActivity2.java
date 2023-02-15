package com.example.hotelbookingformadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity2 extends AppCompatActivity {
   private EditText edpromoid2, edpromocode2, edpromodiscount2;
    private Button btnupdatepromo;
    //private PromoData promoData;
    //private PromoDB promoDB;
    //private PromoDao promoDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);

        //promoDB = PromoDB.getInstance(this);
        //promoDao = promoDB.getPromoDao();

        edpromoid2 = findViewById(R.id.editid2);
        edpromocode2 = findViewById(R.id.editcode2);
        edpromodiscount2 = findViewById(R.id.editdiscount2);
        btnupdatepromo = findViewById(R.id.btnupdatepromo);
        //promoData = (PromoData) getIntent().getSerializableExtra("mod");

        //edpromoid2.setText(promoData.getPromoid());
        //edpromocode2.setText(promoData.getPromocode());
        //edpromodiscount2.setText(promoData.getPromodiscount());

        btnupdatepromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PromoData promoModel = new PromoData(promoData.getId(), edpromoid2.getText().toString(),edpromocode2.getText().toString(), edpromodiscount2.getText().toString());
                //promoDao.update(promoModel);
                //finish();
            }
        });
    }
}