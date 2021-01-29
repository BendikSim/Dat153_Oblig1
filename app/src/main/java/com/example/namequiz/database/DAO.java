package com.example.namequiz.database;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.PersistableBundle;

import com.example.namequiz.R;
import com.example.namequiz.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DAO extends Application {
    private static List<Person> personList = new ArrayList<>();
    private static int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Person p1 = new Person("Jon", BitmapFactory.decodeResource(this.getResources(), R.drawable.jon));
        personList.add(p1);
    }

    public void addPerson(Person p){
        personList.add(p);
    }

    public static List<Person> getPersonList() {
        return new ArrayList(personList);
    }

    public static Bitmap getBitmap(String n){
        Bitmap bitmap = null;
        boolean found = false;
        for(int i = 0; i < personList.size() && !found; i++){
            if(personList.get(i).getName().equals(n)){
                bitmap = personList.get(i).getImage();
                found = true;
            }
        }
        return bitmap;
    }
}
