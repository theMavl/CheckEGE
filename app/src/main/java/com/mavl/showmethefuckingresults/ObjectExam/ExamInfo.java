package com.mavl.showmethefuckingresults.ObjectExam;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExamInfo {

    @SerializedName("Threshold")
    @Expose
    private int threshold;
    @SerializedName("IsComposition")
    @Expose
    private boolean isComposition;
    @SerializedName("IsBasicMath")
    @Expose
    private boolean isBasicMath;
    @SerializedName("IsForeignLanguage")
    @Expose
    private boolean isForeignLanguage;
    @SerializedName("PartB")
    @Expose
    private List<PartB> partB = null;
    @SerializedName("WithCriteria")
    @Expose
    private List<WithCriterium> withCriteria = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ExamInfo() {
    }

    /**
     *
     * @param isComposition
     * @param partB
     * @param isForeignLanguage
     * @param withCriteria
     * @param isBasicMath
     * @param threshold
     */
    public ExamInfo(int threshold, boolean isComposition, boolean isBasicMath, boolean isForeignLanguage, List<PartB> partB, List<WithCriterium> withCriteria) {
        super();
        this.threshold = threshold;
        this.isComposition = isComposition;
        this.isBasicMath = isBasicMath;
        this.isForeignLanguage = isForeignLanguage;
        this.partB = partB;
        this.withCriteria = withCriteria;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
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

    public List<PartB> getPartB() {
        return partB;
    }

    public void setPartB(List<PartB> partB) {
        this.partB = partB;
    }

    public List<WithCriterium> getWithCriteria() {
        return withCriteria;
    }

    public void setWithCriteria(List<WithCriterium> withCriteria) {
        this.withCriteria = withCriteria;
    }

}