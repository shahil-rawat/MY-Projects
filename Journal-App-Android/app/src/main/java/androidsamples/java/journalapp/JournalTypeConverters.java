package androidsamples.java.journalapp;

import androidx.room.TypeConverter;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class JournalTypeConverters {
    @TypeConverter
    public UUID toUUID(@NotNull String uuid){
        return UUID.fromString(uuid);
    }

    @TypeConverter
    public String fromUUID(@NotNull UUID uuid){
        return uuid.toString();
    }

    @TypeConverter
    public Date toDate(String date) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, MMM dd, yyyy HH:ss");
        Date d = dateFormatter.parse(date);
        return d;
    }

    @TypeConverter
    public String fromDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, MMM dd, yyyy HH:ss");
        return dateFormatter.format(date);
    }

}
