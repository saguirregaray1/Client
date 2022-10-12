package com.example.clientTIC.models;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private Long id;
    private String nombre;
    private Long nroCuenta;

    private List<Employee> companyEmployees;

    public Company(Long id, String nombre,Long nroCuenta, List<Employee> companyEmployees) {
        this.id = id;
        this.nombre = nombre;
        this.nroCuenta = nroCuenta;
        this.companyEmployees = companyEmployees;
    }
    public Company(String nombre,Long nroCuenta, List<Employee> companyEmployees) {
        this.nombre = nombre;
        this.nroCuenta = nroCuenta;
        this.companyEmployees = companyEmployees;
    }

    public Company(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(Long nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public List<Employee> getCompanyEmployees() {
        return companyEmployees;
    }

    public void setCompanyEmployees(List<Employee> companyEmployees) {
        this.companyEmployees = companyEmployees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nroCuenta=" + nroCuenta +
                ", companyEmployees=" + companyEmployees +
                '}';
    }
}