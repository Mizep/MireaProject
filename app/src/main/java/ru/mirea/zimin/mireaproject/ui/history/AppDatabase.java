package ru.mirea.zimin.mireaproject.ui.history;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ru.mirea.zimin.mireaproject.ui.history.Cell.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ru.mirea.zimin.mireaproject.ui.hihistories.HistoryDao storyDao();
}

