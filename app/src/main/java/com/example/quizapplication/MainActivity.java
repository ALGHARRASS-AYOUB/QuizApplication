package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    String name;
    TextInputEditText name_input;
    MaterialButton start_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_input=findViewById(R.id.name_edit_text);
        start_button=findViewById(R.id.start_session_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name=name_input.getText().toString();
                // Perform action on click
                Intent quizIntent=new Intent(getApplicationContext(),QuizActivity.class);

                String key;
                Resources rs=getResources();
                key=rs.getString(R.string.name);

                if(name.isEmpty() || name.equals("")){
                    Toast.makeText(MainActivity.this, "enter your name please", Toast.LENGTH_SHORT).show();
                }
                else{
                quizIntent.putExtra(key,name);
                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(quizIntent);
                }
            }
        });

    }
}