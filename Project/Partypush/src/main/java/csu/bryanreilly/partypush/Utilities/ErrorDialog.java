package csu.bryanreilly.partypush.Utilities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import csu.bryanreilly.partypush.R;

public class ErrorDialog extends DialogFragment {
    public static String ERROR_MESSAGE_KEY = "ErrorMessage";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String errorMessage;
        try {
            errorMessage = getArguments().getString(ERROR_MESSAGE_KEY);
        }
        catch(NullPointerException e){
            errorMessage = "DEV ERROR! Set Error Message Using setArguments()!";
        }
        StringResourceGetter res = new StringResourceGetter(getActivity());
        builder.setMessage(errorMessage)
                .setPositiveButton(res.get(R.string.default_error_message_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do nothing, clicking the button will dismiss the dialog
                    }
                });

        return builder.create();
    }
}
