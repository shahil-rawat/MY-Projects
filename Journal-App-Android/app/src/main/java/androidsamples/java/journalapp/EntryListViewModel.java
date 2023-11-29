package androidsamples.java.journalapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.UUID;

public class EntryListViewModel extends ViewModel {
    private final JournalRepository mRepository;
    public EntryListViewModel() {
        mRepository=JournalRepository.getInstance();
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return mRepository.getAllEntries();
    }
}
