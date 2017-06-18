package com.mavl.showmethefuckingresults.ObjectExam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GekDocument {

    @SerializedName("GekNumber")
    @Expose
    private String gekNumber;
    @SerializedName("GekDate")
    @Expose
    private String gekDate;
    @SerializedName("Url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     *
     */
    public GekDocument() {
    }

    /**
     *
     * @param gekDate
     * @param gekNumber
     * @param url
     */
    public GekDocument(String gekNumber, String gekDate, String url) {
        super();
        this.gekNumber = gekNumber;
        this.gekDate = gekDate;
        this.url = url;
    }

    public String getGekNumber() {
        return gekNumber;
    }

    public void setGekNumber(String gekNumber) {
        this.gekNumber = gekNumber;
    }

    public String getGekDate() {
        return gekDate;
    }

    public void setGekDate(String gekDate) {
        this.gekDate = gekDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
