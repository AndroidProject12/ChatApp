<<<<<<< HEAD
package com.androidproject.alole.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.alole.Model.MesajIstegi;
import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.MoreObjects;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MesajIstekleriAdapter extends RecyclerView.Adapter<MesajIstekleriAdapter.MesajIstekleriHolder> {
    private ArrayList<MesajIstegi> mMesajIstegiList;
    private Context mContext;
    private MesajIstegi mesajIstegi, yeniMesajIstegi;
    private View v;
    private int mPos;
    private String mUID, mIsim, mProfilUrl;

    private FirebaseFirestore mFirestore;



    public MesajIstekleriAdapter(ArrayList<MesajIstegi> mMesajIstegiList, Context mContext, String mUID, String mIsim, String mProfilUrl) {
        this.mMesajIstegiList = mMesajIstegiList;
        this.mContext = mContext;
        this.mUID = mUID;
        this.mIsim = mIsim;
        this.mProfilUrl = mProfilUrl;
        mFirestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public MesajIstekleriHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(mContext).inflate(R.layout.gelen_mesaj_istekleri_item, parent, false);
        return new MesajIstekleriHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MesajIstekleriHolder holder, int position) {
       mesajIstegi = mMesajIstegiList.get(position);
       holder.txtMesaj.setText(mesajIstegi.getKullaniciIsim() + " Kullanıcısı Sana Mesaj Göndermek İstiyor");

       if(mesajIstegi.getKullaniciProfil().equals("default")){
           holder.imgProfil.setImageResource(R.mipmap.ic_launcher);
       }else
           Picasso.get().load(mesajIstegi.getKullaniciProfil()).resize(77,77).into(holder.imgProfil);

       holder.imgOnay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            mPos = holder.getAdapterPosition();

            if(mPos != RecyclerView.NO_POSITION){
                yeniMesajIstegi = new MesajIstegi(mMesajIstegiList.get(mPos).getKanalId(),mMesajIstegiList.get(mPos).getKullaniciId(),
                        mMesajIstegiList.get(mPos).getKullaniciIsim(), mMesajIstegiList.get(mPos).getKullaniciProfil());

                mFirestore.collection("Kullanıcılar").document(mUID).collection("Kanal").document(mMesajIstegiList.get(mPos).getKullaniciId())
                     .set(yeniMesajIstegi)
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   yeniMesajIstegi = new MesajIstegi(mMesajIstegiList.get(mPos).getKanalId(), mUID, mIsim, mProfilUrl);

                                   mFirestore.collection("Kullanıcılar").document(mMesajIstegiList.get(mPos).getKullaniciId()).collection("Kanal").document(mUID)
                                           .set(yeniMesajIstegi)
                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                     if (task.isSuccessful())
                                                         mesajIsteginiSil(mMesajIstegiList.get(mPos).getKullaniciId(), "Mesaj İsteği Kabul Edildi");
                                                     else
                                                         Toast.makeText(mContext,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                               }
                                           });
                               }else
                                   Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });
            }
           }
       });

       holder.imgIptal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             mPos = holder.getAdapterPosition();

             if(mPos != RecyclerView.NO_POSITION)
                 mesajIsteginiSil(mMesajIstegiList.get(mPos).getKullaniciId(), "Mesaj İsteği Reddedildi");
           }
       });
    }
    @Override
    public int getItemCount() {
        return mMesajIstegiList.size();
    }

    class MesajIstekleriHolder extends RecyclerView.ViewHolder{
        CircleImageView imgProfil;
        TextView txtMesaj;
        ImageView imgIptal, imgOnay;

        public MesajIstekleriHolder(@NonNull View itemView) {
            super(itemView);
            imgProfil = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_imgProfil);
            txtMesaj = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_txtMesaj);
            imgIptal = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_imgIptal);
            imgOnay = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_imgOnayla);
        }
    }
    private void mesajIsteginiSil(String hedefUID, final String mesajIcerigi){
        mFirestore.collection("Mesajİstekleri").document(mUID).collection("İstekler").document(hedefUID)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyDataSetChanged();
                            Toast.makeText(mContext, mesajIcerigi, Toast.LENGTH_SHORT).show();
                        }else
                             Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
