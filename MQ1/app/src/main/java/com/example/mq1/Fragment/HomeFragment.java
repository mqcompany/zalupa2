package com.example.mq1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mq1.MainActivity;
import com.example.mq1.MainLenta;
import com.example.mq1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button logoutB;
    private FirebaseAuth firebaseAuth;
    private String Nickname;
    private EditText WriteNickname;
    private Button savenickname;
    private TextView showNick;
    private FirebaseDatabase database;
    private DatabaseReference myRefer;
    private String UserID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefer = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        savenickname = (Button)view.findViewById(R.id.saveNicknameButton);
        showNick = (TextView)view.findViewById(R.id.showNickName);
        WriteNickname = (EditText)view.findViewById(R.id.nickname);
        logoutB = (Button) view.findViewById(R.id.LogOutB);
        logoutB.setOnClickListener(this);
        savenickname.setOnClickListener(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserID = user.getUid().toString().trim();
        myRefer.child("Users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Nickname = dataSnapshot.child("NickName").getValue().toString();
                    showNick.setText(Nickname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }



    @Override
    public void onClick(View view) {
        if(view == logoutB){
            firebaseAuth.signOut();
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
        if(view == savenickname){
            Nickname = WriteNickname.getText().toString();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            myRefer.child("Users").push();
            myRefer.child("Users").child(user.getUid().toString().trim()).child("NickName").setValue(Nickname);

        }
    }
}