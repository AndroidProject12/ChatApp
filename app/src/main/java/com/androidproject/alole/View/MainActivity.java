<<<<<<< HEAD
package com.androidproject.alole.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.androidproject.alole.Adapter.MesajIstekleriAdapter;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
import com.androidproject.alole.Fragment.KullanicilarFragment;
import com.androidproject.alole.Fragment.MesajlarFragment;
import com.androidproject.alole.Fragment.ProfilFragment;
import com.androidproject.alole.Model.Kullanici;
import com.androidproject.alole.Model.MesajIstegi;
import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
=======
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Object> mData;
    private BottomNavigationView mBottomView;
    private KullanicilarFragment kullanicilarFragment;
    private MesajlarFragment mesajlarFragment;
    private ProfilFragment profilFragment;
    private FragmentTransaction transaction;
    private Toolbar mToolbar;
    private RelativeLayout mRelaNotif;
    private TextView txtBildirimSayisi;

    private FirebaseFirestore mFireStore;
    private Query mQuery;
    private FirebaseUser mUser;
    private MesajIstegi mMesajIstegi;
    private ArrayList<MesajIstegi> mesajIstegiList;

    private Dialog mesajIstekleriDialog;
    private ImageView mesajIstekleriKapat;
    private RecyclerView mesajIstekleriRecyclerView;
    private MesajIstekleriAdapter mAdapter;

    private DocumentReference mRef;
    private Kullanici mKullanici;

    private void init(){
        mFireStore = FirebaseFirestore.getInstance();
        mUser= FirebaseAuth.getInstance().getCurrentUser();

        mRef = mFireStore.collection("Kullanıcılar").document(mUser.getUid());
        mRef.get()
                .addOnSuccessListener(this, new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                      if(documentSnapshot.exists())
                          mKullanici = documentSnapshot.toObject(Kullanici.class);
                    }
                });

<<<<<<< HEAD
        mesajIstegiList = new ArrayList<>();

=======
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
        mBottomView = (BottomNavigationView)findViewById(R.id.main_activity_bottomView);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mRelaNotif = (RelativeLayout)findViewById(R.id.bar_layout_relaNotif);
        txtBildirimSayisi = (TextView)findViewById(R.id.bar_layout_txtBildirimSayisi);
        mesajIstekleriRecyclerView = (RecyclerView)findViewById(R.id.custom_dialog_gelen_mesaj_istekleri_recycleView);


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
        mesajIstegiList = new ArrayList<MesajIstegi>();
        mQuery = mFireStore.collection("Mesajİstekleri").document(mUser.getUid()).collection("İstekler");
        mQuery.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value != null){
                    txtBildirimSayisi.setText(String.valueOf(value.getDocuments().size()));
                    mesajIstegiList.clear();

                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        mMesajIstegi = snapshot.toObject(MesajIstegi.class);
                        mesajIstegiList.add(mMesajIstegi);
                    }
                }
            }
        });
        mRelaNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesajDialog();
            }
        });

        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_ic_people:
                        mRelaNotif.setVisibility(View.INVISIBLE);
                        fragmentiAyarla(kullanicilarFragment);
                        return true;

                    case R.id.bottom_nav_ic_message:
                        mRelaNotif.setVisibility(View.VISIBLE);
                        fragmentiAyarla(mesajlarFragment);
                        return true;

                    case R.id.bottom_nav_ic_profile:
                        mRelaNotif.setVisibility(View.INVISIBLE);
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

    private void mesajDialog(){

        mesajIstekleriDialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        mesajIstekleriDialog.setContentView(R.layout.custom_dialog_gelen_mesaj_istekleri);

        mesajIstekleriKapat = mesajIstekleriDialog.findViewById(R.id.custom_dialog_gelen_mesaj_istekleri_imgKapat);
        mesajIstekleriRecyclerView = mesajIstekleriDialog.findViewById(R.id.custom_dialog_gelen_mesaj_istekleri_recycleView);

        mesajIstekleriKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesajIstekleriDialog.dismiss();
            }
        });
        mesajIstekleriRecyclerView.setHasFixedSize(true);
        mesajIstekleriRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        if(mesajIstegiList.size() > 0){
            mAdapter = new MesajIstekleriAdapter(mesajIstegiList ,this, mKullanici.getKullaniciId(),mKullanici.getKullaniciIsmi(),mKullanici.getKullaniciProfil());
            mesajIstekleriRecyclerView.setAdapter(mAdapter);
        }

        mesajIstekleriDialog.show();
    }

