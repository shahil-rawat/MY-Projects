package androidsamples.java.journalapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryDetailsFragment # newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntryDetailsFragment extends Fragment implements OnDialogCloseListener{
  private SharedViewModel mSharedViewModel;
  private static final String TAG = "EntryDetailsFragment";
  private JournalEntry mEntry;
  EntryDetailsViewModel mEntryDetailsViewModel;
  private UUID presentEntryId;
  private static final int REQUEST_CODE = 11;
  EditText mTitle;
  Button mDate, mStart, mEnd, mSave;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);
    mSharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
    presentEntryId = (UUID) EntryDetailsFragmentArgs.fromBundle(getArguments()).getEntryId();
    if (presentEntryId!=null){
      mEntryDetailsViewModel.loadEntry(presentEntryId);
    }
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_entry_details, container, false);
  }
  private Date StringToDate(String s) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("E, MMM dd, yyyy");
    return format.parse(s);
  }

  private Date StringToTime(String s) throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    return format.parse(s);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mTitle= view.findViewById(R.id.edit_title);
    mDate= view.findViewById(R.id.btn_entry_date);
    mStart=view.findViewById(R.id.btn_start_time);
    mEnd=view.findViewById(R.id.btn_end_time);
    mSave=view.findViewById(R.id.btn_save);
    mEntryDetailsViewModel.getEntryLiveData().observe(getActivity(),
            entry -> {
              this.mEntry = entry;
              updateUI();
            });



    mStart.setOnClickListener(v -> {
      try {
        TimePickerFragment mStpf;
        if(mStart.getText().toString().equals("Start Time")) {
          mStpf= TimePickerFragment.newInstance(null, "Start", this);
        }
        else {
          Date tempStart = new Date();
          tempStart = StringToTime(mStart.getText().toString());
          mStpf = TimePickerFragment.newInstance(tempStart, "Start", this);
        }
        mStpf.show(getFragmentManager()!=null?getFragmentManager():null, "START_TIME_PICK");
      } catch (Exception e){
        e.printStackTrace();
      }
    });

    mDate.setOnClickListener(v -> {
      try {
        DatePickerFragment mDPF;
        if(mDate.getText().toString().equals("Date")) {
          mDPF = DatePickerFragment.newInstance(null, this);
        }
        else {
          Date tDate=new Date();
          tDate = StringToDate(mDate.getText().toString());
          mDPF = DatePickerFragment.newInstance(tDate, this);

        }
        mDPF.show(getFragmentManager()!=null?getFragmentManager():null, "DATE_PICK");
      } catch (Exception e) {
        e.printStackTrace();
      }

    });

    mSave.setOnClickListener(t -> {
      try {
        onClick(t);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    });

    mEnd.setOnClickListener(v->{
      try {
        TimePickerFragment mEtpf;
        if(mEnd.getText().toString().equals("End Time")) {
          mEtpf = TimePickerFragment.newInstance(null, "End", this);
        }
        else {
          Date tempEnd = new Date();
          tempEnd = StringToTime(mEnd.getText().toString());
          mEtpf = TimePickerFragment.newInstance(tempEnd, "End", this);
        }
        mEtpf.show(getChildFragmentManager()!=null?getFragmentManager():null, "END_TIME_PICK");
      }
      catch (Exception e){
        e.printStackTrace();
      }
    });


  }

  private void updateUI() {
    if(mEntry!=null){
      String STime = TimeToString(mEntry.getStartTime());
      String ETime = TimeToString(mEntry.getEndTime());
      String Date = DateToString(mEntry.getDate());
      mStart.setText(STime);
      mEnd.setText(ETime);
      mTitle.setText(mEntry.getTitle());
      mDate.setText(Date);
    }
  }
  private String DateToString(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("E, MMM dd, yyyy");
    return format.format(date);
  }
  private String TimeToString(Date time) {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(time);
  }


  private void onClick(View view) throws ParseException
  {
    if (mTitle.getText().toString().equals(""))
      Toast.makeText(getContext(), "Please enter the Journal Title", Toast.LENGTH_SHORT).show();
    else if (mDate.getText().toString().equals("Date"))
      Toast.makeText(getContext(), "Please enter the date", Toast.LENGTH_SHORT).show();
    else if (mStart.getText().toString().equals("Start Time"))
      Toast.makeText(getContext(), "Please enter the start time", Toast.LENGTH_SHORT).show();
    else if (mEnd.getText().toString().equals("End Time"))
      Toast.makeText(getContext(), "Please enter the end Time", Toast.LENGTH_SHORT).show();
    else if (StringToTime(mEnd.getText().toString()).compareTo(StringToTime(mStart.getText().toString()))<=0){
      Toast.makeText(getContext(), "End time not greater than start time", Toast.LENGTH_SHORT).show();
    }
    else {
      saveEntry(view);
    }
  }
  private void saveEntry(View v) throws ParseException {
    Date sTime = StringToTime(mStart.getText().toString());
    Date eTime = StringToTime(mEnd.getText().toString());
    Date D = StringToDate(mDate.getText().toString());

    if(presentEntryId!=null)
    {
      mEntry.setStartTime(sTime);
      mEntry.setEndTime(eTime);
      mEntry.setDate(D);
      mEntry.setTitle(mTitle.getText().toString());
      mEntryDetailsViewModel.saveExistingEntry(mEntry);
    }
    else
    {
      mEntry = new JournalEntry(mTitle.getText().toString(), D, sTime, eTime);
      mEntryDetailsViewModel.addNewEntry(mEntry);
    }
    getActivity().onBackPressed();
  }


  @Override
  public void onStartTimeDialogClose() {
    mStart.setText(TimeToString(mSharedViewModel.getDate()));
  }

  @Override
  public void onEndTimeDialogClose() {
    mEnd.setText(TimeToString(mSharedViewModel.getDate()));
  }
  @Override
  public void onDateDialogClose() {
    mDate.setText(DateToString(mSharedViewModel.getDate()));
  }

  @Override
  public void onDeleteDialogClose() {
    mEntryDetailsViewModel.deleteEntry(mEntry);
    @NonNull NavDirections actionToReturn = EntryDetailsFragmentDirections.actionEntryDetailsFragmentToEntryListFragment();
    Navigation.findNavController(requireView()).navigate(actionToReturn);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu,
                                  @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_details, menu);
  }
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId()==R.id.menu_share)
    {
      if(mEntry!=null)
      {
        String textToShare = "Look What I have been up to: " + mEntry.getTitle() + " on " + mEntry.getDate() + " , " + mEntry.getStartTime() + " to " + mEntry.getEndTime();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textToShare);
        if(intent.resolveActivity(requireActivity().getPackageManager()) != null)
        {
          startActivity(intent);
        }
      }
      else
      {
        Toast.makeText(getContext(), "There was some error in saving the entry", Toast.LENGTH_SHORT).show();
      }
    }

    if (item.getItemId() == R.id.menu_delete)
    {
      if (presentEntryId!=null)
      {
        DeleteDialogFragment deleteDialogFragment = new DeleteDialogFragment(this);
        deleteDialogFragment.show(getFragmentManager()!=null?getChildFragmentManager():null, "DeleteFragment");
      }
      else
      {
        Toast.makeText(getContext(), "Entry was not saved", Toast.LENGTH_SHORT).show();
      }
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}