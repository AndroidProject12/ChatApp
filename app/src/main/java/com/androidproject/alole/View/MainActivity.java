package com.androidproject.alole.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidproject.alole.Fragment.KullanicilarFragment;
import com.androidproject.alole.Fragment.MesajlarFragment;
import com.androidproject.alole.Fragment.ProfilFragment;
import com.androidproject.alole.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomView;
    private KullanicilarFragment kullanicilarFragment;
    private MesajlarFragment mesajlarFragment;
    private ProfilFragment profilFragment;
    private FragmentTransaction transaction;

    private void init(){
        mBottomView = (BottomNavigationView)findViewById(R.id.main_activity_bottomView);

        kullanicilarFragment = new KullanicilarFragment();
        mesajlarFragment = new MesajlarFragment();
        profilFragment = new ProfilFragment();

        fragmentiAyarla(kullanicilarFragment);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_ic_people:
                        fragmentiAyarla(kullanicilarFragment);
                        return true;

                    case R.id.bottom_nav_ic_message:
                        fragmentiAyarla(mesajlarFragment);
                        return true;

                    case R.id.bottom_nav_ic_profile:
                        fragmentiAyarla(profilFragment);
                        return true;

                    default:
                        return false;
                }

            }
        });
    }
    private void fragmentiAyarla(Fragment fragment){
       transaction = getSupportFragmentManager().beginTransaction();
       transaction.replace(R.id.main_activity_frameLayout,fragment);
       transaction.commit();
    }
}