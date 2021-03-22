package me.fetsh.geekbrains.notepad;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteFragment extends Fragment {

    private static final String ARG_NOTE_ID = "noteId";
    private NoteSetListener noteSetListener;
    private int id;

    public static NoteFragment newInstance(int id) {
        NoteFragment f = new NoteFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_NOTE_ID, id);
        f.setArguments(args);
        return f;
    }

    public NoteFragment() {
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
    public void onStop() {
        super.onStop();
        noteSetListener.onNoteSet(-1);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        noteSetListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_NOTE_ID);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        TextView description = view.findViewById(R.id.fragment_note_id);

        Note.findById(id).ifPresent(n -> description.setText(n.getDescription()));
        return view;
    }
}