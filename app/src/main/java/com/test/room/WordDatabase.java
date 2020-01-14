package com.test.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table Word add column meaning_invisible integer  not null default 0");
        }
    };
    private static WordDatabase wordDatabase;

    public synchronized static WordDatabase getWordDatabase(Context context) {
        if (wordDatabase == null) {
            wordDatabase = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, " word_database")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return wordDatabase;
    }

    public abstract WordDao getWordDao();
}
