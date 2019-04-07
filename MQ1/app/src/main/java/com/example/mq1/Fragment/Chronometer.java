package com.example.mq1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mq1.R;

import java.util.concurrent.TimeUnit;

public class Chronometer extends Fragment {
    TextView chromometer;
    FrameLayout select;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container, false);
        // Inflate the layout for this fragment
        chromometer = (TextView)view.findViewById(R.id.view_chronomenter);
        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
                chromometer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                chromometer.setText("Go!");
                Fragment playGame= new PlayGame();
      //          getActivity().getSupportFragmentManager().beginTransaction()
      //                  .replace(R.id.fragmet_game_container, playGame, "findThisFragment")
      //                  .addToBackStack(null)
      //                  .commit();
                FragmentTransaction trans=getFragmentManager().beginTransaction();
                trans.replace(R.id.fragmet_game_container, playGame);
                trans.commit();
            }
        }.start();
        return view;

    }
}