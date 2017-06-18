package com.mavl.showmethefuckingresults.ObjectExam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servers {

    @SerializedName("Common")
    @Expose
    private String common;
    @SerializedName("Composition")
    @Expose
    private String composition;

    /**
     * No args constructor for use in serialization
     *
     */
    public Servers() {
    }

    /**
     *
     * @param common
     * @param composition
     */
    public Servers(String common, String composition) {
        super();
        this.common = common;
        this.composition = composition;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

}