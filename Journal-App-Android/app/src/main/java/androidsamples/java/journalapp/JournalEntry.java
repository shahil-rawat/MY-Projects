package androidsamples.java.journalapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "journal_table")
public class JournalEntry {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NotNull
    private UUID mUid;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "startTime")
    private Date mStartTime;

    @ColumnInfo(name = "endTime")
    private Date mEndTime;

    public JournalEntry(@NotNull String title, Date date, Date startTime, Date endTime){
        mUid=UUID.randomUUID();
        mStartTime=startTime;
        mEndTime=endTime;
        mTitle=title;
        mDate=date;
    }

    public UUID getUid() { return mUid; }

    public Date getDate() {
        return mDate;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setUid(UUID id) {
        mUid = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setStartTime(Date startTime) {
        mStartTime = startTime;
    }

    public void setEndTime(Date endTime) {
        mEndTime = endTime;
    }
}
