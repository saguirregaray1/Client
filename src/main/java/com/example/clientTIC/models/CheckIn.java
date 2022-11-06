package com.example.clientTIC.models;

public class CheckIn {

    private Long checkInId;

    private Employee employee;

    private Quota quota;

    private String fecha;

    public CheckIn(Long checkInId, Employee employee, Quota quota, String fecha) {
        this.checkInId = checkInId;
        this.employee = employee;
        this.quota = quota;
        this.fecha = fecha;
    }

    public CheckIn(Employee employee, Quota quota, String fecha) {
        this.employee = employee;
        this.quota = quota;
        this.fecha = fecha;
    }

    public CheckIn(){}
}


