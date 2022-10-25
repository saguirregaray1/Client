package com.example.clientTIC.models;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private Long id;

    private Club club;

    private String nombre;

    private Long precio;

    private int cupos;

    private ActivityCategories activityCategories;

    private List<Imagen> pictures;


    public Activity(Long id, Club club, String nombre, Long precio, int cupos, ActivityCategories activityCategories, List<Imagen> pictures) {
        this.id = id;
        this.club = club;
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
        this.pictures = pictures;
    }

    public Activity(Club club, String nombre, Long precio, int cupos, ActivityCategories activityCategories, List<Imagen> pictures) {
        this.club = club;
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
        this.pictures = pictures;
    }

    public Activity(Club club, String nombre, Long precio, int cupos, ActivityCategories activityCategories) {
        this.club = club;
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
        this.pictures = new ArrayList<>();
    }

    public Activity(String nombre, Long precio, int cupos, ActivityCategories activityCategories) {
        this.nombre = nombre;
        this.precio = precio;
        this.cupos = cupos;
        this.activityCategories = activityCategories;
    }

    public Activity() {
    }

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

    public List<Imagen> getPictures() {
        return pictures;
    }

    public void setPictures(List<Imagen> pictures) {
        this.pictures = pictures;
    }
}
