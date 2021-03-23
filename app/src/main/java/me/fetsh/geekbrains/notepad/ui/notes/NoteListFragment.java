package me.fetsh.geekbrains.notepad.ui.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.fetsh.geekbrains.notepad.R;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class NoteListFragment extends Fragment {

    private NoteViewModel mNoteViewModel;
    private final NotesAdapter mAdapter = new NotesAdapter();

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNoteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        initList(view);
    }

    private void updateFragments() {
        if (!isLandscape()) findNavController(this).navigate(R.id.action_list_to_note);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initList(View view) {
        RecyclerView rvNotes = view.findViewById(R.id.note_list);
        mNoteViewModel.getNotes().observe(getViewLifecycleOwner(), (mAdapter::setNotes));
        mAdapter.setOnItemClickListener((position, note) -> {
            mNoteViewModel.select(note);
            updateFragments();
        });
        rvNotes.setAdapter(mAdapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}