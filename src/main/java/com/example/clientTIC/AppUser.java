package com.example.clientTIC;

public class AppUser {

    private Long id;

    private String email;

    private String password;

    private AppUserRole appUserRole;

    public AppUser(Long id, String email, String password, AppUserRole appUserRole) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public AppUser(){}

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

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }
}
