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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteRecyclerViewAdapter extends ListAdapter<Note, NoteRecyclerViewAdapter.ViewHolder> {

//    private List<Note> notes = new ArrayList<>();

    private OnItemClickListener mLister;

    public NoteRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.mTitle.setText(currentNote.getTitle());
        holder.mPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.mDescription.setText(currentNote.getDescription());
    }

    public Note getNoteAt(int position) {
        return getItem(position);
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
                        mLister.onItemClickListener(getItem(position));
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
