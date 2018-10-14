package com.example.limallucas96.archithetureexample.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.limallucas96.archithetureexample.dao.NoteDAO;
import com.example.limallucas96.archithetureexample.database.AppDatabase;
import com.example.limallucas96.archithetureexample.entity.Note;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {
    private NoteDAO mNoteDAO;
    private LiveData<List<Note>> mNotes;

    public Repository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        mNoteDAO = database.noteDAO();
        mNotes = mNoteDAO.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(mNoteDAO).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(mNoteDAO).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(mNoteDAO).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(mNoteDAO).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO mNoteDAO;

        private InsertNoteAsyncTask(NoteDAO mNoteDAO) {
            this.mNoteDAO = mNoteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDAO.insert(notes[0]);
            return null;
        }
    }

       private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO mNoteDAO;

        private UpdateNoteAsyncTask(NoteDAO mNoteDAO) {
            this.mNoteDAO = mNoteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDAO.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO mNoteDAO;

        private DeleteNoteAsyncTask(NoteDAO mNoteDAO) {
            this.mNoteDAO = mNoteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDAO.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDAO mNoteDAO;

        private DeleteAllNoteAsyncTask(NoteDAO mNoteDAO) {
            this.mNoteDAO = mNoteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDAO.deleteAllNotes();
            return null;
        }
    }

}
