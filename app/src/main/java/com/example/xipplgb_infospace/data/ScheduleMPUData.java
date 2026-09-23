package com.example.xipplgb_infospace.data;

import com.example.xipplgb_infospace.model.ScheduleItem;
import java.util.ArrayList;

public class ScheduleMPUData {

    public static ArrayList<ScheduleItem> getScheduleForDay(String dayName) {
        ArrayList<ScheduleItem> list = new ArrayList<>();

        if (dayName.equalsIgnoreCase("Senin")) {
            list.add(new ScheduleItem("PABP (Pendidikan Agama dan Budi Pekerti)", "Jam 1–3",
                    TimeTableData.getTimeRange(false, 1, 3), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BJW (Bahasa Jawa)", "Jam 4–5",
                    TimeTableData.getTimeRange(false, 4, 5), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BK (Bimbingan Konseling)", "Jam 6–7",
                    TimeTableData.getTimeRange(false, 6, 7), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BINDO (Bahasa Indonesia)", "Jam 8–10",
                    TimeTableData.getTimeRange(false, 8, 10), "Guru Mapel", "-"));

        } else if (dayName.equalsIgnoreCase("Selasa")) {
            list.add(new ScheduleItem("BINDO (Bahasa Indonesia)", "Jam 1–2",
                    TimeTableData.getTimeRange(false, 1, 2), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 3–4",
                    TimeTableData.getTimeRange(false, 3, 4), "Guru Mapel", "-"));
            list.add(new ScheduleItem("PP (Pendidikan Pancasila)", "Jam 5–6",
                    TimeTableData.getTimeRange(false, 5, 6), "Guru Mapel", "-"));
            list.add(new ScheduleItem("PABP (Pendidikan Agama dan Budi Pekerti)", "Jam 7–8",
                    TimeTableData.getTimeRange(false, 7, 8), "Guru Mapel", "-"));
            list.add(new ScheduleItem("PJOK (Pendidikan Jasmani, Olahraga, dan Kesehatan)", "Jam 9–10",
                    TimeTableData.getTimeRange(false, 9, 10), "Guru Mapel", "-"));

        } else if (dayName.equalsIgnoreCase("Rabu")) {
            list.add(new ScheduleItem("MAT (Matematika)", "Jam 1–3",
                    TimeTableData.getTimeRange(false, 1, 3), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 4–5",
                    TimeTableData.getTimeRange(false, 4, 5), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BINDO (Bahasa Indonesia)", "Jam 6–7",
                    TimeTableData.getTimeRange(false, 6, 7), "Guru Mapel", "-"));
            list.add(new ScheduleItem("SEJ (Sejarah)", "Jam 8–9",
                    TimeTableData.getTimeRange(false, 8, 9), "Guru Mapel", "-"));
            list.add(new ScheduleItem("PABP (Pendidikan Agama dan Budi Pekerti)", "Jam 10",
                    TimeTableData.getTimeRange(false, 10, 10), "Guru Mapel", "-"));

        } else if (dayName.equalsIgnoreCase("Kamis")) {
            list.add(new ScheduleItem("MAT (Matematika)", "Jam 1–3",
                    TimeTableData.getTimeRange(false, 1, 3), "Guru Mapel", "-"));
            list.add(new ScheduleItem("PJOK (Pendidikan Jasmani, Olahraga, dan Kesehatan)", "Jam 4–5",
                    TimeTableData.getTimeRange(false, 4, 5), "Guru Mapel", "-"));
            list.add(new ScheduleItem("PP (Pendidikan Pancasila)", "Jam 6–7",
                    TimeTableData.getTimeRange(false, 6, 7), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 8–9",
                    TimeTableData.getTimeRange(false, 8, 9), "Guru Mapel", "-"));

        } else if (dayName.equalsIgnoreCase("Jumat")) {
            list.add(new ScheduleItem("BJW (Bahasa Jawa)", "Jam 1–2",
                    TimeTableData.getTimeRange(true, 1, 2), "Guru Mapel", "-"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 3–4",
                    TimeTableData.getTimeRange(true, 3, 4), "Guru Mapel", "-"));
            list.add(new ScheduleItem("SEJ (Sejarah)", "Jam 5–6",
                    TimeTableData.getTimeRange(true, 5, 6), "Guru Mapel", "-"));
            list.add(new ScheduleItem("MAT (Matematika)", "Jam 7–8",
                    TimeTableData.getTimeRange(true, 7, 8), "Guru Mapel", "-"));
        }

        return list;
    }
}