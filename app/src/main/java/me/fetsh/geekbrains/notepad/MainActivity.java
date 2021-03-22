package me.fetsh.geekbrains.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new NoteListFragment())
                    .commit();
        }
        NoteViewModel model = new ViewModelProvider(this).get(NoteViewModel.class);
        model.getSelected().observe(this, this::selectNote);
    }

    private void selectNote(Note note) {
        if (isLandscape()) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction()
                    .replace(R.id.note_list, new NoteListFragment())
                    .replace(R.id.note_detail, new NoteFragment())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new NoteFragment())
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}