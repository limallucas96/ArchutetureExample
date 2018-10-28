package com.example.limallucas96.archithetureexample.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.limallucas96.archithetureexample.R;
import com.example.limallucas96.archithetureexample.entity.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private List<Note> notes = new ArrayList<>();

    private OnItemClickListener mLister;

    @NonNull
    @Override
    public NoteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.mTitle.setText(currentNote.getTitle());
        holder.mPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.mDescription.setText(currentNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mPriority;
        private TextView mDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.title);
            mPriority = itemView.findViewById(R.id.priority);
            mDescription = itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (mLister != null && position != RecyclerView.NO_POSITION) {
                        mLister.onItemClickListener(notes.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(Note none);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mLister = onItemClickListener;
    }
}
