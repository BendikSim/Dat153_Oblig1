package com.example.namequiz.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.namequiz.R;
import com.example.namequiz.adapter.ListViewAdapter;
import com.example.namequiz.database.DAO;
import com.example.namequiz.helper.Helper;
import com.example.namequiz.model.Person;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        //ArrayList<Person> personList = (ArrayList<Person>) DAO.getPersonList();

        // Create adapter with personList
        fillList();
    }

    public void fillList(){
        listView = (ListView) findViewById(R.id.listView);

        //ArrayList<Person> database = ((Helper) this.getApplication()).getPersonList();
        ArrayList<Person> personList = (ArrayList<Person>) DAO.getPersonList();
        ListViewAdapter adapter = new ListViewAdapter(this, personList);
        listView.setAdapter(adapter);

    }

    public void goToAddActivity(View view){
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }





}