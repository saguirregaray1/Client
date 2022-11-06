package com.example.clientTIC.models;

import com.example.clientTIC.AppUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private Long id;

    private Company company;

    private Long cedula;

    private Long saldo;

    private String email;

    private String password;

    private List<Activity> favs;

    private List<CheckIn> access;

    private List<Reservation> reservationsMade;

    private AppUser appUser;

    public Employee(Long id, Company company, Long cedula, Long saldo, String email, String password, List<Activity> favs,AppUser appUser,List<CheckIn> access, List<Reservation> reservationsMade) {
        this.id = id;
        this.company = company;
        this.cedula = cedula;
        this.saldo = saldo;
        this.email = email;
        this.password = password;
        this.favs = favs;
        this.appUser = appUser;
        this.reservationsMade = reservationsMade;
        this.access = access;
    }

    public Employee(Company company, Long cedula, Long saldo, String email, String password, List<Activity> favs, AppUser appUser, List<CheckIn> access, List<Reservation> reservationsMade) {
        this.company = company;
        this.cedula = cedula;
        this.saldo = saldo;
        this.email = email;
        this.password = password;
        this.appUser = appUser;
        this.favs = favs;
        this.access = access;
        this.reservationsMade = reservationsMade;
    }

    public Employee(Company company, Long cedula, Long saldo, String email, String password, List<Activity> favs, AppUser appUser) {
        this.company = company;
        this.cedula = cedula;
        this.saldo = saldo;
        this.email = email;
        this.password = password;
        this.appUser = appUser;
        this.favs = favs;
        this.access = new ArrayList<>();
        this.reservationsMade = new ArrayList<>();
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

    public List<CheckIn> getAccess() {
        return access;
    }

    public void setAccess(List<CheckIn> access) {
        this.access = access;
    }

    public List<Reservation> getReservationsMade() {
        return reservationsMade;
    }

    public void setReservationsMade(List<Reservation> reservationsMade) {
        this.reservationsMade = reservationsMade;
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
