package com.example.mq1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ебашу базу данных (Красава)
        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainLenta.class));
        }

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.ButtonRegister);
        editTextEmail = (EditText)findViewById(R.id.EmailText);
        editTextPassword = (EditText)findViewById(R.id.PasswText);
        textViewSignup = (TextView)findViewById(R.id.SignIn);
        buttonRegister.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        //Ебашу ленту
    }
    public void registerUser(){
        final String Email = editTextEmail.getText().toString().trim();
        String Password =  editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Пожалуйста введите E-mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Пожалуйста введите пароль",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Регистрация пользователя...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Вы успешно зарегистрированы",Toast.LENGTH_SHORT).show();

                    finish();
                    startActivityForResult(new Intent(getApplicationContext(),MainLenta.class),1);
                }
                else{
                    Toast.makeText(MainActivity.this,"Ошибка",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
        if(v == textViewSignup){
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        }
    }
}
