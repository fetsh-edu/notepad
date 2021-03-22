package me.fetsh.geekbrains.notepad.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import me.fetsh.geekbrains.notepad.R;

public class NoteFragment extends Fragment {

    public NoteFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NoteViewModel model = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        model.getSelected().observe(getViewLifecycleOwner(), note -> {
            TextView tv = ((TextView) view.findViewById(R.id.fragment_note_id));
            if (note == null) {
                tv.setText("No note selected");
            } else {
                tv.setText(note.getDescription());
            }
        });
    }
}