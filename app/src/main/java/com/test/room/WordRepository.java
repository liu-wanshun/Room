package com.test.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    LiveData<List<Word>> allWords;
    private WordDao wordDao;

    public WordRepository(Context context) {

        WordDatabase wordDatabase = WordDatabase.getWordDatabase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWords = wordDao.getAllWords();
    }


    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insertWords(Word... words) {
        new InsertWordsAsyncTask(wordDao).execute(words);
    }

    public void deleteAllWords() {
        new DeleteAllWordsAsyncTask(wordDao).execute();
    }

    public void updataWords(Word... words) {
        new UpdateWordsAsyncTask(wordDao).execute(words);

    }

    public void deleteWords(Word... words) {
        new DeleteWordsAsyncTask(wordDao).execute(words);
    }

    static class InsertWordsAsyncTask extends AsyncTask<Word, Void, Void> {
        WordDao wordDao;

        public InsertWordsAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class DeleteWordsAsyncTask extends AsyncTask<Word, Void, Void> {
        WordDao wordDao;

        public DeleteWordsAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        WordDao wordDao;

        public DeleteAllWordsAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }

    static class UpdateWordsAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public UpdateWordsAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }
}
