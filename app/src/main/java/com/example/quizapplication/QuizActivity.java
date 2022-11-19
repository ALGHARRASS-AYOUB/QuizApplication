package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    String guestname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        guestname=this.getIntent().getStringExtra(getString(R.string.name));
        Toast.makeText(this, "hello"+guestname, Toast.LENGTH_SHORT).show();
    }
}