package com.example.quizapplication;

import android.provider.BaseColumns;

public class QuizContract {
    public static  final class QuestionEntry implements BaseColumns{

        public static  final String   TABLE_NAME="questions";
        public static  final String   COLUMN_NAME="name";
        public static  final String   COLUMN_OPTION_1="option_1";
        public static  final String   COLUMN_OPTION_2="option_2";
        public static  final String   COLUMN_OPTION_3="option_3";
        public static  final String   COLUMN_OPTION_4="option_4";
        public static  final String   COLUMN_OPTION_5="option_5";
        public static  final String   COLUMN_ANSWER="answer";

    }


}
