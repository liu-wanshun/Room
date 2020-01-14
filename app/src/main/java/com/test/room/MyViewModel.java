package com.test.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private WordRepository wordRepository;

    public MyViewModel(@NonNull Application application) {

        super(application);
        wordRepository = new WordRepository(application);

    }

    public LiveData<List<Word>> getAllWords() {
        return wordRepository.getAllWords();
    }

    public void insertWords(Word... words) {
        wordRepository.insertWords(words);
    }

    public void deleteAllWords() {
        wordRepository.deleteAllWords();
    }

    public void updataWords(Word... words) {
        wordRepository.updataWords(words);

    }

    public void deleteWords(Word... words) {
        wordRepository.deleteWords(words);
    }


}
