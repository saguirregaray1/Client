package com.example.clientTIC.models;

import com.example.clientTIC.AppUser;

public class Admin {

    private Long id;

    private String email;

    private String password;

    private AppUser appUser;


    public Admin(Long id, String email, String password, AppUser appUser) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.appUser = appUser;
    }

    public Admin(){}

    public Admin(String email, String password,AppUser appUser) {
        this.email = email;
        this.password = password;
        this.appUser = appUser;
    }
    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
        this.appUser = new AppUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
