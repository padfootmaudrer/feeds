package com.example.sushobhithsharma.myapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by sushobhith.sharma on 28/09/17.
 */

public class DataItem implements Serializable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    private String image;
    private String title;
    private String text;
    private String description;
    private String timeStamp;

    public DataItem() {
    }

    public DataItem(String name, String image, String title,
                    String text, String timeStamp, String description) {
        super();
        this.name = name;
        this.image = image;
        this.title = title;
        this.text = text;
        this.timeStamp = timeStamp;
        this.description = description;
    }




}
