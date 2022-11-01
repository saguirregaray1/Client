package com.example.clientTIC.models;

public class Quota {

    private Long quotaId;

    private String day;

    private String startTime;

    private String finishTime;

    private Integer cupos;

    private Activity activity;

    public Quota(Long quotaId, String day, String startTime, String finishTime, Integer cupos, Activity activity) {
        this.quotaId = quotaId;
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cupos = cupos;
        this.activity = activity;
    }

    public Quota(String day, String startTime, String finishTime, Integer cupos, Activity activity) {
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cupos = cupos;
        this.activity = activity;
    }
    public Quota(String day, String startTime, String finishTime, Integer cupos) {
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cupos = cupos;
    }

    public Quota(){}

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

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
