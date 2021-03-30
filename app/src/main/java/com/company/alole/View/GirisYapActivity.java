package com.company.alole.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.company.alole.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisYapActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    
    private LinearLayout mLinear;
    private TextInputLayout inputEmail,inputSifre;
    private EditText editEmail,editSifre;
    private String txtEmail,txtSifre;

    private void init(){
        mLinear = (LinearLayout)findViewById(R.id.giris_yap_linear);
        mAuth = FirebaseAuth.getInstance();

        inputEmail = (TextInputLayout)findViewById(R.id.giris_yap_inputEmail);
        inputSifre = (TextInputLayout)findViewById(R.id.giris_yap_inputSifre);

        editEmail = (EditText)findViewById(R.id.giris_yap_EditEmail);
        editSifre = (EditText)findViewById(R.id.giris_yap_EditSifre);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);
    }

    public void btnGirisYap(View v){
        txtEmail = editEmail.getText().toString();
        txtSifre = editSifre.getText().toString();

        if (!TextUtils.isEmpty(txtEmail)){
            if (!TextUtils.isEmpty(txtSifre)){
                mAuth.signInWithEmailAndPassword(txtEmail,txtSifre)
                        .addOnCompleteListener(GirisYapActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(GirisYapActivity.this,"Basariyla Giris Yaptiniz",Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(GirisYapActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                                }else {
                                    Snackbar.make(mLinear, task.getException().getMessage(),Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else
                inputSifre.setError("Lutfen Gecerli Bir Sifre Giriniz.");
        }else
            inputEmail.setError("Lutfen Gecerli Bir Email Adresi Giriniz.");
    }

    public void btnGitKayitOl(View v){
        startActivity(new Intent(GirisYapActivity.this,KayitOlActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}