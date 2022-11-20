package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        guestname=this.getIntent().getStringExtra(getString(R.string.name));
        Toast.makeText(this, "hello, "+guestname, Toast.LENGTH_SHORT).show();

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

        //create the sqlite db and manipulate it
        QuizDBHelper sqlDbHelper=new QuizDBHelper(this);


        Set<Question> questions=sqlDbHelper.getAllQuestions();
        List<Question> arr_qs=new ArrayList(Arrays.asList(questions.toArray()));
        question.setText("");
//        Log.d("question name",arr_qs.get(0).question_name);
//        Log.d("answer",arr_qs.get(0).answer);


    }
}