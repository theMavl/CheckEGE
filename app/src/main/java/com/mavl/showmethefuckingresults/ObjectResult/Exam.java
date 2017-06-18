package com.mavl.showmethefuckingresults.ObjectResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exam {

    @SerializedName("ExamId")
    @Expose
    private int examId;
    @SerializedName("OralExamId")
    @Expose
    private Object oralExamId;
    @SerializedName("ExamDate")
    @Expose
    private String examDate;
    @SerializedName("OralExamDate")
    @Expose
    private String oralExamDate;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("OralSubject")
    @Expose
    private Object oralSubject;
    @SerializedName("TestMark")
    @Expose
    private int testMark;
    @SerializedName("Mark5")
    @Expose
    private int mark5;
    @SerializedName("MinMark")
    @Expose
    private int minMark;
    @SerializedName("Status")
    @Expose
    private int status;
    @SerializedName("OralStatus")
    @Expose
    private Object oralStatus;
    @SerializedName("HasAppeal")
    @Expose
    private boolean hasAppeal;
    @SerializedName("IsHidden")
    @Expose
    private boolean isHidden;
    @SerializedName("HasResult")
    @Expose
    private boolean hasResult;
    @SerializedName("HasOralResult")
    @Expose
    private boolean hasOralResult;
    @SerializedName("IsHiddenForRegion")
    @Expose
    private boolean isHiddenForRegion;
    @SerializedName("AppealStatus")
    @Expose
    private Object appealStatus;
    @SerializedName("IsComposition")
    @Expose
    private boolean isComposition;
    @SerializedName("IsBasicMath")
    @Expose
    private boolean isBasicMath;
    @SerializedName("IsForeignLanguage")
    @Expose
    private boolean isForeignLanguage;

    /**
     * No args constructor for use in serialization
     *
     */
    public Exam() {
    }

    /**
     *
     * @param isForeignLanguage
     * @param examDate
     * @param minMark
     * @param appealStatus
     * @param status
     * @param hasOralResult
     * @param subject
     * @param hasResult
     * @param oralExamDate
     * @param isBasicMath
     * @param isHiddenForRegion
     * @param mark5
     * @param isComposition
     * @param isHidden
     * @param oralStatus
     * @param testMark
     * @param oralExamId
     * @param examId
     * @param hasAppeal
     * @param oralSubject
     */
    public Exam(int examId, Object oralExamId, String examDate, String oralExamDate, String subject, Object oralSubject, int testMark, int mark5, int minMark, int status, Object oralStatus, boolean hasAppeal, boolean isHidden, boolean hasResult, boolean hasOralResult, boolean isHiddenForRegion, Object appealStatus, boolean isComposition, boolean isBasicMath, boolean isForeignLanguage) {
        super();
        this.examId = examId;
        this.oralExamId = oralExamId;
        this.examDate = examDate;
        this.oralExamDate = oralExamDate;
        this.subject = subject;
        this.oralSubject = oralSubject;
        this.testMark = testMark;
        this.mark5 = mark5;
        this.minMark = minMark;
        this.status = status;
        this.oralStatus = oralStatus;
        this.hasAppeal = hasAppeal;
        this.isHidden = isHidden;
        this.hasResult = hasResult;
        this.hasOralResult = hasOralResult;
        this.isHiddenForRegion = isHiddenForRegion;
        this.appealStatus = appealStatus;
        this.isComposition = isComposition;
        this.isBasicMath = isBasicMath;
        this.isForeignLanguage = isForeignLanguage;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Object getOralExamId() {
        return oralExamId;
    }

    public void setOralExamId(Object oralExamId) {
        this.oralExamId = oralExamId;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getOralExamDate() {
        return oralExamDate;
    }

    public void setOralExamDate(String oralExamDate) {
        this.oralExamDate = oralExamDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Object getOralSubject() {
        return oralSubject;
    }

    public void setOralSubject(Object oralSubject) {
        this.oralSubject = oralSubject;
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

    public int getMinMark() {
        return minMark;
    }

    public void setMinMark(int minMark) {
        this.minMark = minMark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getOralStatus() {
        return oralStatus;
    }

    public void setOralStatus(Object oralStatus) {
        this.oralStatus = oralStatus;
    }

    public boolean isHasAppeal() {
        return hasAppeal;
    }

    public void setHasAppeal(boolean hasAppeal) {
        this.hasAppeal = hasAppeal;
    }

    public boolean isIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }

    public boolean isHasOralResult() {
        return hasOralResult;
    }

    public void setHasOralResult(boolean hasOralResult) {
        this.hasOralResult = hasOralResult;
    }

    public boolean isIsHiddenForRegion() {
        return isHiddenForRegion;
    }

    public void setIsHiddenForRegion(boolean isHiddenForRegion) {
        this.isHiddenForRegion = isHiddenForRegion;
    }

    public Object getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(Object appealStatus) {
        this.appealStatus = appealStatus;
    }

    public boolean isIsComposition() {
        return isComposition;
    }

    public void setIsComposition(boolean isComposition) {
        this.isComposition = isComposition;
    }

    public boolean isIsBasicMath() {
        return isBasicMath;
    }

    public void setIsBasicMath(boolean isBasicMath) {
        this.isBasicMath = isBasicMath;
    }

    public boolean isIsForeignLanguage() {
        return isForeignLanguage;
    }

    public void setIsForeignLanguage(boolean isForeignLanguage) {
        this.isForeignLanguage = isForeignLanguage;
    }

    public String getStringStatus() {
        if (isHidden)
            return "Результат скрыт";
        if (!hasResult)
            return "Нет результата";
        return "Экзамен обработан";
    }

    public String getStringScore() {
        if (isBasicMath)
            return getMark5()+"";
        if (isComposition)
            return (getTestMark() > 0)? "зачёт":"незачёт";
        if (hasResult)
            return getTestMark()+"";
        if (getTestMark() > 0)
            return getTestMark()+"";
        if ((isHidden) || (isHiddenForRegion) || (!hasResult))
            return "";
        return getTestMark()+"";
    }

}