package com.aporter.tracker;

import android.app.DialogFragment;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TableRow;

import java.io.Serializable;
import android.content.Context;
import android.widget.TextView;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.util.Log;
/**
 * Created by Alex on 12/26/14.
 */
public class ItemRow extends TableRow implements Serializable
{
    ImageButton plusButton;
    ImageButton saveButton;
    Item item;
    Context context;
    public ItemRow(Context context, Item item, RoutineOpen parentIn)
    {
        super(context);
        this.context = context;
        this.item = item;
        setBackgroundColor(Color.RED);
        TextView textView = new TextView(context);
        textView.setText(item.getName());
        textView.setTextSize(22);
        addView(textView);
        final RoutineOpen parentActivity = parentIn;
        final ItemRow thisRow = this;
        if(item.getFieldCount()<3)
        {
            plusButton = new ImageButton(context);
            plusButton.setImageResource(R.drawable.ic_plus);
            plusButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentActivity.newFieldDialog(thisRow);
                }
            });
            addView(plusButton);
        }
        saveButton = new ImageButton(context);
        saveButton.setImageResource(R.drawable.ic_check);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItemValues();
            }
        });
        addView(saveButton);

    }
    private void saveItemValues()
    {
        ArrayList<NumField> numFields = item.getNumFields();
        for(int i =0; i<numFields.size(); i++)
        {
            EditText etext = (EditText)findViewWithTag("NUM"+i);
            if(etext != null) {
                numFields.get(i).setValue(etext.getText().toString());
            }
        }

        ArrayList<CycleField> cycleFields = item.getCycleFields();
        for(int i =0; i<cycleFields.size(); i++)
        {
            TextView tview = (TextView)findViewWithTag("CYCLE"+i);

        }
    }
    public void addExistingFields()
    {
        ArrayList<NumField> numFields = item.getNumFields();
        for(int i =0; i<numFields.size(); i++)
        {
            buildNumField(numFields.get(i).getName(),numFields.get(i).getUnit(),numFields.get(i).getValue(),i);
        }

        ArrayList<CycleField> cycleFields = item.getCycleFields();
        for(int i =0; i<cycleFields.size(); i++)
        {
            buildCycleField(cycleFields.get(i).getName(),cycleFields.get(i),i);
        }



    }
    public void addField(String name,boolean numField, String unit)
    {
        if(numField)
        {
             buildNumField(name, unit, "",item.getNumFields().size());
            item.addNumField(name,unit);
        }
        else
        {
            CycleField cf = item.addCycleField(name);
            buildCycleField(name, cf, item.getCycleFields().size() - 1);
        }
        if(item.getFieldCount()>2)
        {
            removeView(plusButton);
        }
    }
    private void buildCycleField(String name,CycleField cf, int i)
    {
        TextView fieldTView = new TextView(context);
        fieldTView.setText(name);
        fieldTView.setTag("CYCLE"+i);
        addView(fieldTView);

       final ArrayList<String> spinnerArray = cf.getValues();
        final Spinner spinner = new Spinner(context);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Log.e("mylog", "add item to field");
                    spinnerArray.add("i");
                    spinner.setSelection(1);
                } else {
                    Log.e("mylog", "item at index " + position + " selected");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        addView(spinner);

    }
    private void buildNumField(String name, String unit,String value, int i)
    {
        TextView fieldTView = new TextView(context);
        fieldTView.setText(name);
        addView(fieldTView);
        EditText fieldEText = new EditText(context);
        fieldEText.setHint(unit);
        fieldEText.setText(value);
        fieldEText.setTag("NUM" + i);
        addView(fieldEText);
    }


}
