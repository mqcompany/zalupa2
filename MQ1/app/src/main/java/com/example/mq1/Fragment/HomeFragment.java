package com.example.mq1.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mq1.ChangeSettingsProfile;
import com.example.mq1.MainActivity;
import com.example.mq1.MainLenta;
import com.example.mq1.R;
import com.example.mq1.SettingProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;


import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private String Nickname;
    FirebaseUser userw;
    private TextView showNick;
    private FirebaseDatabase database;
    private DatabaseReference myRefer;
    private String UserID;
    private CircleImageView settingsAvatarView;
    private String imageUrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefer = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        showNick = (TextView)view.findViewById(R.id.showNickName);


        settingsAvatarView = (CircleImageView)view.findViewById(R.id.settingsAvatarView);


        userw = firebaseAuth.getCurrentUser();
        UserID = userw.getUid().toString().trim();
        Toolbar toolbar = view.findViewById(R.id.toolbar1);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);


        myRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageUrl = dataSnapshot.child("Users").child(UserID).child("imageURL").getValue().toString();
                if(imageUrl == "default"){
                    settingsAvatarView.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    Glide.with(HomeFragment.this).load(imageUrl).into(settingsAvatarView);
                }
                Nickname = dataSnapshot.child("Users").child(UserID).child("NickName").getValue().toString();
                showNick.setText(Nickname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.right_toolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(getActivity(),ChangeSettingsProfile.class));
                break;
            case R.id.item2:
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(),MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {


    }
}