package com.mavl.showmethefuckingresults.ObjectExam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithCriterium {

    @SerializedName("Criteria")
    @Expose
    private Object criteria;
    @SerializedName("DisplayNumber")
    @Expose
    private String displayNumber;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Number")
    @Expose
    private int number;
    @SerializedName("MaxValue")
    @Expose
    private int maxValue;

    /**
     * No args constructor for use in serialization
     *
     */
    public WithCriterium() {
    }

    /**
     *
     * @param criteria
     * @param number
     * @param type
     * @param displayNumber
     * @param maxValue
     */
    public WithCriterium(Object criteria, String displayNumber, String type, int number, int maxValue) {
        super();
        this.criteria = criteria;
        this.displayNumber = displayNumber;
        this.type = type;
        this.number = number;
        this.maxValue = maxValue;
    }

    public Object getCriteria() {
        return criteria;
    }

    public void setCriteria(Object criteria) {
        this.criteria = criteria;
    }

    public String getDisplayNumber() {
        return displayNumber;
    }

    public void setDisplayNumber(String displayNumber) {
        this.displayNumber = displayNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

}