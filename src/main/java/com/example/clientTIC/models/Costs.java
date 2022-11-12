package com.example.clientTIC.models;

import java.util.List;

public class Costs {

    Long total;

    List<CheckIn> checkIns;

    List<Employee> users;

    public Costs(Long total, List<CheckIn> checkIns, List<Employee> users) {
        this.total = total;
        this.checkIns = checkIns;
        this.users = users;
    }

    public Costs(Long total) {
        this.total = total;
    }
    public Costs(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public Costs(List<CheckIn> checkIns, List<Employee> users) {
        this.checkIns = checkIns;
        this.users = users;
    }
    public Costs(){}


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public List<Employee> getUsers() {
        return users;
    }

    public void setUsers(List<Employee> users) {
        this.users = users;
    }
}

