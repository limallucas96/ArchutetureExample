package com.example.limallucas96.archithetureexample;

import android.os.Bundle;
import android.widget.Toast;

import com.example.limallucas96.archithetureexample.entity.Note;
import com.example.limallucas96.archithetureexample.viewmodel.NoteViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel mNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteRecyclerViewAdapter adapter = new NoteRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update recycle view
//                Toast.makeText(MainActivity.this, "onChange", Toast.LENGTH_SHORT).show();
                adapter.setNotes(notes);
            }
        });
    }
}
