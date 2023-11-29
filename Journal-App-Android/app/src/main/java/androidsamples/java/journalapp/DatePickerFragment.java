package androidsamples.java.journalapp;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment {
  private OnDialogCloseListener receiver;
  private static final String KEY = "DatePickerFragment";
  private Date date;
  private SharedViewModel mSharedViewModel;

  public DatePickerFragment(OnDialogCloseListener l) {
    receiver=l;
  }

  @NonNull
  public static DatePickerFragment newInstance(Date d, OnDialogCloseListener l) {
    DatePickerFragment df = new DatePickerFragment(l);
    Bundle args = new Bundle();
    df.setArguments(args);
    args.putSerializable(KEY, d);
    return df;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);
    Bundle tempBundle;
    if (savedInstanceState == null) {
      tempBundle = getArguments();
    } else {
      tempBundle = savedInstanceState;
    }
    date = (Date) tempBundle.getSerializable(KEY);
    mSharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

    int dy, mn, yr;
    Calendar cal = Calendar.getInstance();
    if(date!=null){
      cal.setTime(date);
      dy=cal.get(Calendar.DAY_OF_MONTH);
      mn=cal.get(Calendar.MONTH);
      yr = cal.get(Calendar.YEAR);
    }
    else{;
      dy=cal.get(Calendar.DAY_OF_MONTH);
      mn= cal.get(Calendar.MONTH);
      yr = cal.get(Calendar.YEAR);
    }
    return new DatePickerDialog(requireContext(), (dp, year, month, day) -> {
      Calendar cald = Calendar.getInstance();
      cald.set(Calendar.DAY_OF_MONTH, day);
      cald.set(Calendar.MONTH, month);
      cald.set(Calendar.YEAR, year);
      DateFormat dF = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.ENGLISH);
      String selectedDate = dF.format(cald.getTime());
      SimpleDateFormat dateFormat2 = new SimpleDateFormat("E, MMM dd, yyyy");
      try {
        mSharedViewModel.setDate(dateFormat2.parse(selectedDate));
      } catch (Exception e) {
        e.printStackTrace();
      }
      receiver.onDateDialogClose();
      dismiss();
    }, yr, mn, dy);
  }
}
