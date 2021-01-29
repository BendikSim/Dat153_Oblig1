package com.example.namequiz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.namequiz.R;
import com.example.namequiz.adapter.ListViewAdapter;
import com.example.namequiz.database.DAO;
import com.example.namequiz.helper.Helper;
import com.example.namequiz.model.Person;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;
    private Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        fillList();
    }

    public void fillList(){
        listView = findViewById(R.id.listView);

        ArrayList<Person> personList = (ArrayList<Person>) DAO.getPersonList();
        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.list_view, personList);
        listView.setAdapter(adapter);

    }

    public void goToAddActivity(View view){
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }





}