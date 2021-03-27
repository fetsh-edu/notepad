package me.fetsh.geekbrains.notepad;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Note implements Parcelable {

    private static final AtomicInteger fakeId = new AtomicInteger(0);

    private final int id;
    private String title;
    private String description;
    private LocalDateTime dateTime;


    public static List<Note> all = new ArrayList<>(Arrays.asList(
            new Note("note 3", "Some text", LocalDateTime.now()),
            new Note("note 4", "Some 4 text", LocalDateTime.now()),
            new Note("note 2", "Some description", LocalDateTime.now().minusHours(2)),
            new Note("note 0", "A material metaphor is the unifying theory of a rationalized space and a system of motion." +
                             "The material is grounded in tactile reality, inspired by the study of paper and ink, yet " +
                             "technologically advanced and open to imagination and magic.\n" +
                             "Surfaces and edges of the material provide visual cues that are grounded in reality. The " +
                             "use of familiar tactile attributes helps users quickly understand affordances. Yet the " +
                             "flexibility of the material creates new affordances that supercede those in the physical " +
                             "world, without breaking the rules of physics.\n", LocalDateTime.now().minusDays(4)),
            new Note("note 1", "Some other description", LocalDateTime.now().minusDays(2))
    ));

    public Note(String title, String description, LocalDateTime dateTime) {
        this.id = fakeId.getAndIncrement();
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
    }

    public static Optional<Note> findById(int id) {
        return all.stream().filter(n -> n.id == id).findFirst();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        dateTime = LocalDateTime.parse(in.readString());
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(dateTime.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
