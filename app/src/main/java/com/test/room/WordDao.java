package com.test.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insertWords(Word... words);

    @Delete
    void deleteWords(Word... words);

    @Update
    void updateWords(Word... words);

    @Query("delete from Word")
    void deleteAllWords();

    @Query("select * from Word order by id desc")
    LiveData<List<Word>> getAllWords();
}
