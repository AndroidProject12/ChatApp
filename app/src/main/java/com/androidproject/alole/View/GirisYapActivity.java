<<<<<<< HEAD
package com.androidproject.alole.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GirisYapActivity extends AppCompatActivity {
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private LinearLayout mLinear;
    private TextInputLayout inputEmail, inputSifre;
    private EditText editEmail, editSifre;
    private String txtEmail, txtSifre;

    private void init(){
       mLinear = (LinearLayout)findViewById(R.id.giris_yap_Linear);
       mAuth = FirebaseAuth.getInstance();
       mUser = mAuth.getCurrentUser();

       inputEmail = (TextInputLayout)findViewById(R.id.giris_yap_inputEmail);
       inputSifre = (TextInputLayout)findViewById(R.id.giris_yap_inputSifre);

       editEmail = (EditText)findViewById(R.id.giris_yap_editEmail);
       editSifre = (EditText)findViewById(R.id.giris_yap_editSifre);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);
        init();
        if(mUser != null){
            finish();
            startActivity(new Intent(GirisYapActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
    public void btnGirisYap(View v){
         txtEmail = editEmail.getText().toString();
         txtSifre = editSifre.getText().toString();
         if(!TextUtils.isEmpty(txtEmail)){
             if(!TextUtils.isEmpty(txtSifre)){
                 mProgress = new ProgressDialog(this);
                 mProgress.setTitle("Giri?? Yap??l??yor...");
                 mProgress.show();

               mAuth.signInWithEmailAndPassword(txtEmail, txtSifre)
                       .addOnCompleteListener(GirisYapActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressAyar();
                                    Toast.makeText(GirisYapActivity.this,"Ba??ar??yla Giri?? Yapt??n??z :)", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(GirisYapActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                }else{
                                    progressAyar();
                                    Snackbar.make(mLinear,task.getException().getMessage(),Snackbar.LENGTH_SHORT).show();
                                }
                           }
                       });
             }else{ inputSifre.setError("L??tfen Ge??erli ??ifre Giriniz!!!");
             }
         }
         else{ inputEmail.setError("L??tfen Ge??erli Email Adresi Giriniz!!!");
         }
    }
    public void btnGitKayitOl(View v){
        startActivity(new Intent(GirisYapActivity.this, KayitOlActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    private void progressAyar(){
        if(mProgress.isShowing()){
            mProgress.dismiss();
        }
    }
=======
package com.androidproject.alole.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidproject.alole.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GirisYapActivity extends AppCompatActivity {
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private LinearLayout mLinear;
    private TextInputLayout inputEmail, inputSifre;
    private EditText editEmail, editSifre;
    private String txtEmail, txtSifre;

    private void init(){
       mLinear = (LinearLayout)findViewById(R.id.giris_yap_Linear);
       mAuth = FirebaseAuth.getInstance();
       mUser = mAuth.getCurrentUser();

       inputEmail = (TextInputLayout)findViewById(R.id.giris_yap_inputEmail);
       inputSifre = (TextInputLayout)findViewById(R.id.giris_yap_inputSifre);

       editEmail = (EditText)findViewById(R.id.giris_yap_editEmail);
       editSifre = (EditText)findViewById(R.id.giris_yap_editSifre);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);
        init();
        if(mUser != null){
            finish();
            startActivity(new Intent(GirisYapActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }
>>>>>>> 3829c2b4fa3643a32abc7eb2e38d477780fc334b
>>>>>>> 9005ef847ef8dfe4cba1840ac80a3a1c5050fa56
    public void btnGirisYap(View v){
         txtEmail = editEmail.getText().toString();
         txtSifre = editSifre.getText().toString();
         if(!TextUtils.isEmpty(txtEmail)){
             if(!TextUtils.isEmpty(txtSifre)){
                 mProgress = new ProgressDialog(this);
                 mProgress.setTitle("Giri?? Yap??l??yor...");
                 mProgress.show();

               mAuth.signInWithEmailAndPassword(txtEmail, txtSifre)
                       .addOnCompleteListener(GirisYapActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressAyar();
                                    Toast.makeText(GirisYapActivity.this,"Ba??ar??yla Giri?? Yapt??n??z :)", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(GirisYapActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                }else{
                                    progressAyar();
                                    Snackbar.make(mLinear,task.getException().getMessage(),Snackbar.LENGTH_SHORT).show();
                                }
                           }
                       });
             }else{ inputSifre.setError("L??tfen Ge??erli ??ifre Giriniz!!!");
             }
         }
         else{ inputEmail.setError("L??tfen Ge??erli Email Adresi Giriniz!!!");
         }
    }
    public void btnGitKayitOl(View v){
        startActivity(new Intent(GirisYapActivity.this, KayitOlActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    private void progressAyar(){
        if(mProgress.isShowing()){
            mProgress.dismiss();
        }
    }
>>>>>>> a9d0e200c79fd8f898f4ea8cc0f8c6328b73cb39
}