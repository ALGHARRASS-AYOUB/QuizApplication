package com.example.quizapplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {


    String question_name;
    List<String> options=new ArrayList<>(5);
    String answer;


    public Question(){}


    public Question(String question_name, List<String> options, String answer) {
        this.question_name = question_name;
        this.options = options;
        this.answer = answer;
    }


    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
