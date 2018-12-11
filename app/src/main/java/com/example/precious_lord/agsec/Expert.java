package com.example.precious_lord.agsec;

public class Expert {

    private int id;
    private String expert_fullname;
    private String expert_email;
    private String expert_phone;
    private String expert_field;
    private String expert_password;

    public Expert(String expert_fullname, String expert_email, String expert_phone, String expert_field, String expert_password) {
        this.expert_fullname = expert_fullname;
        this.expert_email = expert_email;
        this.expert_phone = expert_phone;
        this.expert_field = expert_field;
        this.expert_password = expert_password;
    }

    public Expert(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpert_fullname() {
        return expert_fullname;
    }

    public void setExpert_fullname(String expert_fullname) {
        this.expert_fullname = expert_fullname;
    }

    public String getExpert_email() {
        return expert_email;
    }

    public void setExpert_email(String expert_email) {
        this.expert_email = expert_email;
    }

    public String getExpert_phone() {
        return expert_phone;
    }

    public void setExpert_phone(String expert_phone) {
        this.expert_phone = expert_phone;
    }

    public String getExpert_field() {
        return expert_field;
    }

    public void setExpert_field(String expert_field) {
        this.expert_field = expert_field;
    }

    public String getExpert_password() {
        return expert_password;
    }

    public void setExpert_password(String expert_password) {
        this.expert_password = expert_password;
    }
}
