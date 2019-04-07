package com.example.mq1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CreateQuestions extends AppCompatActivity implements View.OnClickListener{
    final ArrayList<Quiz> Question = new ArrayList<>();
    RecyclerView rvMain;
    QuestionAdapter qAdapter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_questions);
        btn = (Button) findViewById(R.id.addQuestion);
        btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addQuestion:
                EditText QuestionText = (EditText) findViewById(R.id.Questions);
                String question = QuestionText.getText().toString();
                Quiz Q = new Quiz(question,"Answer1","Answer2","Answer3","Answer4");
                Question.add(Q);

                rvMain= (RecyclerView)findViewById(R.id.recQuestions);

                qAdapter = new QuestionAdapter(Question);

                LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                //Применим наш адаптер к RecyclerView
                rvMain.setAdapter(qAdapter);
                //И установим LayoutManager
                rvMain.setLayoutManager(layoutManager);



                // TODO Call second activity
                break;
            default:
                break;
        }
    }
}
