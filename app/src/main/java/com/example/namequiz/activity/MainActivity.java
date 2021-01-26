package com.example.namequiz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.namequiz.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuiz(View view){
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);
    }

    public void addPerson(View view){
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    public void database(View view){
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }

}