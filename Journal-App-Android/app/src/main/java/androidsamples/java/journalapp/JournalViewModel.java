package androidsamples.java.journalapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class JournalViewModel extends ViewModel {
    private final JournalRepository mRepository;

    public JournalViewModel() {
        mRepository=JournalRepository.getInstance();
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return mRepository.getAllEntries();
    }
    public void insert(JournalEntry entry){
        mRepository.insert(entry);
    }
}
