package com.app.or.DTO;

import android.graphics.Bitmap;

public class Review_preview {
    long id;
    String Simple_title;
    String Simple_main;
    Bitmap Simple_image;

    public Review_preview(long id, String simple_title, String simple_main, Bitmap simple_image) {
        this.id = id;
        Simple_title = simple_title;
        Simple_main = simple_main;
        Simple_image = simple_image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSimple_title() {
        return Simple_title;
    }

    public void setSimple_title(String simple_title) {
        Simple_title = simple_title;
    }

    public String getSimple_main() {
        return Simple_main;
    }

    public void setSimple_main(String simple_main) {
        Simple_main = simple_main;
    }

    public Bitmap getSimple_image() {
        return Simple_image;
    }

    public void setSimple_image(Bitmap simple_image) {
        Simple_image = simple_image;
    }
}
