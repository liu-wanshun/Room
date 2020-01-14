package com.test.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "english_word")
    private String word;

    @ColumnInfo(name = "chinese_word")
    private String meaning;

    @ColumnInfo(name = "meaning_invisible")
    private boolean meaning_invisible;

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public boolean isMeaning_invisible() {
        return meaning_invisible;
    }

    public void setMeaning_invisible(boolean meaning_invisible) {
        this.meaning_invisible = meaning_invisible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
