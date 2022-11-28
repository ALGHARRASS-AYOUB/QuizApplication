package com.example.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizDBHelper extends SQLiteOpenHelper {

    private   static final String DATABASE_NAME="question.db";
    private static final Integer DATABASE_VERSION=1;
    private  SQLiteDatabase db;

    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
         db=this.getReadableDatabase();
         this.removeDate();
        this.fillDB(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_QUESTION_REQ=String.format("CREATE TABLE  %s ( %s INTEGER PRIMARY KEY," +
                "%s TEXT," +
                " %s TEXT," +
                " %s TEXT," +
                " %s TEXT," +
                " %s TEXT," +
                " %s TEXT," +
                " %s TEXT" +
                ");",QuizContract.QuestionEntry.TABLE_NAME,
                    QuizContract.QuestionEntry._ID,
                QuizContract.QuestionEntry.COLUMN_NAME,
                QuizContract.QuestionEntry.COLUMN_OPTION_1,
                QuizContract.QuestionEntry.COLUMN_OPTION_2,
                QuizContract.QuestionEntry.COLUMN_OPTION_3,
                QuizContract.QuestionEntry.COLUMN_OPTION_4,
                QuizContract.QuestionEntry.COLUMN_OPTION_5,
                QuizContract.QuestionEntry.COLUMN_ANSWER
                );

            db.execSQL(CREATE_TABLE_QUESTION_REQ);

            Log.v("status","database has been created ...");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+QuizContract.QuestionEntry.TABLE_NAME+" ;");
            onCreate(db);

        }

    private   void addQuestion(SQLiteDatabase db,Question q){
        String name=q.getQuestion_name();
        String answer=q.getAnswer();

        ContentValues cv=new ContentValues();
        cv.put(QuizContract.QuestionEntry.COLUMN_NAME,name);
        cv.put(QuizContract.QuestionEntry.COLUMN_OPTION_1,q.getOptions().get(0));
        cv.put(QuizContract.QuestionEntry.COLUMN_OPTION_2,q.getOptions().get(1));
        cv.put(QuizContract.QuestionEntry.COLUMN_OPTION_3,q.getOptions().get(2));
        cv.put(QuizContract.QuestionEntry.COLUMN_OPTION_4,q.getOptions().get(3));
        cv.put(QuizContract.QuestionEntry.COLUMN_OPTION_5,q.getOptions().get(4));
        cv.put(QuizContract.QuestionEntry.COLUMN_ANSWER,answer);
        db.insert(QuizContract.QuestionEntry.TABLE_NAME,null,cv);
    }

    public void fillDB(SQLiteDatabase db){
     List<Question> questions = new ArrayList<Question>(Arrays.asList(
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("who has invented the blockchain?",Arrays.asList("elon musk", "bill gates", "satushi nakamoto", "ayoub", "natasha rabilla"), "satushi nakamoto"),
                new Question("which is the first programming language ?",Arrays.asList("java", "c++", "c", "assembly", "short code"), "short code"),
                new Question("In which year the c++ programming language was created ?",Arrays.asList("1997", "1979", "1983", "2001", "1999"), "1983"),
                new Question("wich from the next options is not aprogramming language ?",Arrays.asList("Actor", "coq", "Eiffel", "brainfuck", "rawdon"), "rawdon"),
                new Question("Which of these is not a core data type ?",Arrays.asList("List", "Dictionary", "Tuple", "Class", "Collection"), "Class"),
                new Question("Which of the following is a Python tuple ?",Arrays.asList("[1,2,3,4]", "(1,2,3,4)", "{1,2,3,4}", "{}", "[\"a\",\"b\"]"), "(1,2,3,4)"),
                new Question("what the OOP means ?",Arrays.asList("Object Organized Progress", "Object Oriented Programming", "Object Oriented Progress", "Ordered Objects Poping", "none from above options"), "Object Oriented Programming"),
                new Question("What is the built in library function to compare two strings ?",Arrays.asList("string_cmp()", "strcmp()", "equals()", "str_compare()", "String.compare()"), "strcmp()"),
                new Question("What does P2P stand for in blockchain ?",Arrays.asList("Password to Password", "Peer to Peer", " Product to Product", " Private to Public", "none of the above"), "Peer to Peer")
                ));

        for (Question q: questions) {
            addQuestion(this.db,q);
        }

    }

    public  ArrayList<Question> getAllQuestions(){
        ArrayList<Question> allQs=new ArrayList<Question>();
        Cursor cursor=this.db.rawQuery("SELECT * FROM "+QuizContract.QuestionEntry.TABLE_NAME,null);

        if(cursor.moveToFirst()){
            Log.v("the get count ==>",String.valueOf(cursor.getCount()));
            do {
                ArrayList<String> options=new ArrayList<>();
                Question q=new Question();
                q.setQuestion_name(cursor.getString(1));
                options.add(cursor.getString(2));
                options.add(cursor.getString(3));
                options.add(cursor.getString(4));
                options.add(cursor.getString(5));
                options.add(cursor.getString(6));
                q.setAnswer(cursor.getString(7));
                q.setOptions(options);
                allQs.add(q);
            }while (cursor.moveToNext());
        }
        return allQs;
    }

    public void removeDate(){
        this.db.execSQL("DELETE FROM "+QuizContract.QuestionEntry.TABLE_NAME);
    }

}
