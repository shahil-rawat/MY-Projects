package androidsamples.java.journalapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.UUID;

public class EntryDetailsViewModel extends ViewModel {
    private final MutableLiveData<UUID> idCurrentData = new MutableLiveData<>();
    private final JournalRepository mRepository;

    public EntryDetailsViewModel()
    {
        mRepository = JournalRepository.getInstance();
    }

    void addNewEntry(JournalEntry entry)
    {
        mRepository.insert(entry);
    }

    void deleteEntry(JournalEntry entry)
    {
        mRepository.delete(entry);
    }

    LiveData<JournalEntry> getEntryLiveData()
    {
        return Transformations.switchMap(idCurrentData, mRepository::getEntry);
    }

    void saveExistingEntry(JournalEntry entry)
    {
        mRepository.update(entry);
    }

    void loadEntry(UUID entryId)
    {
        idCurrentData.setValue(entryId);
    }

}
