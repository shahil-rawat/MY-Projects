package androidsamples.java.journalapp;

import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import android.app.TimePickerDialog;
import android.os.Bundle;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TimePickerFragment extends DialogFragment {
  private static final String KEY_1 = "TimePickerFragment";
  private static final String KEY_2 = "TimeSelector";
  private Date time;
  private OnDialogCloseListener receiver;
  private SharedViewModel mSharedViewModel;
  public TimePickerFragment(OnDialogCloseListener l) {
    receiver = l;
  }


  @NonNull
  public static TimePickerFragment newInstance(Date t, String timeSelector, OnDialogCloseListener l) {
    TimePickerFragment fragment = new TimePickerFragment(l);
    Bundle Btemp = new Bundle();
    Btemp.putSerializable(KEY_1, t);
    Btemp.putSerializable(KEY_2, timeSelector);
    fragment.setArguments(Btemp);
    return fragment;
  }
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//    Bundle args = savedInstanceState != null ? savedInstanceState : getArguments();
    Bundle bd;
    if (savedInstanceState == null)
    {
      bd = getArguments();
    }
    else
    {
      bd = savedInstanceState;
    }

    mSharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    time = (Date) bd.getSerializable(KEY_1);

    if(time==null)
    {
      time = new Date();
      time.setHours(0);
      time.setMinutes(0);
    }
    return new TimePickerDialog(requireContext(), (tp, Hours, Minutes)->{
      time.setHours(Hours);
      time.setMinutes(Minutes);
      mSharedViewModel.setDate(time);
      if(bd.getSerializable(KEY_2).equals("Start"))
      {
        receiver.onStartTimeDialogClose();
      }
      else if(bd.getSerializable(KEY_2).equals("End"))
      {
        receiver.onEndTimeDialogClose();
      }
      dismiss();
    }, time.getHours(), time.getMinutes(), false);
  }
}
