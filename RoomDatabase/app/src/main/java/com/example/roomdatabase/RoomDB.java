package com.example.roomdatabase;

// Add Database entity

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //Create database instance
    private static RoomDB database;

    // Define Database name
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getDatabase(Context context) {
        // Check condition

        if(database == null) {
            // When database is null
            // Initialize database
            database = Room.databaseBuilder(context.getApplicationContext()
                    ,RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        //Return database
        return database;
    }

    // Create Dao
    public abstract MainDao mainDao();
}
