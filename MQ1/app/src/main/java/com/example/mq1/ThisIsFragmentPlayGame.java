package com.example.mq1;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mq1.Fragment.Chronometer;
import com.example.mq1.Fragment.LentaFragment;
import com.example.mq1.Fragment.PlayGame;

public class ThisIsFragmentPlayGame extends AppCompatActivity {
    Fragment selectfragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_is_fragment_play_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Chronometer();
    }
    private void Chronometer(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmet_game_container, new Chronometer()).commit();
    }
}
