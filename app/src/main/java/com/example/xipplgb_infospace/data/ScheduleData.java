package com.example.xipplgb_infospace.data;

import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.model.ScheduleItem;
import com.example.xipplgb_infospace.utils.BlockHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleData {

    // Fungsi utama: mengambil jadwal hari ini secara otomatis
    public static ArrayList<ScheduleItem> getTodaySchedule() {
        Calendar today = Calendar.getInstance();
        return getScheduleForCalendar(today);
    }

    // Fungsi fleksibel: mengambil jadwal untuk tanggal kalender tertentu
    public static ArrayList<ScheduleItem> getScheduleForCalendar(Calendar targetCal) {
        // 1. Dapatkan informasi blok dari tanggal target
        BlockInfo info = BlockHelper.getBlockInfoForDate(targetCal);

        String blockType = info.getBlockType(); // "KK" atau "MPU"
        int weekNumber = info.getWeekNumber();   // 1 atau 2
        String dayName = info.getDayName();      // "Senin", "Selasa", dst.

        // 2. Jika hari Sabtu atau Minggu, jadwal kosong
        if (dayName.equalsIgnoreCase("Sabtu") || dayName.equalsIgnoreCase("Minggu")) {
            return new ArrayList<>();
        }

        // 3. Ambil data sesuai tipe blok
        if (blockType.equalsIgnoreCase("KK")) {
            return ScheduleKKData.getSchedule(weekNumber, dayName);
        } else {
            return ScheduleMPUData.getScheduleForDay(dayName);
        }
    }
}