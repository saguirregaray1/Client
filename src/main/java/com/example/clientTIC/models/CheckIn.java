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


    public CheckIn(Long checkInId,String fecha) {
        this.checkInId = checkInId;
        this.fecha = fecha;
    }


    public CheckIn(){}

    public Long getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(Long checkInId) {
        this.checkInId = checkInId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}


