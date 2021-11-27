package com.example.cuoiia.model;

import android.graphics.Bitmap;

public class Topic {
    private String name;
    private Bitmap bitmap;

    public Topic(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
