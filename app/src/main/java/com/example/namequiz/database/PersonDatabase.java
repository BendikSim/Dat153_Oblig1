package com.example.namequiz.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.namequiz.converters.Converter;
import com.example.namequiz.model.Person;

@Database(entities = {Person.class}, version = 7)
@TypeConverters({Converter.class})
public abstract class PersonDatabase extends RoomDatabase {
    private static final String DB_NAME = "db";
    private static PersonDatabase instance;

    public static PersonDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract PersonDAO personDAO();
}
