package me.fetsh.geekbrains.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements NoteSetListener {

    private final static String KEY_LAST_NOTE_ID = "lastNoteId";
    private FragmentManager fragmentManager;
    private int lastNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            showPortNote(-1);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        onNoteSet(instanceState.getInt(KEY_LAST_NOTE_ID, -1));
        Log.e("Note", "lastNoteId" + lastNoteId);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.e("Note", "lastNoteId" + lastNoteId);
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_LAST_NOTE_ID, lastNoteId);
    }

    @Override
    public void onNoteSet(int id) {
        lastNoteId = id;
        if (isLandscape()) {
            showLandNote(id);
        } else {
            showPortNote(id);
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void showPortNote(int id) {
        if (id == -1) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, new NoteListFragment())
                    .commit();
        } else {
            NoteFragment noteFragment = NoteFragment.newInstance(id);

            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.main_fragment_container, noteFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .commit();
        }
    }

    private void showLandNote(int id) {
        if (id == -1) return;
//        NoteFragment noteFragment = NoteFragment.newInstance(id);
//
//        fragmentManager.beginTransaction()
//                .setReorderingAllowed(true)
//                .replace(R.id.note, noteFragment)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .commit();
    }
}