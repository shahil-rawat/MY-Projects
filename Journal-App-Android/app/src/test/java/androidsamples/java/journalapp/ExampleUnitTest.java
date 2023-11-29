package androidsamples.java.journalapp;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test
  public void addition_isCorrect() {
    assertEquals(4, 2 + 2);
  }

  @Test
  public void testJournalEntry_constructor() {
    String title = "My Journal Entry";
    Date date = new Date();
    Date startTime = new Date();
    Date endTime = new Date();

    JournalEntry entry = new JournalEntry(title, date, startTime, endTime);

    assertNotNull(entry);
    assertNotNull(entry.getUid());
    assertEquals(title, entry.getTitle());
    assertEquals(date, entry.getDate());
    assertEquals(startTime, entry.getStartTime());
    assertEquals(endTime, entry.getEndTime());
  }

  @Test
  public void testToUUID(){
    String uuid = "9d9b0ed6-ca4f-4cf2-8e41-5a5a947ae973";
    UUID expected = UUID.fromString(uuid);
    JournalTypeConverters journalTypeConverters = new JournalTypeConverters();
    UUID result = journalTypeConverters.toUUID(uuid);
    assertEquals(expected, result);
  }
}