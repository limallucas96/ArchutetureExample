package com.example.limallucas96.archithetureexample.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.limallucas96.archithetureexample.R;

import androidx.appcompat.app.AppCompatActivity;


public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.limallucas96.archithetureexample.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.limallucas96.archithetureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRPTION = "com.example.limallucas96.archithetureexample.EXTRA_DESCRPTION";
    public static final String EXTRA_PRIORITY = "com.example.limallucas96.archithetureexample.EXTRA_PRIORITY";

    private EditText mTitle;
    private EditText mDescription;
    private NumberPicker mPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mTitle = findViewById(R.id.title);
        mDescription = findViewById(R.id.description);
        mPicker = findViewById(R.id.picker);

        mPicker.setMinValue(1);
        mPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        if (getIntent().hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            mTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
            mDescription.setText(getIntent().getStringExtra(EXTRA_DESCRPTION));
            mPicker.setValue(getIntent().getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveNote() {
        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        int pririoty = mPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRPTION, description);
        data.putExtra(EXTRA_PRIORITY, pririoty);

        if (getIntent().hasExtra(EXTRA_ID)) {
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ID, id);
            }
        }

        setResult(RESULT_OK, data); //indicates if the input was success or not
        finish();
    }
}
