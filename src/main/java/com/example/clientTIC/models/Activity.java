package com.example.clientTIC.models;

public class Activity {

    private Long id;

    private Long clubId;

    private String nombre;

    private Long precio;

    private int cupos;

    private ActivityCategories activityCategories;

    public Activity(Long id, Long clubId, String nombre, Long precio, int cupos, ActivityCategories activityCategories) {
        this.id = id;
        this.clubId = clubId;
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
    }
    public Activity(Long clubId, String nombre, Long precio, int cupos, ActivityCategories activityCategories) {
        this.clubId = clubId;
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
    }

    public Activity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public ActivityCategories getActivityCategories() {
        return activityCategories;
    }

    public void setActivityCategories(ActivityCategories activityCategories) {
        this.activityCategories = activityCategories;
    }
}
