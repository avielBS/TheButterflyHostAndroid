package com.example.mylibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ReporterDialog extends AppCompatDialogFragment {

    private EditText reporterDialogName;
    private EditText reporterDialogWayToContact;
    private EditText reporterDialogDate;

    private ReporterDialogData listener;


    public ReporterDialog(ReporterDialogData listener ){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.report_dialog,null);
        reporterDialogName = view.findViewById(R.id.report_dialog_name);
        reporterDialogWayToContact = view.findViewById(R.id.report_dialog_way_to_contact);
        reporterDialogDate = view.findViewById(R.id.report_dialog_date);

        builder.setView(view).
                setTitle("Contact").
                setIcon(R.drawable.ic_action_name).
                setPositiveButton("send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = reporterDialogName.getText().toString();
                String wayTC = reporterDialogWayToContact.getText().toString();
                String date = reporterDialogDate.getText().toString();
                listener.WhenGetDialogReporterData(name,wayTC,date);
            }
        });
        return builder.create();
    }

}
