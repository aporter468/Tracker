package com.aporter.tracker;

/**
 * Created by Alex on 12/26/14.
 */
//TODO: entry history by date (different than field's history changing when new value)
import java.io.Serializable;
import java.util.ArrayList;
import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;

public class Item implements Serializable
{
    private String name;
    private ArrayList<NumField> numFields;
    private ArrayList<CycleField> cycleFields;

    private int fieldCount;
    public Item(String name)
    {
        this.name = name;
        numFields = new ArrayList<NumField>();
        cycleFields = new ArrayList<CycleField>();

    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void addNumField(String name,String unit )
    {
        NumField newField  = new NumField(name, unit);
        numFields.add(newField);
        fieldCount++;
    }
    public CycleField addCycleField(String name )
    {
        CycleField newField = new CycleField(name);
        cycleFields.add(newField);
        fieldCount++;
        return newField;

    }
    public int getFieldCount(){return fieldCount;}
    public String getName()
    {
        return name;
    }
    public ArrayList<NumField> getNumFields(){return numFields;}
    public ArrayList<CycleField> getCycleFields(){return cycleFields;}
}
