package com.example.p.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity

public class UserClass {



    @Id
    @GeneratedValue
    private Integer userID;
    private String name;
    private String email;
    private String password;



    public UserClass(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserClass() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return userID;
    }

    public String getPassword() {
        return password;
    }
}
