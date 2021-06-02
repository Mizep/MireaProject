package ru.mirea.zimin.mireaproject.ui.hihistories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM Cell")
    List<ru.mirea.zimin.mireaproject.ui.history.Cell> getAll();
    @Query("SELECT * FROM Cell WHERE id = :id")
    ru.mirea.zimin.mireaproject.ui.history.Cell getById(long id);
    @Insert
    void insert(ru.mirea.zimin.mireaproject.ui.history.Cell cell);
    @Update
    void update(ru.mirea.zimin.mireaproject.ui.history.Cell cell);
    @Delete
    void delete(ru.mirea.zimin.mireaproject.ui.history.Cell cell);

}
