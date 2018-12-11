package com.example.precious_lord.agsec;

public class Question {

    private int question_id;
    private String user_email;
    private String question_domain;
    private String question_detail;
    private String question_reply;

    public Question(String user_email, String question_domain, String question_detail, String question_reply) {
        this.user_email = user_email;
        this.question_domain = question_domain;
        this.question_detail = question_detail;
        this.question_reply = question_reply;
    }

    public Question(){

    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getQuestion_domain() {
        return question_domain;
    }

    public void setQuestion_domain(String question_domain) {
        this.question_domain = question_domain;
    }

    public String getQuestion_detail() {
        return question_detail;
    }

    public void setQuestion_detail(String question_detail) {
        this.question_detail = question_detail;
    }

    public String getQuestion_reply() {
        return question_reply;
    }

    public void setQuestion_reply(String question_reply) {
        this.question_reply = question_reply;
    }
}
