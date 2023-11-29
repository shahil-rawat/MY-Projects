package androidsamples.java.journalapp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
public class DeleteDialogFragment extends DialogFragment {
    private final OnDialogCloseListener receiver;

    public DeleteDialogFragment(OnDialogCloseListener rcv) {
        receiver = rcv;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder bld = new AlertDialog.Builder(requireContext());
        bld.setTitle("Delete")
                .setMessage("Do you wanna delete this entry??")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    dismiss();
                })
                .setNegativeButton("No", (dialogInterface, i) -> {
                    dismiss();
                });
        return bld.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        receiver.onDeleteDialogClose();
    }
}