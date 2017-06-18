package com.mavl.showmethefuckingresults.ObjectExam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Blank {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     *
     */
    public Blank() {
    }

    /**
     *
     * @param title
     * @param url
     */
    public Blank(String title, String url) {
        super();
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}