package com.mavl.showmethefuckingresults.ObjectExam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExamInDetails {

    @SerializedName("ExamInfo")
    @Expose
    private ExamInfo examInfo;
    @SerializedName("Answers")
    @Expose
    private Answers answers;
    @SerializedName("GekDocument")
    @Expose
    private GekDocument gekDocument;
    @SerializedName("Servers")
    @Expose
    private Servers servers;

    /**
     * No args constructor for use in serialization
     *
     */
    public ExamInDetails() {
    }

    /**
     *
     * @param gekDocument
     * @param examInfo
     * @param servers
     * @param answers
     */
    public ExamInDetails(ExamInfo examInfo, Answers answers, GekDocument gekDocument, Servers servers) {
        super();
        this.examInfo = examInfo;
        this.answers = answers;
        this.gekDocument = gekDocument;
        this.servers = servers;
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public GekDocument getGekDocument() {
        return gekDocument;
    }

    public void setGekDocument(GekDocument gekDocument) {
        this.gekDocument = gekDocument;
    }

    public Servers getServers() {
        return servers;
    }

    public void setServers(Servers servers) {
        this.servers = servers;
    }

}