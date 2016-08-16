package com.example.tankorbox.myplayer.Data;

/**
 * Created by tankorbox on 8/13/2016.
 */
public class MyNavi {
    int imageView;
    String name;

    public MyNavi(int imageView, String name) {
        this.imageView = imageView;
        this.name = name;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
