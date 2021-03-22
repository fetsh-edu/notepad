package me.fetsh.geekbrains.notepad;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NoteListFragment extends Fragment {

    private NoteSetListener noteSetListener;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            noteSetListener = (NoteSetListener) getActivity();
        } catch(Exception e) {
            Log.e("Notepad", "Couldn't set noteSetListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        noteSetListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        noteSetListener = (NoteSetListener) getActivity();
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        for(Note note: Note.all){
            TextView tv = new TextView(getContext());
            tv.setText(note.getTitle());
            tv.setTextSize(30);
            tv.setOnClickListener(v -> showNote(note.getId()));
            layoutView.addView(tv);
        }
    }

    private void showNote(final int id) {
        if (noteSetListener != null) {
            noteSetListener.onNoteSet(id);
        }
    }
}