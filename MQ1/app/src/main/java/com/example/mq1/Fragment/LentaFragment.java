package com.example.mq1.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mq1.AdapterMQ;
import com.example.mq1.MainLenta;
import com.example.mq1.R;
import com.example.mq1.Read;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LentaFragment extends Fragment {
    DatabaseReference myRef;
    AdapterMQ adapterMQ;
    RecyclerView recyclerView;
    public List<Read> read_list1 = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lenta, container, false);
        myRef = FirebaseDatabase.getInstance().getReference("table");
        recyclerView= (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listner();
        return view;
    }
    private void listner(){
        myRef.addValueEventListener(new ValueEventListener() {
            //если данные в БД меняются
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (read_list1.size() > 0) { // sggds
                    read_list1.clear();
                }
                //проходим по всем записям и помещаем их в list_users в виде класса User
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Read read = postSnapshot.getValue(Read.class);
                    read_list1.add(read);
                }
                Collections.reverse(read_list1);
                adapterMQ = new AdapterMQ(getContext(), read_list1);

                recyclerView.setAdapter(adapterMQ);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
