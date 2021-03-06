package com.ita.tinnews;

import android.app.Application;

import androidx.room.Room;

import com.ita.tinnews.database.TinNewsDatabase;

public class TinNewsApplication extends Application {

    private TinNewsDatabase database;



    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(this, TinNewsDatabase.class, "tinnews_db").build();
    }

    public TinNewsDatabase getDatabase() {
        return database;
    }
}
