<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
package com.androidproject.alole.Adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.ImageView;
=======
<<<<<<< HEAD
import android.widget.ImageView;
=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.alole.Model.MesajIstegi;
import com.androidproject.alole.R;
import com.androidproject.alole.View.ChatActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MesajlarAdapter extends RecyclerView.Adapter<MesajlarAdapter.MesajlarHolder> {
    private ArrayList<MesajIstegi> mArrayList;
    private Context mContext;
    private ArrayList<String> mSonMsgList;
    private MesajIstegi mesajIstegi;
    private View v;
    private int kPos;
    private Intent chatIntent;

    public MesajlarAdapter(ArrayList<MesajIstegi> mArrayList, Context mContext, ArrayList<String> mSonMsgList) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
        this.mSonMsgList = mSonMsgList;
    }

    @NonNull
    @Override
    public MesajlarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(mContext).inflate(R.layout.mesajlar_item, parent, false);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

        return new MesajlarHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MesajlarHolder holder, int position) {
        mesajIstegi = mArrayList.get(position);
        holder.kullaniciIsmi.setText(mesajIstegi.getKullaniciIsim());
        holder.sonMesaj.setText(mSonMsgList.get(position));

        if (mesajIstegi.getKullaniciProfil().equals("default"))
            holder.kullaniciProfil.setImageResource(R.mipmap.ic_launcher);
        else
            Picasso.get().load(mesajIstegi.getKullaniciProfil()).resize(66,66).into(holder.kullaniciProfil);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kPos = holder.getAdapterPosition();

                if(kPos != RecyclerView.NO_POSITION){
                    chatIntent = new Intent(mContext, ChatActivity.class);
                    chatIntent.putExtra("kanalId", mArrayList.get(kPos).getKanalId());
                    chatIntent.putExtra("hedefId", mArrayList.get(kPos).getKullaniciId());
                    chatIntent.putExtra("hedefProfil", mArrayList.get(kPos).getKullaniciProfil());
                    chatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(chatIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class MesajlarHolder extends RecyclerView.ViewHolder{
        TextView kullaniciIsmi, sonMesaj;
        CircleImageView kullaniciProfil;

        public MesajlarHolder(@NonNull View itemView) {
            super(itemView);

            kullaniciIsmi = itemView.findViewById(R.id.mesajlar_item_txtKullaniciIsim);
            kullaniciProfil = itemView.findViewById(R.id.mesajlar_item_imgKullaniciProfil);
            sonMesaj = itemView.findViewById(R.id.mesajlar_item_txtSonMesaj);
        }
    }
}
=======
package com.androidproject.alole.Adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.alole.Model.MesajIstegi;
import com.androidproject.alole.R;
import com.androidproject.alole.View.ChatActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MesajlarAdapter extends RecyclerView.Adapter<MesajlarAdapter.MesajlarHolder> {
    private ArrayList<MesajIstegi> mArrayList;
    private Context mContext;
    private ArrayList<String> mSonMsgList;
    private MesajIstegi mesajIstegi;
    private View v;
    private int kPos;
    private Intent chatIntent;

    public MesajlarAdapter(ArrayList<MesajIstegi> mArrayList, Context mContext, ArrayList<String> mSonMsgList) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
        this.mSonMsgList = mSonMsgList;
    }

    @NonNull
    @Override
    public MesajlarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(mContext).inflate(R.layout.mesajlar_item, parent, false);

>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
        return new MesajlarHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MesajlarHolder holder, int position) {
        mesajIstegi = mArrayList.get(position);
        holder.kullaniciIsmi.setText(mesajIstegi.getKullaniciIsim());
        holder.sonMesaj.setText(mSonMsgList.get(position));

        if (mesajIstegi.getKullaniciProfil().equals("default"))
<<<<<<< HEAD
            holder.kullaniciProfil.setImageResource(R.drawable.ic_profil);
=======
<<<<<<< HEAD
            holder.kullaniciProfil.setImageResource(R.drawable.ic_profil);
=======
            holder.kullaniciProfil.setImageResource(R.mipmap.ic_launcher);
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
        else
            Picasso.get().load(mesajIstegi.getKullaniciProfil()).resize(66,66).into(holder.kullaniciProfil);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kPos = holder.getAdapterPosition();

                if(kPos != RecyclerView.NO_POSITION){
                    chatIntent = new Intent(mContext, ChatActivity.class);
                    chatIntent.putExtra("kanalId", mArrayList.get(kPos).getKanalId());
                    chatIntent.putExtra("hedefId", mArrayList.get(kPos).getKullaniciId());
                    chatIntent.putExtra("hedefProfil", mArrayList.get(kPos).getKullaniciProfil());
                    chatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(chatIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class MesajlarHolder extends RecyclerView.ViewHolder{
        TextView kullaniciIsmi, sonMesaj;
        CircleImageView kullaniciProfil;

        public MesajlarHolder(@NonNull View itemView) {
            super(itemView);

            kullaniciIsmi = itemView.findViewById(R.id.mesajlar_item_txtKullaniciIsim);
            kullaniciProfil = itemView.findViewById(R.id.mesajlar_item_imgKullaniciProfil);
            sonMesaj = itemView.findViewById(R.id.mesajlar_item_txtSonMesaj);
        }
    }
}
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> a9d0e200c79fd8f898f4ea8cc0f8c6328b73cb39
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
