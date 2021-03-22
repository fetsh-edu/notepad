package me.fetsh.geekbrains.notepad;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private final MutableLiveData<Note> selected = new MutableLiveData<>();
    private MutableLiveData<List<Note>> notes;

    public LiveData<List<Note>> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<>();
            notes.setValue(Note.all);
        }
        return notes;
    }
    public void select(Note note) {
        if (note != null) {
            Log.e("Note", "note selected " + note.getId());
        } else {
            Log.e("Note", "note selected " + "null");
        }
        selected.setValue(note);
    }

    public LiveData<Note> getSelected() {
        return selected;
    }
}
