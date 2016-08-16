package com.example.tankorbox.myplayer.Data;

import android.widget.Filter;
import android.widget.Filterable;

/**
 * Created by tankorbox on 8/13/2016.
 */
public class SongData implements Filterable{
    String name;
    String time;
    int id;

    public SongData(String name, String time, int id) {
        this.name = name;
        this.time = time;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
