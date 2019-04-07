package com.example.mq1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateQuiz extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        btn = (Button) findViewById(R.id.button4);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                Intent intent = new Intent(this, CreateQuestions.class);
                startActivity(intent);
                // TODO Call second activity
                break;
            default:
                break;
        }
    }
}
