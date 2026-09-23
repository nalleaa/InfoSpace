package com.example.xipplgb_infospace.model;

public class Student {
    private int absentNumber;
    private String name;

    // Constructor: untuk mengisi data siswa baru
    public Student(int absentNumber, String name) {
        this.absentNumber = absentNumber;
        this.name = name;
    }

    // Getter untuk mengambil nomor absen
    public int getAbsentNumber() {
        return absentNumber;
    }

    // Getter untuk mengambil nama siswa
    public String getName() {
        return name;
    }
}