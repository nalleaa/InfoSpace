package com.example.xipplgb_infospace.utils;

import com.example.xipplgb_infospace.model.BlockInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BlockHelper {

    // Titik acuan resmi: Senin, 21 September 2026 (KK Minggu 1)
    private static final int BASE_YEAR = 2026;
    private static final int BASE_MONTH = Calendar.SEPTEMBER; // Index 8 di Java Calendar
    private static final int BASE_DAY = 21;

    public static BlockInfo getCurrentBlockInfo() {
        Calendar today = Calendar.getInstance();
        return getBlockInfoForDate(today);
    }

    public static BlockInfo getBlockInfoForDate(Calendar targetDate) {
        // 1. Inisialisasi Tanggal Basis Acuan (Senin 21 Sep 2026)
        Calendar baseCal = Calendar.getInstance();
        baseCal.set(BASE_YEAR, BASE_MONTH, BASE_DAY, 0, 0, 0);
        baseCal.set(Calendar.MILLISECOND, 0);

        // 2. Normalisasi targetDate ke awal hari (pukul 00:00:00)
        Calendar calcCal = (Calendar) targetDate.clone();
        calcCal.set(Calendar.HOUR_OF_DAY, 0);
        calcCal.set(Calendar.MINUTE, 0);
        calcCal.set(Calendar.SECOND, 0);
        calcCal.set(Calendar.MILLISECOND, 0);

        // 3. Hitung selisih hari
        long diffMillis = calcCal.getTimeInMillis() - baseCal.getTimeInMillis();
        long diffDays = diffMillis / (24 * 60 * 60 * 1000);

        // Menghitung selisih minggu (7 hari per minggu)
        long diffWeeks = (long) Math.floor((double) diffDays / 7.0);

        // 4. Modulo 4 untuk rotasi 4 minggu
        int cycleIndex = (int) (diffWeeks % 4);
        if (cycleIndex < 0) {
            cycleIndex += 4;
        }

        String blockType;
        int weekNumber;

        switch (cycleIndex) {
            case 0: // Minggu ke-1 sejak 21 Sep
                blockType = "KK";
                weekNumber = 1;
                break;
            case 1: // Minggu ke-2 sejak 21 Sep
                blockType = "KK";
                weekNumber = 2;
                break;
            case 2: // Minggu ke-3 sejak 21 Sep
                blockType = "MPU";
                weekNumber = 1;
                break;
            case 3: // Minggu ke-4 sejak 21 Sep
            default:
                blockType = "MPU";
                weekNumber = 2;
                break;
        }

        // 5. Hitung Rentang Tanggal Mingguan (Senin s.d. Minggu)
        Calendar startOfWeek = (Calendar) calcCal.clone();
        int dayOfWeek = startOfWeek.get(Calendar.DAY_OF_WEEK);

        // Java Calendar: Minggu = 1, Senin = 2, ..., Sabtu = 7
        int daysToSubtract = (dayOfWeek == Calendar.SUNDAY) ? 6 : (dayOfWeek - Calendar.MONDAY);
        startOfWeek.add(Calendar.DAY_OF_MONTH, -daysToSubtract);

        Calendar endOfWeek = (Calendar) startOfWeek.clone();
        endOfWeek.add(Calendar.DAY_OF_MONTH, 6);

        Locale localeId = new Locale("in", "ID");
        SimpleDateFormat monthYearFmt = new SimpleDateFormat("MMMM yyyy", localeId);
        SimpleDateFormat dayFmt = new SimpleDateFormat("d", localeId);

        String dateRange;
        if (startOfWeek.get(Calendar.MONTH) == endOfWeek.get(Calendar.MONTH)) {
            dateRange = dayFmt.format(startOfWeek.getTime()) + "–" +
                    dayFmt.format(endOfWeek.getTime()) + " " +
                    monthYearFmt.format(endOfWeek.getTime());
        } else {
            SimpleDateFormat fullFmt = new SimpleDateFormat("d MMM", localeId);
            dateRange = fullFmt.format(startOfWeek.getTime()) + " – " +
                    fullFmt.format(endOfWeek.getTime()) + " " +
                    endOfWeek.get(Calendar.YEAR);
        }

        // 6. Format nama hari & tanggal hari ini
        SimpleDateFormat dayNameFmt = new SimpleDateFormat("EEEE", localeId);
        SimpleDateFormat fullDateFmt = new SimpleDateFormat("d MMMM yyyy", localeId);

        String dayName = dayNameFmt.format(calcCal.getTime());
        String formattedDate = fullDateFmt.format(calcCal.getTime());

        return new BlockInfo(blockType, weekNumber, dateRange, dayName, formattedDate);
    }
}