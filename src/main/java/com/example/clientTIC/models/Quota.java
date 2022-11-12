package com.example.clientTIC.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Quota {

    private Long quotaId;

    private String day;

    private String startTime;

    private String finishTime;

    private Integer maxCupos;

    private List<Reservation> reservationsReceived;

    private List<CheckIn> confirmedUses;
    private Activity activity;

    public Quota(Long quotaId, String day, String startTime, String finishTime, Integer maxCupos, Activity activity,List<Reservation> reservationsReceived, List<CheckIn> confirmedUses) {
        this.quotaId = quotaId;
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.maxCupos = maxCupos;
        this.activity = activity;
        this.reservationsReceived = reservationsReceived;
        this.confirmedUses = confirmedUses;
    }

    public Quota(String day, String startTime, String finishTime, Integer maxCupos, List<Reservation> reservationsReceived, List<CheckIn> confirmedUses, Activity activity) {
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.maxCupos = maxCupos;
        this.reservationsReceived = reservationsReceived;
        this.confirmedUses = confirmedUses;
        this.activity = activity;
    }

    public Quota(String day, String startTime, String finishTime, Integer maxCupos) {
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.maxCupos = maxCupos;
        this.reservationsReceived = new ArrayList<>();
        this.confirmedUses = new ArrayList<>();
    }

    public Quota(String day, String startTime, String finishTime) {
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.maxCupos = -1;
        this.reservationsReceived = new ArrayList<>();
        this.confirmedUses = new ArrayList<>();
    }

    public Quota(){}

    public Integer calculateCupos(String fecha) {
        LocalDate now = LocalDate.now();
        Integer cupos = this.maxCupos;
        for (Reservation reservation : this.reservationsReceived) {
            if (reservation.getReservationStatus().equals(ReservationStatus.PENDIENTE) && reservation.getFecha().equals(fecha)) {
                cupos = cupos - 1;
            }
        }
        for (CheckIn checkIn : this.confirmedUses) {
            if (checkIn.getFecha().equals(fecha)) {
                cupos = cupos - 1;
            }
        }
        return cupos;
    }

    public Integer calculateCuposForToday() {
        LocalDate now = LocalDate.now();
        Integer cupos = this.maxCupos;
        for (Reservation reservation : this.reservationsReceived) {
            if (reservation.getReservationStatus().equals(ReservationStatus.PENDIENTE) && reservation.getFecha().equals(LocalDate.now().toString())) {
                cupos = cupos - 1;
            }
        }
        for (CheckIn checkIn : this.confirmedUses) {
            if (checkIn.getFecha().equals(LocalDate.now().toString())) {
                cupos = cupos - 1;
            }
        }
        return cupos;
    }

    public Long getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getMaxCupos() {
        return maxCupos;
    }

    public void setMaxCupos(Integer maxCupos) {
        this.maxCupos = maxCupos;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<Reservation> getReservationsReceived() {
        return reservationsReceived;
    }

    public void setReservationsReceived(List<Reservation> reservationsReceived) {
        this.reservationsReceived = reservationsReceived;
    }

    public List<CheckIn> getConfirmedUses() {
        return confirmedUses;
    }

    public void setConfirmedUses(List<CheckIn> confirmedUses) {
        this.confirmedUses = confirmedUses;
    }
}
