package com.example.xipplgb_infospace.model;

public class BlockInfo {
    private String blockType;     // "KK" atau "MPU"
    private int weekNumber;       // 1 atau 2
    private String dateRange;      // Contoh: "21–27 September 2026"
    private String dayName;        // "Senin", "Selasa", dst.
    private String formattedDate;  // "23 September 2026"

    public BlockInfo(String blockType, int weekNumber, String dateRange, String dayName, String formattedDate) {
        this.blockType = blockType;
        this.weekNumber = weekNumber;
        this.dateRange = dateRange;
        this.dayName = dayName;
        this.formattedDate = formattedDate;
    }

    public String getBlockType() {
        return blockType;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getDayName() {
        return dayName;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    // Teks ringkas untuk label dashboard
    public String getFullStatusTitle() {
        return "BLOK " + blockType + " — Minggu ke-" + weekNumber;
    }
}