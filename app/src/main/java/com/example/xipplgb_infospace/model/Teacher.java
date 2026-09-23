package com.example.xipplgb_infospace.model;

public class Teacher {
    private int officialCode;    // Kode guru resmi (contoh: 137)
    private String name;           // Nama lengkap beserta gelar
    private String blockCode;      // Kode singkatan jadwal KK (contoh: R3)

    // Constructor untuk guru umum (tanpa kode R)
    public Teacher(int officialCode, String name) {
        this.officialCode = officialCode;
        this.name = name;
        this.blockCode = "-";
    }

    // Constructor untuk guru produktif PPLG (dengan kode R)
    public Teacher(int officialCode, String name, String blockCode) {
        this.officialCode = officialCode;
        this.name = name;
        this.blockCode = blockCode;
    }

    public int getOfficialCode() {
        return officialCode;
    }

    public String getName() {
        return name;
    }

    public String getBlockCode() {
        return blockCode;
    }
}