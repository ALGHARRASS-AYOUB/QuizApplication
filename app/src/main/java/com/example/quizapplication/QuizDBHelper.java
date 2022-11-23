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
                new Question("who has invented the blockchain?",Arrays.asList("elon musk", "bill gates", "satushi nakamoto", "ayoub", "natasha rabilla"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac"),
                new Question("wich chioce is the compiler of java ?",Arrays.asList("javac", "ansi89", "ansi99", "gcc", "sdk"), "javac")
                ));

        for (Question q: questions) {
            addQuestion(this.db,q);
        }

    }

    public  HashSet<Question> getAllQuestions(){
        HashSet<Question> allQs=new HashSet<>();
        ArrayList<String> options=new ArrayList<>();
        Cursor cursor=this.db.rawQuery("SELECT * FROM "+QuizContract.QuestionEntry.TABLE_NAME,null);

        if(cursor.moveToFirst()){
            Log.v("the get count ==>",String.valueOf(cursor.getCount()));
            do {
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
