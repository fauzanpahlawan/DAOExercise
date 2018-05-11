package com.example.fauza.daoexercise.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.fauza.daoexercise.dao.PeopleDao;
import com.example.fauza.daoexercise.entity.People;

@Database(entities = {People.class}, version = 1)
public abstract class PeopleDatabase extends RoomDatabase {
    private static final String DB_NAME = "people-database";

    private static PeopleDatabase INSTANCE;

    public abstract PeopleDao getPeopleDao();

    public static PeopleDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, PeopleDatabase.class, DB_NAME).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
