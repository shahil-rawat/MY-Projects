package androidsamples.java.journalapp;

import androidx.lifecycle.ViewModel;

import java.util.Date;

public class SharedViewModel extends ViewModel {
    private Date mDate;
    public SharedViewModel() {
        mDate = new Date();
    }
    public Date getDate(){
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
}
