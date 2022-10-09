package com.example.clientTIC;

public class Company {

    private Long id;
    private String nombre;
    private String email;

    private Long nroCuenta;
    private String password;

    public Company(Long id, String nombre, String email, Long nroCuenta, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.nroCuenta = nroCuenta;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public Long getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(Long nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}