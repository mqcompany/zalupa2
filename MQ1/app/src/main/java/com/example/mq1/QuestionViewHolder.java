package com.example.mq1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class QuestionViewHolder extends RecyclerView.ViewHolder {
    //объявим поля, созданные в файле интерфейса recycle_question.xml
    public TextView resQuestion;


    //объявляем конструктор
    public QuestionViewHolder(final View recycle_qustion){
        super(recycle_qustion);
        //привязываем элементы к полям
        resQuestion = (TextView)recycle_qustion.findViewById(R.id.question);
    }

}
