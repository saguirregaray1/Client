package com.example.clientTIC;

import com.example.clientTIC.models.Admin;
import com.example.clientTIC.models.Club;
import com.example.clientTIC.models.Company;
import com.example.clientTIC.models.Employee;

public class AppUser {

    private Long id;

    private String email;

    private String password;

    private AppUserRole appUserRole;

    private Employee employee;

    private Club club;

    private Company company;

    private Admin admin;

    public AppUser(Long id, String email, String password, AppUserRole appUserRole, Employee employee) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.employee = employee;
    }

    public AppUser(Long id, String email, String password, AppUserRole appUserRole, Club club) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.club = club;
    }

    public AppUser(Long id, String email, String password, AppUserRole appUserRole, Company company) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.company = company;
    }

    public AppUser(Long id, String email, String password, AppUserRole appUserRole, Admin admin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.admin = admin;
    }


    public AppUser(){}

    public AppUser(String email, String password, AppUserRole appUserRole, Employee employee) {
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.employee = employee;
    }

    public AppUser(String email, String password, AppUserRole appUserRole, Club club) {
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.club = club;
    }

    public AppUser(String email, String password, AppUserRole appUserRole, Company company) {
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.company = company;
    }

    public AppUser(String email, String password, AppUserRole appUserRole, Admin admin) {
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.admin = admin;
    }

    public AppUser(String email, String password, AppUserRole appUserRole) {
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
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

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", appUserRole=" + appUserRole +
                ", employee=" + employee +
                ", club=" + club +
                ", company=" + company +
                ", admin=" + admin +
                '}';
    }
}
