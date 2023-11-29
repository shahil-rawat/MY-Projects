package androidsamples.java.journalapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {JournalEntry.class},
        version = 1,
        exportSchema = false)

@TypeConverters(JournalTypeConverters.class)

public abstract class JournalRoomDatabase extends RoomDatabase {

    public abstract JournalEntryDao journalEntryDao();
}
