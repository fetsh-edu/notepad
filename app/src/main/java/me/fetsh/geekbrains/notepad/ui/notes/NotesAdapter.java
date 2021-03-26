package me.fetsh.geekbrains.notepad.ui.notes;

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

    private int selectedPos = RecyclerView.NO_POSITION;
    private int selectedNoteId = -1;

    public interface OnNoteClickListener {

        void onNoteClicked(int position, Note note);
    }
    private List<Note> mNotes;

    private OnNoteClickListener onNoteClickListener;

    public void setNotes(List<Note> notes) {
        this.mNotes = notes;
    }

    public void setSelectedNoteId(int selectedNoteId) {
        this.selectedNoteId = selectedNoteId;
    }
    public void setOnNoteClickListener(OnNoteClickListener listener) {
        onNoteClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.note_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.itemTitle.setText(note.getTitle());
        holder.itemDescription.setText(note.getDescription());
        if (note.getId() == selectedNoteId) selectedPos = position;
        holder.itemView.findViewById(R.id.note_selected).setVisibility(selectedPos == position ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public TextView itemDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.note_list_item_title);
            itemDescription = itemView.findViewById(R.id.note_list_item_description);
            itemView.setOnClickListener(v -> {
                notifyItemChanged(selectedPos);
                selectedPos = getAdapterPosition();
                notifyItemChanged(selectedPos);
                if (onNoteClickListener != null)
                    onNoteClickListener.onNoteClicked(getAdapterPosition(), mNotes.get(getAdapterPosition()));
            });

        }
    }
}
