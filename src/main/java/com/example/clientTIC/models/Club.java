package com.example.clientTIC.models;

import java.util.List;

public class Club {

    private Long id;

    private String nombre;

    private String dir;

    private List<Activity> clubActivities;

    public Club(Long id,String nombre, String dir, List<Activity> clubActivities) {
        this.id = id;
        this.nombre = nombre;
        this.dir = dir;
        this.clubActivities = clubActivities;
    }

    public Club(String nombre, String dir, List<Activity> clubActivities) {
        this.nombre = nombre;
        this.dir = dir;
        this.clubActivities = clubActivities;
    }

    public Club(){}

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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public List<Activity> getClubActivities() {
        return clubActivities;
    }

    public void setClubActivities(List<Activity> clubActivities) {
        this.clubActivities = clubActivities;
    }
}
