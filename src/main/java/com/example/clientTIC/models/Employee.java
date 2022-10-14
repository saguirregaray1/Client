package com.example.clientTIC.models;

import java.util.List;

public class Employee {

    private Long id;

    private Long companyId;

    private Long cedula;

    private Long saldo;

    private String email;

    private String password;

    private List<Activity> favs;

    public Employee(Long id, Long companyId, Long cedula, Long saldo, String email, String password, List<Activity> favs) {
        this.id = id;
        this.companyId = companyId;
        this.cedula = cedula;
        this.saldo = saldo;
        this.email = email;
        this.password = password;
        this.favs = favs;
    }

    public Employee(Long companyId, Long cedula, Long saldo, String email, String password, List<Activity> favs) {
        this.companyId = companyId;
        this.cedula = cedula;
        this.saldo = saldo;
        this.email = email;
        this.password = password;
        this.favs = favs;
    }

    public Employee(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", cedula=" + cedula +
                ", saldo=" + saldo +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favs=" + favs +
                '}';
    }
}
