package androidsamples.java.journalapp;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.text.SimpleDateFormat;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class EntryListFragment extends Fragment {
  private EntryListViewModel mEntryListViewModel;
  private View view;
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.menu_info) {
      Navigation.findNavController(view).navigate(EntryListFragmentDirections.infoFragmentAction());
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu,
                                  @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_entry_list, menu);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    mEntryListViewModel = new ViewModelProvider(this).get(EntryListViewModel.class);
  }
  @NonNull
  public static EntryListFragment newInstance() {
    EntryListFragment fm = new EntryListFragment();
    Bundle args = new Bundle();
    fm.setArguments(args);
    return fm;
  }
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_entry_list, container, false);
    view.findViewById(R.id.btn_add_entry).setOnClickListener(this::addNewEntry);
    RecyclerView eRecords = view.findViewById(R.id.recyclerView);
    eRecords.setLayoutManager(new LinearLayoutManager(getActivity()));
    JournalEntryListAdapter adapter = new JournalEntryListAdapter(getActivity());
    eRecords.setAdapter(adapter);
    mEntryListViewModel.getAllEntries().observe(requireActivity(), adapter::setEntries);

    return view;
  }

  private void addNewEntry(View view) {
    EntryListFragmentDirections.AddEntryAction action = EntryListFragmentDirections.addEntryAction();
    Navigation.findNavController(view).navigate(action);
  }

  public class JournalEntryListAdapter extends RecyclerView.Adapter<JournalEntryListAdapter.EntryViewHolder> {
    private List<JournalEntry> mEntries;
    private final LayoutInflater mInflater;

    public JournalEntryListAdapter(Context context){
      mInflater=LayoutInflater.from(context);
    }

    @NotNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NotNull ViewGroup parent,
                                              int viewType) {
      View itemView = mInflater.inflate(R.layout.fragment_entry,
              parent,
              false);
      return new EntryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull EntryViewHolder holder, int position){
      if(mEntries!=null)
      {
        JournalEntry present = mEntries.get(position);

        Date d = present.getDate();
        SimpleDateFormat dF = new SimpleDateFormat("E, MMM dd, yyyy");
        String Date = dF.format(d);
        holder.mTxtDate.setText(Date);

        SimpleDateFormat tF = new SimpleDateFormat("HH:mm");

        Date sTime = present.getStartTime();
        String StartTime = tF.format(sTime);
        holder.mTxtStartTime.setText(StartTime);

        Date eTime = present.getEndTime();
        String EndTime = tF.format(eTime);
        holder.mTxtEndTime.setText(EndTime);

        holder.mTxtTitle.setText(present.getTitle());

        holder.mEntry.setOnClickListener((View view)->{
          EntryListFragmentDirections.AddEntryAction action = EntryListFragmentDirections.addEntryAction();
          action.setEntryId(present.getUid());
          Navigation.findNavController(view).navigate(action);
        });
      }
    }
    class EntryViewHolder extends RecyclerView.ViewHolder {
      private final TextView mTxtTitle, mTxtDate, mTxtStartTime, mTxtEndTime;
      private final LinearLayout mEntry;

      public EntryViewHolder(@NotNull View itemView){
        super(itemView);
        mTxtStartTime = itemView.findViewById(R.id.txt_item_start_time);
        mTxtEndTime = itemView.findViewById(R.id.txt_item_end_time);
        mTxtDate = itemView.findViewById(R.id.txt_item_date);
        mTxtTitle = itemView.findViewById(R.id.txt_item_title);
        mEntry = itemView.findViewById(R.id.fragment_entry);
      }
    }
    @Override
    public int getItemCount()
    {
      if(mEntries == null)
        return 0;
      else
      {
        int si = mEntries.size();
        return si;
      }
    }
    public void setEntries(List<JournalEntry> entries)
    {
      mEntries = entries;
      notifyDataSetChanged();
    }
  }
}