package com.example.precious_lord.agsec.Database;

public class Users {

     private int id;
     private String fullName;
     private String email;
     private String phone;
     private String password;

     public Users(String fullName, String email, String phone, String password) {
          this.fullName = fullName;
          this.email = email;
          this.phone = phone;
          this.password = password;
     }

     public Users(){

     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
          return fullName;
     }

    public void setFullName(String fullName) {
          this.fullName = fullName;
     }

    public String getEmail() {
          return email;
     }

    public void setEmail(String email) {
          this.email = email;
     }

    public String getPhone() {
          return phone;
     }

    public void setPhone(String phone) {
          this.phone = phone;
     }

    public String getPassword() {
          return password;
     }

    public void setPassword(String password) {
          this.password = password;
     }
}
