package com.example.clientTIC.models;

import com.example.clientTIC.AppUser;

import java.util.List;

public class Employee {

    private Long id;

    private Company company;

    private Long cedula;

    private Long saldo;

    private String email;

    private String password;

    private List<Activity> favs;

    private AppUser appUser;

    public Employee(Long id, Company company, Long cedula, Long saldo, String email, String password, List<Activity> favs,AppUser appUser) {
        this.id = id;
        this.company = company;
        this.cedula = cedula;
        this.saldo = saldo;
        this.email = email;
        this.password = password;
        this.favs = favs;
        this.appUser = appUser;
    }

    public Employee(Company company, Long cedula, Long saldo, String email, String password, List<Activity> favs, AppUser appUser) {
        this.company = company;
        this.cedula = cedula;
        this.saldo = saldo;
        this.email = email;
        this.password = password;
        this.appUser = appUser;
        this.favs = favs;
    }

    public Employee(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public List<Activity> getFavs() {
        return favs;
    }

    public void setFavs(List<Activity> favs) {
        this.favs = favs;
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", company=" + company +
                ", cedula=" + cedula +
                ", saldo=" + saldo +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favs=" + favs +
                ", appUser=" + appUser +
                '}';
    }
}
