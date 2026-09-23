package com.example.xipplgb_infospace.model;

public class TimeSlot {
    private int periodNumber; // Jam ke- (1, 2, 3, dst.)
    private String startTime;    // Contoh: "07.00"
    private String endTime;      // Contoh: "07.45"

    public TimeSlot(int periodNumber, String startTime, String endTime) {
        this.periodNumber = periodNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTimeRange() {
        return startTime + " - " + endTime;
    }
}