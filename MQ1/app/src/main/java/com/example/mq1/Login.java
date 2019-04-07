package com.example.mq1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText editTextEmailL;
    private EditText editTextPasswordL;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainLenta.class));
        }
        progressDialog = new ProgressDialog(this);
        buttonLogin = (Button)findViewById(R.id.ButtonLogin);
        editTextEmailL = (EditText)findViewById(R.id.EmailLoginText);
        editTextPasswordL = (EditText)findViewById(R.id.PasswLoginText);
        buttonLogin.setOnClickListener(this);
    }
    void Login(){
        String EmailL = editTextEmailL.getText().toString().trim();
        String PasswordL = editTextPasswordL.getText().toString().trim();
        if(TextUtils.isEmpty(EmailL)){
            Toast.makeText(this,"Пожалуйста введите E-mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(PasswordL)){
            Toast.makeText(this,"Пожалуйста введите пароль",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Вход...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(EmailL,PasswordL)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainLenta.class));
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if (v == buttonLogin){
            Login();
        }

    }
}
