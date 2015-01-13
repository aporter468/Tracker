package com.aporter.tracker;

import android.graphics.Color;
import android.widget.TableRow;

import java.io.Serializable;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Alex on 12/26/14.
 */
public class RoutineRow extends TableRow implements Serializable
{
    private Routine routine;
    public RoutineRow(Context context, Routine routine)
    {
        super(context);
        this.routine = routine;
        setBackgroundColor(Color.BLUE);
       TextView textView = new TextView(context);
        textView.setText(routine.getName());
        textView.setTextSize(22);
        addView(textView);
    }
    public Routine getRoutine(){return routine;}
}