<<<<<<< HEAD
=======
=======
package com.androidproject.alole.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.androidproject.alole.Adapter.MesajIstekleriAdapter;
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
import com.androidproject.alole.Fragment.KullanicilarFragment;
import com.androidproject.alole.Fragment.MesajlarFragment;
import com.androidproject.alole.Fragment.ProfilFragment;
import com.androidproject.alole.Model.Kullanici;
import com.androidproject.alole.Model.MesajIstegi;
import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Object> mData;
    private BottomNavigationView mBottomView;
    private KullanicilarFragment kullanicilarFragment;
    private MesajlarFragment mesajlarFragment;
    private ProfilFragment profilFragment;
    private FragmentTransaction transaction;
    private Toolbar mToolbar;
    private RelativeLayout mRelaNotif;
    private TextView txtBildirimSayisi;

    private FirebaseFirestore mFireStore;
    private Query mQuery;
    private FirebaseUser mUser;
    private MesajIstegi mMesajIstegi;
    private ArrayList<MesajIstegi> mesajIstegiList;

    private Dialog mesajIstekleriDialog;
    private ImageView mesajIstekleriKapat;
    private RecyclerView mesajIstekleriRecyclerView;
    private MesajIstekleriAdapter mAdapter;

    private DocumentReference mRef;
    private Kullanici mKullanici;

    private void init(){
        mFireStore = FirebaseFirestore.getInstance();
        mUser= FirebaseAuth.getInstance().getCurrentUser();

        mRef = mFireStore.collection("Kullanıcılar").document(mUser.getUid());
        mRef.get()
                .addOnSuccessListener(this, new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                      if(documentSnapshot.exists())
                          mKullanici = documentSnapshot.toObject(Kullanici.class);
                    }
                });

<<<<<<< HEAD
        mesajIstegiList = new ArrayList<>();

=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
        mBottomView = (BottomNavigationView)findViewById(R.id.main_activity_bottomView);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mRelaNotif = (RelativeLayout)findViewById(R.id.bar_layout_relaNotif);
        txtBildirimSayisi = (TextView)findViewById(R.id.bar_layout_txtBildirimSayisi);
        mesajIstekleriRecyclerView = (RecyclerView)findViewById(R.id.custom_dialog_gelen_mesaj_istekleri_recycleView);


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
        mesajIstegiList = new ArrayList<MesajIstegi>();
        mQuery = mFireStore.collection("Mesajİstekleri").document(mUser.getUid()).collection("İstekler");
        mQuery.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value != null){
                    txtBildirimSayisi.setText(String.valueOf(value.getDocuments().size()));
                    mesajIstegiList.clear();

                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        mMesajIstegi = snapshot.toObject(MesajIstegi.class);
                        mesajIstegiList.add(mMesajIstegi);
                    }
                }
            }
        });
        mRelaNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesajDialog();
            }
        });

        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_ic_people:
                        mRelaNotif.setVisibility(View.INVISIBLE);
                        fragmentiAyarla(kullanicilarFragment);
                        return true;

                    case R.id.bottom_nav_ic_message:
                        mRelaNotif.setVisibility(View.VISIBLE);
                        fragmentiAyarla(mesajlarFragment);
                        return true;

                    case R.id.bottom_nav_ic_profile:
                        mRelaNotif.setVisibility(View.INVISIBLE);
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

    private void mesajDialog(){

        mesajIstekleriDialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        mesajIstekleriDialog.setContentView(R.layout.custom_dialog_gelen_mesaj_istekleri);

        mesajIstekleriKapat = mesajIstekleriDialog.findViewById(R.id.custom_dialog_gelen_mesaj_istekleri_imgKapat);
        mesajIstekleriRecyclerView = mesajIstekleriDialog.findViewById(R.id.custom_dialog_gelen_mesaj_istekleri_recycleView);

        mesajIstekleriKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesajIstekleriDialog.dismiss();
            }
        });
        mesajIstekleriRecyclerView.setHasFixedSize(true);
        mesajIstekleriRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        if(mesajIstegiList.size() > 0){
            mAdapter = new MesajIstekleriAdapter(mesajIstegiList ,this, mKullanici.getKullaniciId(),mKullanici.getKullaniciIsmi(),mKullanici.getKullaniciProfil());
            mesajIstekleriRecyclerView.setAdapter(mAdapter);
        }

        mesajIstekleriDialog.show();
    }

<<<<<<< HEAD
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
    private void kullaniciSetOnline(Boolean b){
        mData = new HashMap<>();
        mData.put("kullaniciOnline", b);

        mFireStore.collection("Kullanıcılar").document(mUser.getUid())
                .update(mData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        kullaniciSetOnline(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        kullaniciSetOnline(false);
    }
<<<<<<< HEAD
=======
=======
>>>>>>> a9d0e200c79fd8f898f4ea8cc0f8c6328b73cb39
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
}