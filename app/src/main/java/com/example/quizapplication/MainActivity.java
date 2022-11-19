package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    String name;
    MaterialButton start_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name_edit_text).toString();
        start_button=findViewById(R.id.start_session_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent quizIntent=new Intent(getApplicationContext(),QuizActivity.class);

                if(name.isEmpty() || name==null){
                    Toast.makeText(MainActivity.this, "enter your name please", Toast.LENGTH_SHORT).show();
                }
                quizIntent.putExtra(getString(R.string.name),name);
                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(quizIntent);
            }
        });

    }
}