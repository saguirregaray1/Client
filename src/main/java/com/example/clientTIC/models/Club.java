package com.example.clientTIC.models;

import com.example.clientTIC.AppUser;

import java.util.List;

public class Club {

    private Long id;

    private String nombre;

    private String dir;

    private List<Activity> clubActivities;

    private List<AppUser> clubUsers;

    public Club(Long id,String nombre, String dir, List<Activity> clubActivities, List<AppUser> clubUsers) {
        this.id = id;
        this.nombre = nombre;
        this.dir = dir;
        this.clubActivities = clubActivities;
        this.clubUsers = clubUsers;
    }

    public Club(String nombre, String dir, List<Activity> clubActivities, List<AppUser> clubUsers) {
        this.nombre = nombre;
        this.dir = dir;
        this.clubActivities = clubActivities;
        this.clubUsers = clubUsers;
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

    public List<AppUser> getClubUsers() {
        return clubUsers;
    }

    public void setClubUsers(List<AppUser> clubUsers) {
        this.clubUsers = clubUsers;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dir='" + dir + '\'' +
                ", clubActivities=" + clubActivities +
                ", clubUsers=" + clubUsers +
                '}';
    }
}