=======
package com.androidproject.alole.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.alole.Model.MesajIstegi;
import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.MoreObjects;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MesajIstekleriAdapter extends RecyclerView.Adapter<MesajIstekleriAdapter.MesajIstekleriHolder> {
    private ArrayList<MesajIstegi> mMesajIstegiList;
    private Context mContext;
    private MesajIstegi mesajIstegi, yeniMesajIstegi;
    private View v;
    private int mPos;
    private String mUID, mIsim, mProfilUrl;

    private FirebaseFirestore mFirestore;



    public MesajIstekleriAdapter(ArrayList<MesajIstegi> mMesajIstegiList, Context mContext, String mUID, String mIsim, String mProfilUrl) {
        this.mMesajIstegiList = mMesajIstegiList;
        this.mContext = mContext;
        this.mUID = mUID;
        this.mIsim = mIsim;
        this.mProfilUrl = mProfilUrl;
        mFirestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public MesajIstekleriHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(mContext).inflate(R.layout.gelen_mesaj_istekleri_item, parent, false);
        return new MesajIstekleriHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MesajIstekleriHolder holder, int position) {
       mesajIstegi = mMesajIstegiList.get(position);
       holder.txtMesaj.setText(mesajIstegi.getKullaniciIsim() + " Kullanıcısı Sana Mesaj Göndermek İstiyor");

       if(mesajIstegi.getKullaniciProfil().equals("default")){
           holder.imgProfil.setImageResource(R.mipmap.ic_launcher);
       }else
           Picasso.get().load(mesajIstegi.getKullaniciProfil()).resize(77,77).into(holder.imgProfil);

       holder.imgOnay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            mPos = holder.getAdapterPosition();

            if(mPos != RecyclerView.NO_POSITION){
                yeniMesajIstegi = new MesajIstegi(mMesajIstegiList.get(mPos).getKanalId(),mMesajIstegiList.get(mPos).getKullaniciId(),
                        mMesajIstegiList.get(mPos).getKullaniciIsim(), mMesajIstegiList.get(mPos).getKullaniciProfil());

                mFirestore.collection("Kullanıcılar").document(mUID).collection("Kanal").document(mMesajIstegiList.get(mPos).getKullaniciId())
                     .set(yeniMesajIstegi)
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   yeniMesajIstegi = new MesajIstegi(mMesajIstegiList.get(mPos).getKanalId(), mUID, mIsim, mProfilUrl);

                                   mFirestore.collection("Kullanıcılar").document(mMesajIstegiList.get(mPos).getKullaniciId()).collection("Kanal").document(mUID)
                                           .set(yeniMesajIstegi)
                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                     if (task.isSuccessful())
                                                         mesajIsteginiSil(mMesajIstegiList.get(mPos).getKullaniciId(), "Mesaj İsteği Kabul Edildi");
                                                     else
                                                         Toast.makeText(mContext,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                               }
                                           });
                               }else
                                   Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });
            }
           }
       });

       holder.imgIptal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             mPos = holder.getAdapterPosition();

             if(mPos != RecyclerView.NO_POSITION)
                 mesajIsteginiSil(mMesajIstegiList.get(mPos).getKullaniciId(), "Mesaj İsteği Reddedildi");
           }
       });
    }
    @Override
    public int getItemCount() {
        return mMesajIstegiList.size();
    }

    class MesajIstekleriHolder extends RecyclerView.ViewHolder{
        CircleImageView imgProfil;
        TextView txtMesaj;
        ImageView imgIptal, imgOnay;

        public MesajIstekleriHolder(@NonNull View itemView) {
            super(itemView);
            imgProfil = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_imgProfil);
            txtMesaj = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_txtMesaj);
            imgIptal = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_imgIptal);
            imgOnay = itemView.findViewById(R.id.gelen_mesaj_istekleri_item_imgOnayla);
        }
    }
    private void mesajIsteginiSil(String hedefUID, final String mesajIcerigi){
        mFirestore.collection("Mesajİstekleri").document(mUID).collection("İstekler").document(hedefUID)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyDataSetChanged();
                            Toast.makeText(mContext, mesajIcerigi, Toast.LENGTH_SHORT).show();
                        }else
                             Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
>>>>>>> a9d0e200c79fd8f898f4ea8cc0f8c6328b73cb39
