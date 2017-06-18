package com.mavl.showmethefuckingresults.ObjectExam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartB {

    @SerializedName("LegalSymbols")
    @Expose
    private String legalSymbols;
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
    public PartB() {
    }

    /**
     *
     * @param number
     * @param type
     * @param displayNumber
     * @param maxValue
     * @param legalSymbols
     */
    public PartB(String legalSymbols, String displayNumber, String type, int number, int maxValue) {
        super();
        this.legalSymbols = legalSymbols;
        this.displayNumber = displayNumber;
        this.type = type;
        this.number = number;
        this.maxValue = maxValue;
    }

    public String getLegalSymbols() {
        return legalSymbols;
    }

    public void setLegalSymbols(String legalSymbols) {
        this.legalSymbols = legalSymbols;
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