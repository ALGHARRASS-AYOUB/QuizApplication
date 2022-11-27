package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {
    String guestname;
    TextView score,time,question_number,question;
    ImageView back;
    AppCompatButton option1,option2,option3,option4,option5;
    AppCompatButton next;

    boolean choosen=false;
    int numberOfQ;
    int score_count;
    final int SCORE_INCREMENT=10;
    String choice="";
    QuizDBHelper sqlDbHelper;
    ArrayList<Question> questions;
    Resources rs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        guestname=this.getIntent().getStringExtra(getString(R.string.name));
        Toast.makeText(this, "hello, "+guestname, Toast.LENGTH_SHORT).show();

        sqlDbHelper=new QuizDBHelper(this);
        questions=new ArrayList<>(sqlDbHelper.getAllQuestions());
        //get xml elements
        score=findViewById(R.id.score);
        time=findViewById(R.id.time);
        question=findViewById(R.id.question);
        question_number=findViewById(R.id.question_number);
        back=findViewById(R.id.back);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        option5=findViewById(R.id.option5);
        next=findViewById(R.id.next);
        Intent finalResultIntent=new Intent(this,FinalActivity.class);


        numberOfQ=0;
        score_count=0;

        load_question(numberOfQ);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choosen){
                    return;
                }
                    choosen=true;
                choice=option1.getText().toString();
                if(isCorrect(choice,questions.get(numberOfQ).getAnswer())){
                    option1.setBackgroundResource(R.drawable.correct_layout_green );
                }
                else{
                    List<AppCompatButton> other_options=new ArrayList<AppCompatButton>(){};
                    other_options.add(option2);
                    other_options.add(option3);
                    other_options.add(option4);
                    other_options.add(option5);
                    option1.setBackgroundResource(R.drawable.wrong_layout_red);
                    for ( AppCompatButton option:other_options) {
                        if(isCorrect(option.getText().toString(),questions.get(numberOfQ).getAnswer())){
                            option.setBackgroundResource(R.drawable.correct_layout_green);
                        }
                    }

                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choosen){
                    return;
                }
                choosen=true;
                choice=option2.getText().toString();
                if(isCorrect(choice,questions.get(numberOfQ).getAnswer())){
                    option2.setBackgroundResource(R.drawable.correct_layout_green );
                }
                else{
                    List<AppCompatButton> other_options=new ArrayList<AppCompatButton>(){};
                    other_options.add(option1);
                    other_options.add(option3);
                    other_options.add(option4);
                    other_options.add(option5);
                    option2.setBackgroundResource(R.drawable.wrong_layout_red);
                    for ( AppCompatButton option:other_options) {
                        if(isCorrect(option.getText().toString(),questions.get(numberOfQ).getAnswer())){
                            option.setBackgroundResource(R.drawable.correct_layout_green);
                        }
                    }

                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choosen){
                    return;
                }
                choosen=true;
                choice=option3.getText().toString();
                if(isCorrect(choice,questions.get(numberOfQ).getAnswer())){
                    option3.setBackgroundResource(R.drawable.correct_layout_green );
                }
                else{
                    List<AppCompatButton> other_options=new ArrayList<AppCompatButton>(){};
                    other_options.add(option2);
                    other_options.add(option1);
                    other_options.add(option4);
                    other_options.add(option5);
                    option3.setBackgroundResource(R.drawable.wrong_layout_red);
                    for ( AppCompatButton option:other_options) {
                        if(isCorrect(option.getText().toString(),questions.get(numberOfQ).getAnswer())){
                            option.setBackgroundResource(R.drawable.correct_layout_green);
                        }
                    }

                }
            }
        });


        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choosen){
                    return;
                }
                choosen=true;
                choice=option4.getText().toString();
                if(isCorrect(choice,questions.get(numberOfQ).getAnswer())){
                    option4.setBackgroundResource(R.drawable.correct_layout_green );
                }
                else{
                    List<AppCompatButton> other_options=new ArrayList<AppCompatButton>(){};
                    other_options.add(option2);
                    other_options.add(option3);
                    other_options.add(option1);
                    other_options.add(option5);
                    option4.setBackgroundResource(R.drawable.wrong_layout_red);
                    for ( AppCompatButton option:other_options) {
                        if(isCorrect(option.getText().toString(),questions.get(numberOfQ).getAnswer())){
                            option.setBackgroundResource(R.drawable.correct_layout_green);
                        }
                    }

                }
            }
        });

        option5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choosen){
                    return;
                }
                choosen=true;
                choice=option5.getText().toString();
                if(isCorrect(choice,questions.get(numberOfQ).getAnswer())){
                    option5.setBackgroundResource(R.drawable.correct_layout_green );
                }
                else{
                    List<AppCompatButton> other_options=new ArrayList<AppCompatButton>(){};
                    other_options.add(option2);
                    other_options.add(option3);
                    other_options.add(option4);
                    other_options.add(option1);
                    option5.setBackgroundResource(R.drawable.wrong_layout_red);
                    for ( AppCompatButton option:other_options) {
                        if(isCorrect(option.getText().toString(),questions.get(numberOfQ).getAnswer())){
                            option.setBackgroundResource(R.drawable.correct_layout_green);
                        }
                    }

                }
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numberOfQ== questions.size()-1){
                    score_count=(isCorrect(choice,questions.get(numberOfQ).getAnswer()))? score_count+SCORE_INCREMENT:score_count;
                    rs=getResources();
                    String keyScore=rs.getString(R.string.score_text);
                    String keyName=rs.getString(R.string.name);
                    finalResultIntent.putExtra(keyScore,score_count);
                    finalResultIntent.putExtra(keyName,guestname);
                    startActivity(finalResultIntent);
                    finish();
                }
                else{
                    if(isCorrect(choice,questions.get(numberOfQ).getAnswer()))
                    {
                        score_count+=SCORE_INCREMENT;
                    }

                    numberOfQ++;
                    load_question(numberOfQ);
                    choice="";
                    score.setText(String.valueOf(score_count));
                    question_number.setText(String.valueOf(numberOfQ+1)+"/"+String.valueOf(questions.size()));
                    resertBackgroundOptions();

                }
            }
        });




    }

    private void resetDrawableBackground() {
        option1.setBackgroundResource(R.drawable.white_background_option);
        option2.setBackgroundResource(R.drawable.white_background_option);
        option3.setBackgroundResource(R.drawable.white_background_option);
        option4.setBackgroundResource(R.drawable.white_background_option);
        option5.setBackgroundResource(R.drawable.white_background_option);
    }

    private boolean isCorrect(String choice,String answer){
        return  (choice.equals(answer))?true:false;
    }

    private void load_question(int number_quest) {

        question.setText(questions.get(number_quest).question_name);
        option1.setText(questions.get(number_quest).getOptions().get(0));
        option2.setText(questions.get(number_quest).getOptions().get(1));
        option3.setText(questions.get(number_quest).getOptions().get(2));
        option4.setText(questions.get(number_quest).getOptions().get(3));
        option5.setText(questions.get(number_quest).getOptions().get(4));
    }

    private void resertBackgroundOptions(){
        List<AppCompatButton> other_options=new ArrayList<AppCompatButton>(){};
        other_options.add(option1);
        other_options.add(option2);
        other_options.add(option3);

        other_options.add(option4);
        other_options.add(option5);
        for ( AppCompatButton option:other_options) {
                option.setBackgroundResource(R.drawable.white_background_option);
         }
        choosen=false;
    }

    // to  final result

}