package ru.mirea.zimin.mireaproject.ui.history;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cell {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String text;
}
