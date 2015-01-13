package com.aporter.tracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CycleValueDialog extends DialogFragment
{
    private boolean isMetric;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        LayoutInflater i = getActivity().getLayoutInflater();
        final View rootView = i.inflate(R.layout.fragment_cycle_value_dialog,null);

        AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                .setTitle("Enter Information")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {

                                EditText nameText = (EditText)rootView.findViewById(R.id.valueEditText);
                                ((RoutineOpen)getActivity()).addItem(nameText.getText().toString());

                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
        b.setView(rootView);
        return b.create();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }






}