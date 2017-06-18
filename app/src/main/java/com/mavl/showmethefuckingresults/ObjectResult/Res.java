package com.mavl.showmethefuckingresults.ObjectResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res {

    @SerializedName("Info")
    @Expose
    private Info info;
    @SerializedName("Result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     *
     */
    public Res() {
    }

    /**
     *
     * @param result
     * @param info
     */
    public Res(Info info, Result result) {
        super();
        this.info = info;
        this.result = result;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}