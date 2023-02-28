package com.example.hotelbooking;

import static com.example.hotelbooking.R.id.framelayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment=null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private DateFragment dateFragment = new DateFragment();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        tabLayout=findViewById(R.id.tablayout);
        frameLayout=findViewById(R.id.framelayout);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, dateFragment);
        fragmentTransaction.commit();








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







            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.getTabAt(tab.getPosition()).setCustomView(null);


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.getTabAt(0).select();

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
