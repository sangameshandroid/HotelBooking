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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExtraFaciltyActivity extends AppCompatActivity {
    private EditText edit_extrafacility, edit_extracharge;
    private Button btn_add;
    private DatabaseReference ref;
    private List<ExtraFacility> facilityList;
    private FacilityAdapter facilityAdapter;
    private ExtraFacility ef;
    private RecyclerView facilityrecycler;
    ActivityResultLauncher<Intent> updateLauncher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_facilty);
        edit_extrafacility = findViewById(R.id.edit_extrafacility);
        edit_extracharge = findViewById(R.id.edit_extracharge);
        facilityrecycler = findViewById(R.id.facilityrecycler);
        btn_add = findViewById(R.id.btn_add);

        ref = FirebaseDatabase.getInstance().getReference("ExtraFacility");
        facilityList = new ArrayList<>();
        facilityAdapter = new FacilityAdapter(facilityList);

        updateLauncher = registerForActivityResult(new ActivityResultContracts.
                StartActivityForResult(),
                result -> {
            if(result.getResultCode()== Activity.RESULT_OK){
                String Key = result.getData().getStringExtra("Key");
                String extrafacility = result.getData().getStringExtra("Extrafacility");
                String extracharge = result.getData().getStringExtra("Extracharge");

                for(int i=0; i<facilityList.size();i++){
                    if(facilityList.get(i).getKey().equals(Key)){
                        facilityList.get(i).setExtrafacility(extrafacility);
                        facilityList.get(i).setExtracharge(extracharge);
                        facilityAdapter.notifyItemChanged(i);
                        break;
                    }
                }
            }
            else if(result.getResultCode()==7){
                Intent data = result.getData();
                if(data!=null && data.hasExtra("position")){
                    int pos = data.getIntExtra("position", -1);
                            if(pos!=-1){
                                facilityList.remove(pos);
                                facilityAdapter.notifyItemRemoved(pos);
                                Toast.makeText(this, "Facility has been deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                }
            }

                }
        );





        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()
                     ) { ef = snap.getValue(ExtraFacility.class);

                        ef.setKey(snap.getKey());
                        facilityList.add(ef);



                }
                facilityAdapter = new FacilityAdapter(facilityList);
                facilityrecycler.setAdapter(facilityAdapter);
                facilityrecycler.setLayoutManager(new LinearLayoutManager(ExtraFaciltyActivity.this));
                facilityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extrafacilty = edit_extrafacility.getText().toString();
                String extracharge = edit_extracharge.getText().toString();
                ef = new ExtraFacility(extrafacilty, extracharge);
                ref.push().setValue(ef);
                facilityAdapter.notifyDataSetChanged();

                edit_extrafacility.setText("");
                edit_extracharge.setText("");


            }
        });
    }
}