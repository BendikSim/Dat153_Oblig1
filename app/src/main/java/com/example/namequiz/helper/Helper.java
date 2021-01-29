package com.example.namequiz.helper;

import android.app.Application;
import android.graphics.Bitmap;

import com.example.namequiz.model.Person;

import java.util.ArrayList;

public class Helper extends Application {
    private ArrayList<Person> personList = new ArrayList<>();
    private boolean Start = true;
    private Bitmap selectedImg;

    // Getter for personlist
    public ArrayList<Person> getPersonList(){
        return personList;
    }

    public void addPerson(Person p){
        this.personList.add(p);
    }

    // Getter for image
    public Bitmap getSelectedImg(){
        return selectedImg;
    }

    public void addSelectedImg(Bitmap bitmap) {
        this.selectedImg = bitmap;
    }

}
