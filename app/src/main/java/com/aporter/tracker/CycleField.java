package com.aporter.tracker;

/**
 * Created by Alex on 12/26/14.
 */
import java.io.Serializable;
import java.util.ArrayList;
public class CycleField implements Serializable{
    private String name;
    private ArrayList<String> values;
    private ArrayList<Integer> useCounts;
    //TODO: useCounts
    private int currentValue;
    public CycleField(String name)
    {
        this.name = name;
        values = new ArrayList<String>();
        values.add("+");
        useCounts = new ArrayList<Integer>();
    }
    public void setValues(ArrayList<String> values)
    {
        this.values = values;
    }
    public void addValue(String val)
    {
        values.add(val);
    }
    public int getCurrent()
    {
          return currentValue;
    }
    public ArrayList<String> getValues()
    {
        return values;
    }
    public void cycleNext()
    {
        currentValue++;
        if(currentValue==values.size())
            currentValue = 0;
    }
    public void setSelection(int i)
    {
        if(i <values.size())
        currentValue = i;
        else
            currentValue = values.size()-1;
    }
    public String getName()
    {
        return name;
    }
}
