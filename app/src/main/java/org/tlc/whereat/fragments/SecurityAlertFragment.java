package org.tlc.whereat.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.tlc.whereat.R;

public class SecurityAlertFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        return new AlertDialog.Builder(getActivity())
            .setIcon(android.R.drawable.stat_notify_error)
            .setTitle(R.string.sec_alert_title)
            .setMessage(R.string.sec_alert_message)
            .setPositiveButton(R.string.sec_alert_yes_btn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                    startActivity(new Intent( Intent.ACTION_VIEW, Uri.parse(getString(R.string.sec_alert_url))));
                }
            })
            .setNegativeButton(R.string.sec_alert_no_btn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            })
            .create();
    }
}