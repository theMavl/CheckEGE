package com.mavl.showmethefuckingresults.ObjectResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("Exams")
    @Expose
    private List<Exam> exams = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param exams
     */
    public Result(List<Exam> exams) {
        super();
        this.exams = exams;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

}