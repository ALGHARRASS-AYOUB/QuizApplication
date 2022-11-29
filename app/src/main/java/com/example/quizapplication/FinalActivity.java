package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
        TextView message,score_result;
        Button restart,quit;
        ImageView icon_result;
        RatingBar rate;
        int score;
        String name;
        Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        rate=findViewById(R.id.score_rating_bar);
        message=findViewById(R.id.result_message);
        restart=findViewById(R.id.restart);
        quit=findViewById(R.id.quit);
        icon_result=findViewById(R.id.result_icon);
        score_result=findViewById(R.id.score_result);

        Intent intent   =getIntent();
        res=getResources();
        String keyName=res.getString(R.string.name);
        String keyScore=res.getString(R.string.score_text);

        name= intent.getStringExtra(keyName);
        score=intent.getIntExtra(keyScore,0);
        float scoreVal=score*4/100;
        rate.setRating(scoreVal);
        rate.setClickable(false);
        if(score>=80 && score<=100){
            message.setText(" Excellent ! Mr "+name);
        }
        else if(score >=50 && score<80){
            message.setText(" Good ! Mr "+name);
        }
        else if(score < 50 && score >= 0) {
            message.setText("Weak ! ");
            icon_result.setImageDrawable(getResources().getDrawable(R.drawable.sad_icon_result));
        }
        score_result.setText(String.valueOf(intent.getIntExtra(keyScore,0)));
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGameIntent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra(keyName,name);
                startActivity(newGameIntent);
                finish();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}