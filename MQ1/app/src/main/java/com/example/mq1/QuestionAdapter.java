package com.example.mq1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder>{
    //Здесь мы будем хранить набор наших данных
    private ArrayList<Quiz> questions;


    //Конструктор
    public QuestionAdapter(ArrayList<Quiz> questions){
        this.questions = questions;
    }
    //Этот метод вызывается при прикреплении нового элемента к RecyclerView
    @Override
    public void onBindViewHolder(QuestionViewHolder QuestionViewHolder, int i){
        //Получаем элемент набора данных для заполнения
        Quiz currentQuestion = questions.get(i);
        //Заполняем поля viewHolder'а данными из элемента набора данных
        QuestionViewHolder.resQuestion.setText(currentQuestion.question);
    }

    //Этот метод вызывается при создании нового ViewHolder'а

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        //Создаём новый view при помощи LayoutInflater
        //Особенно упорные могут создать его программно с помощью view.addView
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_qustion, viewGroup, false);

        return new QuestionViewHolder(itemView);
    }

    //Этот метод возвращает количество элементов списка
    @Override
    public int getItemCount(){
        return questions.size();
    }
}
