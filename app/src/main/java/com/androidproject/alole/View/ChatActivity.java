<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
package com.androidproject.alole.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
=======
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
<<<<<<< HEAD
import android.widget.ProgressBar;
=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.alole.Adapter.ChatAdapter;
import com.androidproject.alole.Model.Chat;
import com.androidproject.alole.Model.Kullanici;
import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnCompleteListener;
<<<<<<< HEAD
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
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
<<<<<<< HEAD
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
=======
import com.squareup.picasso.Picasso;

>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
<<<<<<< HEAD
    private static final int IZIN_KODU = 0;
    private static final int IZIN_ALINDI_KODU = 1;

    private FirebaseUser mUser;
    private StorageReference mStorageRef, yeniRef, sRef;
    private HashMap<String, Object> mData;

    private ProgressDialog mProgress;
    private Intent galeriyeGit;
    private Uri imgUri;
    private String kayitYeri, indirmeLinki;
    private Bitmap imgBitmap;
    private ByteArrayOutputStream outputStream;
    private byte[] imgByte;
    private ImageDecoder.Source imgSource;
=======
    private FirebaseUser mUser;
    private HashMap<String, Object> mData;

>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
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

<<<<<<< HEAD
    private LinearLayoutManager mManager;
=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
    private Query chatOuery;
    private ArrayList<Chat> mChatList;
    private Chat mChat;
    private ChatAdapter chatAdapter;

    private void init(){
        mRecycleView = (RecyclerView)findViewById(R.id.chat_activity_recycleView);
        editMesaj = (EditText)findViewById(R.id.chat_activity_editMesaj);
        hedefProfil = (CircleImageView)findViewById(R.id.chat_activity_imgHedefProfil);
        hedefIsim = (TextView)findViewById(R.id.chat_activity_txtHedefIsim);

<<<<<<< HEAD
        mStorageRef = FirebaseStorage.getInstance().getReference();

=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
        mFireStore = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        gelenIntent = getIntent();
        hedefId= gelenIntent.getStringExtra("hedefId");
        kanalId=gelenIntent.getStringExtra("kanalId");
        hedefProfilUrl=gelenIntent.getStringExtra("hedefProfil");

        mChatList = new ArrayList<>();
<<<<<<< HEAD

        mProgress = new ProgressDialog(ChatActivity.this);
        mProgress.setTitle("Resim Gönderiliyor :)");
=======
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
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
<<<<<<< HEAD
                          hedefProfil.setImageResource(R.drawable.ic_profil);
                      else
                          Picasso.get().load(hedefKullanici.getKullaniciProfil()).resize(76,76).into(hedefProfil);
=======
                          hedefProfil.setImageResource(R.mipmap.ic_launcher);
                      else
                          Picasso.get().load(hedefKullanici.getKullaniciProfil()).resize(66,66).into(hedefProfil);
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b

                  }
               }
            }
        });

        mRecycleView.setHasFixedSize(true);
<<<<<<< HEAD
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mManager.setStackFromEnd(true);
        mRecycleView.setLayoutManager(mManager);
=======
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b

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
<<<<<<< HEAD
           mesajGonder(txtMesaj, "text");
=======
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
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
       }else
           Toast.makeText(ChatActivity.this, "Boş Mesaj Gönderemezsiniz!!!", Toast.LENGTH_SHORT).show();
    }
    public void btnChatKapat(View v){
        finish();
    }
<<<<<<< HEAD
    public void btnGaleridenResimGonder(View v){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IZIN_KODU);
        else
            galeriIntent();
    }

    public void galeriIntent(){
        galeriyeGit = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galeriyeGit, IZIN_ALINDI_KODU);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == IZIN_KODU){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                galeriIntent();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IZIN_ALINDI_KODU){
            if (resultCode == RESULT_OK && data != null && data.getData() != null){
                imgUri = data.getData();

                try {
                   if(Build.VERSION.SDK_INT >= 28){
                       imgSource = ImageDecoder.createSource(this.getContentResolver(), imgUri);
                       imgBitmap = ImageDecoder.decodeBitmap(imgSource);
                   }else{
                       imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imgUri);
                   }

                   outputStream = new ByteArrayOutputStream();
                   imgBitmap.compress(Bitmap.CompressFormat.PNG, 75, outputStream);
                   imgByte = outputStream.toByteArray();

                   kayitYeri = "ChatYuklenenler/" + kanalId + "/" + mUser.getUid() + "/" + System.currentTimeMillis() + ".png";
                   sRef = mStorageRef.child(kayitYeri);
                   sRef.putBytes(imgByte)
                           .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                               @Override
                               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                   mProgress.show();

                                   yeniRef = FirebaseStorage.getInstance().getReference(kayitYeri);
                                   yeniRef.getDownloadUrl()
                                           .addOnSuccessListener(ChatActivity.this, new OnSuccessListener<Uri>() {
                                               @Override
                                               public void onSuccess(Uri uri) {
                                                   indirmeLinki = uri.toString();

                                                   mesajGonder(indirmeLinki, "resim");
                                               }
                                           }).addOnFailureListener(ChatActivity.this, new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           e.printStackTrace();
                                           progressAyari();
                                       }
                                   });
                               }
                           }).addOnFailureListener(this, new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           e.printStackTrace();
                       }
                   });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void mesajGonder(String txtMesaj, String mesajTipi){
        docId = UUID.randomUUID().toString();

        mData = new HashMap<>();
        mData.put("mesajIcerigi", txtMesaj);
        mData.put("gonderen", mUser.getUid());
        mData.put("alici", hedefId);
        mData.put("mesajTipi", mesajTipi);
        mData.put("mesajTarihi", FieldValue.serverTimestamp());
        mData.put("docId", docId);

        mFireStore.collection("ChatKanalları").document(kanalId).collection("Mesajlar").document(docId)
                .set(mData)
                .addOnCompleteListener(ChatActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            editMesaj.setText("");

                           progressAyari();
                        }else
                            Toast.makeText(ChatActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void progressAyari(){
        if (mProgress.isShowing())
            mProgress.dismiss();
    }
=======
=======
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
>>>>>>> a9d0e200c79fd8f898f4ea8cc0f8c6328b73cb39
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
}