package com.aporter.tracker;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.util.Log;
import android.content.Intent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class RoutineOpen extends Activity implements OnClickListener {
    TableLayout routineOpenContainer;
    private ItemRow fieldAddRow;
    Routine openRoutine;
    String routineName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
         routineName = intent.getStringExtra("tracker.openedroutine");

        setContentView(R.layout.activity_routine_open);
        routineOpenContainer = (TableLayout)(findViewById(R.id.routineOpenContainer));
        routineOpenContainer.setOnClickListener(this);
        readRoutineFromFile();
        if(openRoutine == null)
        {
            openRoutine = new Routine(routineName);
            Log.e("mylog","new routine created");
        }
        else
        {
            populateRows();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_routine_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void addItemClicked(View v)
    {
        DialogFragment newFragment = new NewItemDialog();
        newFragment.show(getFragmentManager(), "newitem");

    }
    public void addItem(String name)
    {
        Item newItem = new Item(name);
        ItemRow newRow = new ItemRow(this, newItem,this);
        newRow.setOnClickListener(this);
        routineOpenContainer.addView(newRow);
        openRoutine.addItem(newItem);

    }
    private void populateRows()
    {
        for (int i =0; i<openRoutine.getNumberItems(); i++) {
            ItemRow newRow = new ItemRow(this, openRoutine.getItem(i), this);
            newRow.addExistingFields();
            newRow.setOnClickListener(this);
            routineOpenContainer.addView(newRow);
        }
    }
    @Override
    public void onClick(View view)
    {
        if(view instanceof RoutineRow)
        {
            Log.e("mylog","rowclick");
            Intent intent = new Intent(this, RoutineOpen.class);
            startActivity(intent);

        }
    }

    public void newFieldDialog(ItemRow fieldAddRow)
    {
        this.fieldAddRow = fieldAddRow;
        DialogFragment newFragment = new NewFieldDialog();
        newFragment.show(getFragmentManager(), "newfield");
    }
    public void addField(String name, Boolean numField, String unit)
    {
        fieldAddRow.addField(name, numField,unit);
    }

    public void readRoutineFromFile()
    {
        FileInputStream in;

        try {
            in = openFileInput(routineName+"routinefile.dat");

            ObjectInputStream objInputStream = new ObjectInputStream(in);
            Routine routineIn = (Routine) objInputStream.readObject();
            if (routineIn!=null)// actual data, not just
            {

                    openRoutine = routineIn;


            }



        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch block
            // /e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            Log.e("mylog", "class not found");

            // e.printStackTrace();
        }

    }
    private void writeRoutineToFile()
    {

        FileOutputStream outputStream;
        ObjectOutputStream objOutStream;
        try {
            outputStream = openFileOutput(routineName+"routinefile.dat", Context.MODE_PRIVATE);
            objOutStream = new ObjectOutputStream(outputStream);
            Log.e("mylog","count:"+openRoutine.getNumberItems());
                objOutStream.writeObject(openRoutine);
            objOutStream.close();
            outputStream.close();
            Log.e("mylog","routine written:"+routineName);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onPause()
    {
        writeRoutineToFile();
        Log.e("mylog","routineopen paused; saved");
        super.onPause();
    }
    @Override
    protected void onStop()
    {
        writeRoutineToFile();
        Log.e("mylog","routineopen stopped; saved");
        super.onStop();
    }
}
