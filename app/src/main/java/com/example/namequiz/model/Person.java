package com.example.namequiz.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Person {

    private String name;
    private Bitmap image;

    public Person(String name, Bitmap img){
        this.name = name;
        this.image = img;
    }

    public String getName() {

        return name;
    }

    public Bitmap getImage() {

        return image;
    }

}
