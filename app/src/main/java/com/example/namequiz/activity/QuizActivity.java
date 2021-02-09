package com.example.namequiz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namequiz.R;
import com.example.namequiz.database.PersonDatabase;
import com.example.namequiz.model.Person;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ImageView image;
    private Button button;
    private TextView name;
    private TextView scoreView;
    private int score;
    private int totalScore;

    private Person person;
    private Iterator<Person> iterator;
    private PersonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = PersonDatabase.getInstance(this);

        // Score variables
        score = 0;
        totalScore = 0;

        // Views
        image = findViewById(R.id.quiz_image);
        name = findViewById(R.id.guess);
        button = findViewById(R.id.submit_Btn);
        scoreView = findViewById(R.id.quizScore);

        button.setOnClickListener(x -> guessPerson());

        List<Person> personList = db.personDAO().getAllPersons();

        Collections.shuffle(personList);
        iterator = personList.iterator();

        nextQuestion();
    }

    public void nextQuestion(){
        if(iterator.hasNext()){
            person = iterator.next();
            image.setImageBitmap(person.getImage());
            name.setText("");
        }else{
            image.setImageBitmap(null);
            name.setText("");
            name.setHint("");
            name.clearFocus();
            name.setBackgroundResource(android.R.color.transparent);
            scoreView.setText("Final score: " + score + "/" + totalScore);
            button.setText("End quiz");
            button.setOnClickListener(x -> finishQuiz());
            //name.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
            name.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

    private void guessPerson() {
        totalScore++;
        String guess = name.getText().toString();
        String answer = person.getName();

        if(guess.toLowerCase().equals(answer.toLowerCase())){
            score++;
            Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Wrong answer, correct name was "+ answer + ".", Toast.LENGTH_SHORT).show();
        }
        scoreView.setText(score + "/" + totalScore);
        name.onEditorAction(EditorInfo.IME_ACTION_DONE);
        nextQuestion();
    }

    public void finishQuiz(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}