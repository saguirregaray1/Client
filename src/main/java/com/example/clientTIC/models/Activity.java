package com.example.clientTIC.models;

public class Activity {

    private Long id;

    private Club club;

    private String nombre;

    private Long precio;

    private int cupos;

    private ActivityCategories activityCategories;

    public Activity(Long id, Club club, String nombre, Long precio, int cupos, ActivityCategories activityCategories) {
        this.id = id;
        this.club = club;
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
    }
    public Activity(Club club, String nombre, Long precio, int cupos, ActivityCategories activityCategories) {
        this.club = club;
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
    }

    public Activity(String nombre, Long precio, int cupos, ActivityCategories activityCategories) {
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

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
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
