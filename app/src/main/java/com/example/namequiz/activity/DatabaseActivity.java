package com.example.namequiz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.namequiz.R;
import com.example.namequiz.adapter.ListViewAdapter;
import com.example.namequiz.database.PersonDatabase;
import com.example.namequiz.helper.Helper;
import com.example.namequiz.model.Person;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;
    private Helper helper = new Helper();

    private PersonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        db = PersonDatabase.getInstance(this);

        fillList();
    }

    public void fillList(){
        listView = findViewById(R.id.listView);

        List<Person> personList = db.personDAO().getAllPersons();

        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.list_view, personList);
        listView.setAdapter(adapter);
    }

    public void goToAddActivity(View view){
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    public void goToMainActivity(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }





}