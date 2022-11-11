package com.example.clientTIC.models;

public class Reservation {

    private Long reservationId;

    private Employee employee;

    private Quota quota;

    private ReservationStatus reservationStatus;

    private String fecha;

    public Reservation(Long reservationId, Employee employee, Quota quota, ReservationStatus reservationStatus, String fecha) {
        this.reservationId = reservationId;
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

    public Long getReservationId() {
        return reservationId;
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

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
