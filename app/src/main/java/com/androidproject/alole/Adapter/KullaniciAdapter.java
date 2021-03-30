package com.androidproject.alole.Adapter;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidproject.alole.Model.Kullanici;
import com.androidproject.alole.R;
import com.google.api.Distribution;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class KullaniciAdapter extends RecyclerView.Adapter<KullaniciAdapter.KullaniciHolder> {
    private ArrayList<Kullanici> mKullaniciList;
    private Context mContext;
    private View v;
    private Kullanici mKullanici;
    private int kPos;

    private Dialog mesajDialog;
    private ImageView imgIptal;
    private LinearLayout linearGonder;
    private CircleImageView imgProfil;
    private EditText editMesaj;
    private String txtMesaj;
    private Window mesajWindow;

    public KullaniciAdapter(ArrayList<Kullanici> mKullaniciList, Context mContext) {
        this.mKullaniciList = mKullaniciList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public KullaniciHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(mContext).inflate(R.layout.kullanici_item,parent,false);

        return new KullaniciHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final KullaniciHolder holder, int position) {
       mKullanici = mKullaniciList.get(position);
       holder.kullaniciIsmi.setText(mKullanici.getKullaniciIsmi());

       if(mKullanici.getKullaniciProfil().equals("default"))
           holder.kullaniciProfil.setImageResource(R.mipmap.ic_launcher);

       else
           Picasso.get().load(mKullanici.getKullaniciProfil()).resize(66,66).into(holder.kullaniciProfil);
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                kPos = holder.getAdapterPosition();

                if (kPos != RecyclerView.NO_POSITION)
                    mesajGonderDialog(mKullaniciList.get(kPos));
               }
           });
    }

    private void mesajGonderDialog(Kullanici kullanici) {
        mesajDialog = new Dialog(mContext);
        mesajDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mesajWindow = mesajDialog.getWindow();
        mesajWindow.setGravity(Gravity.CENTER);
        mesajDialog.setContentView(R.layout.custom_dialog_mesaj_gonder);

        imgIptal = mesajDialog.findViewById(R.id.custom_dialog_mesaj_gonder_imgIptal);
        linearGonder = mesajDialog.findViewById(R.id.custom_dialog_mesaj_gonder_linearGonder);
        imgProfil = mesajDialog.findViewById(R.id.custom_dialog_mesaj_gonder_imgKullaniciProfil);
        editMesaj = mesajDialog.findViewById(R.id.custom_dialog_mesaj_gonder_editMesaj);

        if(kullanici.getKullaniciProfil().equals("default"))
            imgProfil.setImageResource(R.mipmap.ic_launcher);
        else
            Picasso.get().load(kullanici.getKullaniciProfil()).resize(126,126).into(imgProfil);

        imgIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesajDialog.dismiss();
            }
        });

        linearGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMesaj = editMesaj.getText().toString();

                if(!TextUtils.isEmpty(txtMesaj)){

                }else
                    Toast.makeText(mContext, "Boş Mesaj Gönderemezsiniz!!!", Toast.LENGTH_SHORT).show();
            }
        });
        mesajWindow.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        mesajDialog.show();
    }

    @Override
    public int getItemCount() {
        return mKullaniciList.size();
    }

    class KullaniciHolder extends RecyclerView.ViewHolder{
        TextView kullaniciIsmi;
        CircleImageView kullaniciProfil;

        public KullaniciHolder(@NonNull View itemView) {
            super(itemView);

            kullaniciIsmi = itemView.findViewById(R.id.kullanici_item_txtKullaniciIsim);
            kullaniciProfil = itemView.findViewById(R.id.kullanici_item_imgKullaniciProfil);
        }
    }
}
