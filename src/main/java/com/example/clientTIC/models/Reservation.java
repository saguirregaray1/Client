package com.example.clientTIC.models;

public class Reservation {

    private Long id;

    private Employee employee;

    private Quota quota;

    private ReservationStatus reservationStatus;

    private String fecha;

    public Reservation(Long id, Employee employee, Quota quota, ReservationStatus reservationStatus, String fecha) {
        this.id = id;
        this.employee = employee;
        this.quota = quota;
        this.reservationStatus = reservationStatus;
        this.fecha = fecha;
    }

    public Reservation() { }

    public Reservation(Employee employee, Quota quota,ReservationStatus reservationStatus,String fecha) {
        this.employee = employee;
        this.quota = quota;
        this.reservationStatus = reservationStatus;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
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

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
