package com.mavl.showmethefuckingresults.ObjectResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("HotlinePhone")
    @Expose
    private String hotlinePhone;
    @SerializedName("Info")
    @Expose
    private String info;

    /**
     * No args constructor for use in serialization
     *
     */
    public Info() {
    }

    /**
     *
     * @param hotlinePhone
     * @param info
     */
    public Info(String hotlinePhone, String info) {
        super();
        this.hotlinePhone = hotlinePhone;
        this.info = info;
    }

    public String getHotlinePhone() {
        return hotlinePhone;
    }

    public void setHotlinePhone(String hotlinePhone) {
        this.hotlinePhone = hotlinePhone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}