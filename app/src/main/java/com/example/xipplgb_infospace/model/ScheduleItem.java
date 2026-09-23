package com.example.xipplgb_infospace.model;

public class ScheduleItem {
    private String subjectName;    // Nama mapel (singkatan / lengkap)
    private String periodRange;    // Contoh: "Jam 1–3"
    private String timeRange;      // Contoh: "07.00 - 09.15"
    private String teacherName;    // Nama guru
    private String teacherCode;    // Kode guru (misal: "137" atau "R3")

    public ScheduleItem(String subjectName, String periodRange, String timeRange, String teacherName, String teacherCode) {
        this.subjectName = subjectName;
        this.periodRange = periodRange;
        this.timeRange = timeRange;
        this.teacherName = teacherName;
        this.teacherCode = teacherCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getPeriodRange() {
        return periodRange;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherCode() {
        return teacherCode;
    }
}