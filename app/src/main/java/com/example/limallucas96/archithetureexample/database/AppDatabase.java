package com.example.limallucas96.archithetureexample.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.limallucas96.archithetureexample.dao.NoteDAO;
import com.example.limallucas96.archithetureexample.entity.Note;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //This class must be a singleton class, so we can access database from anywhere in our code
    private static AppDatabase instance;

    //Method without body. With this method we will access our DAO
    public abstract NoteDAO noteDAO();

    //synchronized because we will use only one instance of our AppDatabase object (only one single thread)
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) { // We will only instance the database if we dont have one already
            instance = Room.databaseBuilder(context.getApplicationContext(), //Cant call new because its an abstract class so we use Builder
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        //if instance is not null, we only return existing instance of database
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAysncTask(instance).execute();
        }
    };

    private static class PopulateDbAysncTask extends AsyncTask<Void, Void, Void> {

        private NoteDAO mNoteDAO;

        private PopulateDbAysncTask(AppDatabase database) {
            mNoteDAO = database.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDAO.insert(new Note("Title 1", "Description 1", 1));
            mNoteDAO.insert(new Note("Title 2", "Description 2", 2));
            mNoteDAO.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }

}
