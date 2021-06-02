package ru.mirea.zimin.mireaproject.ui.history;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static ru.mirea.zimin.mireaproject.ui.history.App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static ru.mirea.zimin.mireaproject.ui.history.App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}