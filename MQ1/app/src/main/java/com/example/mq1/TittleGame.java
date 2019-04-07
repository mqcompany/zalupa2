package com.example.mq1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class TittleGame extends AppCompatActivity{
    TextView discriptionTW, Title;
    ImageView imageView;
    String image_lenta, discription, title;
    FloatingActionButton playbottongame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tittle_game);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        playbottongame = (FloatingActionButton)findViewById(R.id.playbottongame);
        discriptionTW = (TextView)findViewById(R.id.discription);
        imageView = (ImageView)findViewById(R.id.Quize_Image);
        image_lenta = getIntent().getStringExtra("imag_lenta");
        discription = getIntent().getStringExtra("discription");
        title = getIntent().getStringExtra("title_lenta");
        discriptionTW.setText(discription);
        collapsingToolbar.setTitle(title);
        Picasso.with(this).load(image_lenta).into(imageView);
        PlayClick();
    }
    public void PlayClick(){
        playbottongame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TittleGame.this , ThisIsFragmentPlayGame.class);
                startActivity(intent);
            }
        });
    }

}
