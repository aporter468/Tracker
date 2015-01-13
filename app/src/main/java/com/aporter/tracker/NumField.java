package com.aporter.tracker;

/**
 * Created by Alex on 12/26/14.
 */
import java.io.Serializable;
import java.util.ArrayList;
public class NumField implements Serializable{
    private String name;
    private String unit;
    private String value;
    private ArrayList<String> history;
    public NumField(String name, String unit)
    {
        this.name = name;
        this.unit = unit;
        history = new ArrayList<String>();
    }

    public String getName()
    {
        return name;
    }
    public String getUnit()
    {
        return unit;
    }

    public String getValue()
    {
        return value;
    }
    public void setValue(String valueIn)
    {
        if(value!=null)
            history.add(value);
        value = valueIn;
    }
    public ArrayList<String> getHistory()
    {
        return history;
    }
}

