package com.ngoctin.intuitionmobile.models;

import java.io.Serializable;

public class UpdateProfileRequest implements Serializable {
    private int id;
    private String username;
    private String fullname;
    private String phoneNumber;
    private String email;
    private String jwt;

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(int id, String username, String fullname, String phoneNumber, String email, String jwt) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.jwt = jwt;
    }

    public UpdateProfileRequest(String username, String fullname, String phoneNumber, String email, String jwt) {
        this.username = username;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.jwt = jwt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "UpdateProfile{" +
                "ID='" + id + '\'' +
                "username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
