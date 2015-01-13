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
public class RoutineList extends Activity implements OnClickListener {
    TableLayout routineListContainer;
    ArrayList<Routine> routineList;
    public static String FILENAME = "routineslistfile.dat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_list);
        routineListContainer = (TableLayout)(findViewById(R.id.routineTableContainer));
        routineListContainer.setOnClickListener(this);
        routineList = new ArrayList<Routine>();
       readRoutinesFromFile();
        populateTable();
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

    @Override
    public void onClick(View view)
    {
        if(view instanceof RoutineRow)
        {
            writeRoutinesToFile();
            Intent intent = new Intent(this, RoutineOpen.class);
            Log.e("mylog","put name: "+((RoutineRow)view).getRoutine().getName());
            intent.putExtra("tracker.openedroutine",((RoutineRow)view).getRoutine().getName());
            startActivity(intent);

        }
    }
    public void addRoutineClicked(View v)
    {
        DialogFragment newFragment = new NewRoutineDialog();
        newFragment.show(getFragmentManager(), "newroutine");



    }
    public void addRoutine(String name)
    {
        Routine newRoutine = new Routine(name);
        routineList.add(newRoutine);
        RoutineRow newRow = new RoutineRow(this,newRoutine);
        newRow.setOnClickListener(this);
        routineListContainer.addView(newRow);
    }
    private void populateTable()
    {
        for (Routine r: routineList)
        {
            RoutineRow newRow = new RoutineRow(this,r );
            newRow.setOnClickListener(this);
            routineListContainer.addView(newRow);
        }
    }
    public void writeRoutinesToFile() {
        FileOutputStream outputStream;
        ObjectOutputStream objOutStream;
        try {
            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            objOutStream = new ObjectOutputStream(outputStream);
            for (int i = 0; i < routineList.size(); i++) {
                objOutStream.writeObject(routineList.get(i));
            }
            objOutStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readRoutinesFromFile()
    {
        FileInputStream in;
        ArrayList<Routine> routinesIn = new ArrayList<Routine>();
        try {
            in = openFileInput(FILENAME);

            ObjectInputStream objInputStream = new ObjectInputStream(in);
            Routine routineIn = (Routine) objInputStream.readObject();
            if (routineIn!=null)// actual data, not just

            {// name


                routinesIn.add(routineIn);
            }
            while (routineIn != null) {
                routineIn = (Routine) objInputStream.readObject();

                    routinesIn.add(routineIn);

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
        if(routinesIn.size()>0) {
            routineList = routinesIn;

        }
    }

}
