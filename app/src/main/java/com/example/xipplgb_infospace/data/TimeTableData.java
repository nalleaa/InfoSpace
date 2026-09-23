package com.example.xipplgb_infospace.data;

import com.example.xipplgb_infospace.model.TimeSlot;
import java.util.ArrayList;

public class TimeTableData {

    // Alokasi Jam Pembelajaran: SENIN S.D KAMIS (12 Jam Pelajaran)
    public static ArrayList<TimeSlot> getRegularSlots() {
        ArrayList<TimeSlot> list = new ArrayList<>();
        list.add(new TimeSlot(1, "07.00", "07.45"));
        list.add(new TimeSlot(2, "07.45", "08.30"));
        list.add(new TimeSlot(3, "08.30", "09.15"));
        list.add(new TimeSlot(4, "09.15", "10.00"));
        // Istirahat 10.00 - 10.15
        list.add(new TimeSlot(5, "10.15", "11.00"));
        list.add(new TimeSlot(6, "11.00", "11.45"));
        // Istirahat 11.45 - 12.15
        list.add(new TimeSlot(7, "12.15", "13.00"));
        list.add(new TimeSlot(8, "13.00", "13.45"));
        list.add(new TimeSlot(9, "13.45", "14.30"));
        list.add(new TimeSlot(10, "14.30", "15.15"));
        // Istirahat 15.15 - 15.30
        list.add(new TimeSlot(11, "15.30", "16.15"));
        list.add(new TimeSlot(12, "16.15", "17.00"));
        return list;
    }

    // Alokasi Jam Pembelajaran: KHUSUS HARI JUMAT
    public static ArrayList<TimeSlot> getFridaySlots() {
        ArrayList<TimeSlot> list = new ArrayList<>();
        // Pengembangan Karakter: 07.00 - 08.15
        list.add(new TimeSlot(1, "08.15", "08.45"));
        list.add(new TimeSlot(2, "08.45", "09.15"));
        list.add(new TimeSlot(3, "09.15", "09.45"));
        // Istirahat 09.45 - 10.00
        list.add(new TimeSlot(4, "10.00", "10.30"));
        list.add(new TimeSlot(5, "10.30", "11.00"));
        list.add(new TimeSlot(6, "11.00", "11.30"));
        // Istirahat Sholat Jumat 11.30 - 12.30
        list.add(new TimeSlot(7, "12.30", "13.00"));
        list.add(new TimeSlot(8, "13.00", "13.30"));
        list.add(new TimeSlot(9, "13.30", "14.00"));
        list.add(new TimeSlot(10, "14.00", "14.30"));
        // Istirahat 14.30 - 14.45
        list.add(new TimeSlot(11, "14.45", "15.15"));
        list.add(new TimeSlot(12, "15.15", "15.45"));
        return list;
    }

    // Fungsi pembantu: mengambil rentang waktu mulai dari jam awal s.d. jam akhir
    public static String getTimeRange(boolean isFriday, int startPeriod, int endPeriod) {
        ArrayList<TimeSlot> slots = isFriday ? getFridaySlots() : getRegularSlots();
        String start = "";
        String end = "";

        for (TimeSlot slot : slots) {
            if (slot.getPeriodNumber() == startPeriod) {
                start = slot.getStartTime();
            }
            if (slot.getPeriodNumber() == endPeriod) {
                end = slot.getEndTime();
            }
        }

        if (!start.isEmpty() && !end.isEmpty()) {
            return start + " - " + end;
        }
        return "-";
    }
}