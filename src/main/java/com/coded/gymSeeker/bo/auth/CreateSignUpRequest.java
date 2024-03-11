package com.coded.gymSeeker.bo.auth;


import com.coded.gymSeeker.util.enums.Gender;

public class CreateSignUpRequest {
    private String username;
    private String password;
    private String email;
    private Gender gender;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phomeNumber) {
        this.phoneNumber = phomeNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }
}
