package com.example.namequiz.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.namequiz.model.Person;

import java.util.List;

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM person")
    List<Person> getAllPersons();

    @Insert
    void addPerson(Person person);

    @Delete
    void removePerson(Person person);

}
