package com.aporter.tracker;

/**
 * Created by Alex on 12/26/14.
 */
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
public class Routine implements Serializable {

    private String name;
    private ArrayList<Item> items;
    public Routine(String name)
    {
        this.name = name;
        items = new ArrayList<Item>();
    }
    public String getName()
    {
        return name;
    }
    public void addItem(Item newItem)
    {
        items.add(newItem);

    }
    public int getNumberItems()
    {
        return items.size();
    }
    public Item getItem(int i)
    {
        return items.get(i);
    }
}
