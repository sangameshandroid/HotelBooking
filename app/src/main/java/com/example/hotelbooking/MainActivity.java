package com.example.hotelbooking;

import static com.example.hotelbooking.R.id.framelayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment=null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TextView textView1;
    private DateFragment dateFragment = new DateFragment();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        tabLayout=findViewById(R.id.tablayout);
        frameLayout=findViewById(R.id.framelayout);
        textView1 = findViewById(R.id.textView1);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, dateFragment);
        fragmentTransaction.commit();

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity2.class);
                startActivity(intent);
            }
        });








        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                     int position = tab.getPosition();

                     Objects.requireNonNull(tabLayout.getTabAt(tab.getPosition())).setCustomView(R.layout.custom_tab_selected);


                    String tag = "android:switcher:" + R.id.framelayout + ":" + position;
                     fragment = getSupportFragmentManager().findFragmentByTag(tag);
                     if(fragment==null){
                         switch (position){
                             case 0 :
                                 fragment= dateFragment;
                                 break;
                             case 1 :
                                 fragment = new SelectRoomFragment();
                                 break;
                             case 2 :
                                 fragment = new ContactFragment();
                                 break;
                             case 3 :
                                 fragment = new ConfirmdetailFragment();
                                 break;
                         }
                         getSupportFragmentManager().beginTransaction().replace(framelayout, fragment, tag).commit();
                     }
                     View tabview = Objects.requireNonNull(tab.getCustomView());
                   tabview.setPadding(8, 8, 8, 8);






            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.getTabAt(tab.getPosition()).setCustomView(null);


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.getTabAt(0).setCustomView(R.layout.custom_tab_selected);

    }





    /*@Override
    public void onDataPass(String data) {
        int position = tabLayout.getSelectedTabPosition();
        String tag = "android:switcher:" + R.id.framelayout + ":" + 3;
        paymentFragment = new PaymentFragment();
        paymentFragment = (PaymentFragment) getSupportFragmentManager().findFragmentB

        if (paymentFragment!=null && paymentFragment.isVisible()) {
            paymentFragment.updateData(data);
        }else{
            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, paymentFragment).commit();
            paymentFragment.updateData(data);
        }
    }*/
    }
