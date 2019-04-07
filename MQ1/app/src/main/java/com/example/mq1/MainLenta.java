package com.example.mq1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mq1.Fragment.AddFragment;
import com.example.mq1.Fragment.HomeFragment;
import com.example.mq1.Fragment.LentaFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainLenta extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView ShowEmail;
    private Button logoutB;
    private FirebaseDatabase database;
    private DatabaseReference myRefer;



    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    AddFragment addFragment;
    Fragment selectfragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lenta);

        database = FirebaseDatabase.getInstance();
        myRefer = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.button_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navigationItem);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmet_container, new LentaFragment()).commit();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        else {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            myRefer.child("Users").push();
            myRefer.child("Users").child(user.getUid().toString().trim()).child("Email").setValue(user.getEmail().toString());

        }


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItem = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.person:
                    selectfragment = new HomeFragment();
                    break;
                case R.id.menu_add:
                    Intent intent = new Intent(MainLenta.this, CreateQuiz.class);
                    startActivity(intent);
                    break;
                case R.id.menu_home:
                    selectfragment = new LentaFragment();
                    break;
            }
            if (selectfragment != null) getSupportFragmentManager().beginTransaction().replace(R.id.fragmet_container, selectfragment).commit();
            return true;
        }
    };

    @Override
    public void onClick(View v) {
        if(v == logoutB){
            firebaseAuth.signOut();
            Intent intent = new Intent(this,MainActivity.class);
            finish();
            startActivity(intent);
        }
    }
}
