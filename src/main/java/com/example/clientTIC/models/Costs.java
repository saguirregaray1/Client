package com.example.clientTIC.models;

import java.util.List;

public class Costs {

    Long totalCosts;

    List<CheckIn> checkIns;

    public Costs(Long totalCosts, List<CheckIn> checkIns) {
        this.totalCosts = totalCosts;
        this.checkIns = checkIns;
    }

    public Costs(Long totalCosts) {
        this.totalCosts = totalCosts;
    }
    public Costs(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public Costs(){}

    public Long getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Long totalCosts) {
        this.totalCosts = totalCosts;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }
}

