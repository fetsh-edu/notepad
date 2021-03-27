package me.fetsh.geekbrains.notepad.ui.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import me.fetsh.geekbrains.notepad.ListLiveData;
import me.fetsh.geekbrains.notepad.Note;

public class NoteViewModel extends ViewModel {
    private final MutableLiveData<Note> noteToShow = new MutableLiveData<>();
    private final MutableLiveData<Note> noteToEdit = new MutableLiveData<>();
    private ListLiveData<Note> notes;

    public ListLiveData<Note> getNotes() {
        if (notes == null) {
            notes = new ListLiveData<>(null);
            notes.populate(Note.all);
        }
        return notes;
    }

    public void setNoteToShow(Note note) {
        noteToShow.setValue(note);
    }

    public void setNoteToEdit(Note note) {
        noteToEdit.setValue(note);
    }

    public LiveData<Note> getNoteToShow() {
        return noteToShow;
    }

    public LiveData<Note> getNoteToEdit() {
        return noteToEdit;
    }

    public Note getNote(int id) {
        if (getNotes().getValue() == null) return null;
        return getNotes().getValue().getList().stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }


}
