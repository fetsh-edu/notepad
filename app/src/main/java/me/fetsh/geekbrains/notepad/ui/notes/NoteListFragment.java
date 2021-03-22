package me.fetsh.geekbrains.notepad.ui.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.fetsh.geekbrains.notepad.Note;
import me.fetsh.geekbrains.notepad.R;


public class NoteListFragment extends Fragment {

    private NoteViewModel model;
    private final NotesAdapter mAdapter = new NotesAdapter();

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        initList(view);
    }

    private void selectNote(Note note) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        if (isLandscape()) {
            fragmentManager.beginTransaction()
                    .replace(R.id.note_detail, new NoteFragment())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.notes_container, new NoteFragment())
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initList(View view) {
        RecyclerView rvNotes = view.findViewById(R.id.note_list);
        model.getNotes().observe(getViewLifecycleOwner(), (mAdapter::setNotes));
        mAdapter.setOnItemClickListener((position, note) -> {
            model.select(note);
            selectNote(note);
        });
        rvNotes.setAdapter(mAdapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}