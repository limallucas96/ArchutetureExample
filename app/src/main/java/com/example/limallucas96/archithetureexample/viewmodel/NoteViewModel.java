package com.example.limallucas96.archithetureexample.viewmodel;

import android.app.Application;

import com.example.limallucas96.archithetureexample.entity.Note;
import com.example.limallucas96.archithetureexample.repository.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Note>> mNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mNotes = mRepository.getAllNotes();
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }

    public void delete(Note note) {
        mRepository.delete(note);
    }

    public void update(Note note) {
        mRepository.update(note);
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }

}
