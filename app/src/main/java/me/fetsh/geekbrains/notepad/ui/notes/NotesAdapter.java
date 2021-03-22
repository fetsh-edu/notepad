package me.fetsh.geekbrains.notepad.ui.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.fetsh.geekbrains.notepad.Note;
import me.fetsh.geekbrains.notepad.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    public interface OnNoteClickListener {
        void onNoteClicked(int position, Note note);
    }

    private List<Note> mNotes;
    private OnNoteClickListener onNoteClickListener;

    public void setNotes(List<Note> notes) {
        this.mNotes = notes;
    }
    public void setOnItemClickListener(OnNoteClickListener listener) {
        onNoteClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View distanceView = inflater.inflate(R.layout.note_list_item, parent, false);

        return new ViewHolder(distanceView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.itemTitle.setText(note.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.note_list_item_title);
            itemView.setOnClickListener(v -> {
                if (onNoteClickListener != null)
                    onNoteClickListener.onNoteClicked(getAdapterPosition(), mNotes.get(getAdapterPosition()));
            });

        }
    }
}
