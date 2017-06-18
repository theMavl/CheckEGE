package com.mavl.showmethefuckingresults.ObjectExam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Number")
    @Expose
    private int number;
    @SerializedName("Answer")
    @Expose
    private String answer;
    @SerializedName("Mark")
    @Expose
    private int mark;

    /**
     * No args constructor for use in serialization
     *
     */
    public Answer() {
    }

    /**
     *
     * @param answer
     * @param mark
     * @param number
     * @param type
     */
    public Answer(String type, int number, String answer, int mark) {
        super();
        this.type = type;
        this.number = number;
        this.answer = answer;
        this.mark = mark;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}