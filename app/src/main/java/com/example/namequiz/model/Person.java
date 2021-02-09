package com.example.namequiz.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    public int pid;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "image")
    private Bitmap image;

    // konstruktør
    public Person(String name, Bitmap image){
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(@NonNull Bitmap image) {
        this.image = image;
    }
}
