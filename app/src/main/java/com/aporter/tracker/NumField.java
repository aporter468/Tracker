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
    public NumField(String name, String unit)
    {
        this.name = name;
        this.unit = unit;
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
    public void setValue(String value)
    {
        this.value = value;
    }
}

