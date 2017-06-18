package com.mavl.showmethefuckingresults.ObjectExam;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answers {

    @SerializedName("PrimaryMark")
    @Expose
    private int primaryMark;
    @SerializedName("TestMark")
    @Expose
    private int testMark;
    @SerializedName("Mark5")
    @Expose
    private int mark5;
    @SerializedName("IsHidden")
    @Expose
    private boolean isHidden;
    @SerializedName("Answers")
    @Expose
    private List<Answer> answers = null;
    @SerializedName("CBlanks")
    @Expose
    private Object cBlanks;
    @SerializedName("Blanks")
    @Expose
    private List<Blank> blanks = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Answers() {
    }

    /**
     *
     * @param blanks
     * @param isHidden
     * @param testMark
     * @param answers
     * @param primaryMark
     * @param cBlanks
     * @param mark5
     */
    public Answers(int primaryMark, int testMark, int mark5, boolean isHidden, List<Answer> answers, Object cBlanks, List<Blank> blanks) {
        super();
        this.primaryMark = primaryMark;
        this.testMark = testMark;
        this.mark5 = mark5;
        this.isHidden = isHidden;
        this.answers = answers;
        this.cBlanks = cBlanks;
        this.blanks = blanks;
    }

    public int getPrimaryMark() {
        return primaryMark;
    }

    public void setPrimaryMark(int primaryMark) {
        this.primaryMark = primaryMark;
    }

    public int getTestMark() {
        return testMark;
    }

    public void setTestMark(int testMark) {
        this.testMark = testMark;
    }

    public int getMark5() {
        return mark5;
    }

    public void setMark5(int mark5) {
        this.mark5 = mark5;
    }

    public boolean isIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Object getCBlanks() {
        return cBlanks;
    }

    public void setCBlanks(Object cBlanks) {
        this.cBlanks = cBlanks;
    }

    public List<Blank> getBlanks() {
        return blanks;
    }

    public void setBlanks(List<Blank> blanks) {
        this.blanks = blanks;
    }

}