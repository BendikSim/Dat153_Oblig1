package com.example.namequiz.database;

import android.app.Application;
import android.os.PersistableBundle;

import com.example.namequiz.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DAO extends Application {
    private static List<Person> personList = new ArrayList<>();
    private static int count = 0;

    @Override
    public void onCreate() {

        super.onCreate();
    }

    public void addPerson(Person p){
        personList.add(p);
    }

    public static List<Person> getPersonList() {
        return new ArrayList(personList);
    }
}
