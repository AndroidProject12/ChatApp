package com.androidproject.alole.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.alole.Adapter.ChatAdapter;
import com.androidproject.alole.Model.Chat;
import com.androidproject.alole.Model.Kullanici;
import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    private FirebaseUser mUser;
    private HashMap<String, Object> mData;

    private RecyclerView mRecycleView;
    private EditText editMesaj;
    private String txtMesaj, docId;
    private CircleImageView hedefProfil;
    private TextView hedefIsim;
    private Intent gelenIntent;
    private String hedefId,kanalId, hedefProfilUrl;
    private DocumentReference hedefRef;
    private Kullanici hedefKullanici;
    private FirebaseFirestore mFireStore;

    private Query chatOuery;
    private ArrayList<Chat> mChatList;
    private Chat mChat;
    private ChatAdapter chatAdapter;

    private void init(){
        mRecycleView = (RecyclerView)findViewById(R.id.chat_activity_recycleView);
        editMesaj = (EditText)findViewById(R.id.chat_activity_editMesaj);
        hedefProfil = (CircleImageView)findViewById(R.id.chat_activity_imgHedefProfil);
        hedefIsim = (TextView)findViewById(R.id.chat_activity_txtHedefIsim);

        mFireStore = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        gelenIntent = getIntent();
        hedefId= gelenIntent.getStringExtra("hedefId");
        kanalId=gelenIntent.getStringExtra("kanalId");
        hedefProfilUrl=gelenIntent.getStringExtra("hedefProfil");

        mChatList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();

        hedefRef=mFireStore.collection("Kullanıcılar").document(hedefId);
        hedefRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               if(error != null){
                   Toast.makeText(ChatActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                   return;
               }

               if (value != null && value.exists()){
                   hedefKullanici = value.toObject(Kullanici.class);

                  if (hedefKullanici != null){
                      hedefIsim.setText(hedefKullanici.getKullaniciIsmi());

                      if(hedefKullanici.getKullaniciProfil().equals("default"))
                          hedefProfil.setImageResource(R.mipmap.ic_launcher);
                      else
                          Picasso.get().load(hedefKullanici.getKullaniciProfil()).resize(66,66).into(hedefProfil);

                  }
               }
            }
        });

        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        chatOuery = mFireStore.collection("ChatKanalları").document(kanalId).collection("Mesajlar")
                .orderBy("mesajTarihi", Query.Direction.ASCENDING);
        chatOuery.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(ChatActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();;
                    return;
                }

                if(value != null){
                    mChatList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()){
                        mChat = snapshot.toObject(Chat.class);

                        assert  mChat != null;
                        mChatList.add(mChat);
                          // if(mChat != null)
                    }

                    chatAdapter = new ChatAdapter(mChatList, ChatActivity.this, mUser.getUid(), hedefProfilUrl);
                    mRecycleView.setAdapter(chatAdapter);
                }
            }
        });

    }
    public void btnMesajGonder(View v){
       txtMesaj = editMesaj.getText().toString();

       if(!TextUtils.isEmpty(txtMesaj)){
           docId = UUID.randomUUID().toString();

           mData = new HashMap<>();
           mData.put("mesajIcerigi", txtMesaj);
           mData.put("gonderen", mUser.getUid());
           mData.put("alici", hedefId);
           mData.put("mesajTipi", "text");
           mData.put("mesajTarihi", FieldValue.serverTimestamp());
           mData.put("docId", docId);

           mFireStore.collection("ChatKanalları").document(kanalId).collection("Mesajlar").document(docId)
                   .set(mData)
                   .addOnCompleteListener(ChatActivity.this, new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                               editMesaj.setText("");
                           else
                               Toast.makeText(ChatActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });
       }else
           Toast.makeText(ChatActivity.this, "Boş Mesaj Gönderemezsiniz!!!", Toast.LENGTH_SHORT).show();
    }
    public void btnChatKapat(View v){
        finish();
    }
}