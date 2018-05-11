package com.example.fauza.daoexercise.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fauza.daoexercise.entity.People;

import java.util.List;

@Dao
public interface PeopleDao {
    @Query("SELECT * FROM people")
    List<People> getAll();

    @Query("SELECT * FROM people WHERE first_name LIKE :firstName AND last_name LIKE:lastName")
    People findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) FROM people WHERE first_name LIKE :first_name AND last_name LIKE :last_name")
    int getNumberOfRows(String first_name, String last_name);

    @Insert
    void insertAll(People... people);

    @Delete
    void delete(People people);
}
