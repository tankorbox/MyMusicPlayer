package com.example.tankorbox.myplayer.Data;

/**
 * Created by tankorbox on 8/14/2016.
 */
public class OfflineSong {
    String name;
    String path;

    public OfflineSong(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
