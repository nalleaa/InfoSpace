package com.example.xipplgb_infospace.data;

import com.example.xipplgb_infospace.model.ScheduleItem;
import java.util.ArrayList;

public class ScheduleMPUData {

    public static ArrayList<ScheduleItem> getScheduleForDay(String dayName) {
        ArrayList<ScheduleItem> list = new ArrayList<>();

        if (dayName.equalsIgnoreCase("Senin")) {
            list.add(new ScheduleItem("PABP (Pendidikan Agama dan Budi Pekerti)", "Jam 1–3",
                    TimeTableData.getTimeRange(false, 1, 3), "Syahrun Mubarok, S.Pd.", "5"));
            list.add(new ScheduleItem("BJW (Bahasa Jawa)", "Jam 4–5",
                    TimeTableData.getTimeRange(false, 4, 5), "Agus Santoso, S.Pd.", "55"));
            list.add(new ScheduleItem("BK (Bimbingan Konseling)", "Jam 6–7",
                    TimeTableData.getTimeRange(false, 6, 7), "Dra. Indah Kris Setyorini", "67"));
            list.add(new ScheduleItem("BINDO (Bahasa Indonesia)", "Jam 8–10",
                    TimeTableData.getTimeRange(false, 8, 10), "Anita Ramadhani Permatasari, S.Pd.", "22"));

        } else if (dayName.equalsIgnoreCase("Selasa")) {
            list.add(new ScheduleItem("BINDO (Bahasa Indonesia)", "Jam 1–2",
                    TimeTableData.getTimeRange(false, 1, 2), "Anita Ramadhani Permatasari, S.Pd.", "22"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 3–4",
                    TimeTableData.getTimeRange(false, 3, 4), "Sasmitoadi, S.Pd.", "43"));
            list.add(new ScheduleItem("PP (Pendidikan Pancasila)", "Jam 5–6",
                    TimeTableData.getTimeRange(false, 5, 6), "Budi Yuli Esti, S.Pd.", "13"));
            list.add(new ScheduleItem("PABP (Pendidikan Agama dan Budi Pekerti)", "Jam 7–8",
                    TimeTableData.getTimeRange(false, 7, 8), "Syahrun Mubarok, S.Pd.", "5"));
            list.add(new ScheduleItem("PJOK (Pendidikan Jasmani, Olahraga, dan Kesehatan)", "Jam 9–10",
                    TimeTableData.getTimeRange(false, 9, 10), "Arif Widodo, S.Pd.", "51"));

        } else if (dayName.equalsIgnoreCase("Rabu")) {
            list.add(new ScheduleItem("MAT (Matematika)", "Jam 1–3",
                    TimeTableData.getTimeRange(false, 1, 3), "Sunarto, S.Pd.", "24"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 4–5",
                    TimeTableData.getTimeRange(false, 4, 5), "Sasmitoadi, S.Pd.", "43"));
            list.add(new ScheduleItem("BINDO (Bahasa Indonesia)", "Jam 6–7",
                    TimeTableData.getTimeRange(false, 6, 7), "Anita Ramadhani Permatasari, S.Pd.", "22"));
            list.add(new ScheduleItem("SEJ (Sejarah)", "Jam 8–9",
                    TimeTableData.getTimeRange(false, 8, 9), "Nunuk Sri Haryanti, S.Pd.", "35"));
            list.add(new ScheduleItem("PABP (Pendidikan Agama dan Budi Pekerti)", "Jam 10",
                    TimeTableData.getTimeRange(false, 10, 10), "Syahrun Mubarok, S.Pd.", "5"));

        } else if (dayName.equalsIgnoreCase("Kamis")) {
            list.add(new ScheduleItem("MAT (Matematika)", "Jam 1–3",
                    TimeTableData.getTimeRange(false, 1, 3), "Sunarto, S.Pd.", "24"));
            list.add(new ScheduleItem("PJOK (Pendidikan Jasmani, Olahraga, dan Kesehatan)", "Jam 4–5",
                    TimeTableData.getTimeRange(false, 4, 5), "Arif Widodo, S.Pd.", "51"));
            list.add(new ScheduleItem("PP (Pendidikan Pancasila)", "Jam 6–7",
                    TimeTableData.getTimeRange(false, 6, 7), "Budi Yuli Esti, S.Pd.", "13"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 8–9",
                    TimeTableData.getTimeRange(false, 8, 9), "Sasmitoadi, S.Pd.", "43"));

        } else if (dayName.equalsIgnoreCase("Jumat")) {
            list.add(new ScheduleItem("BJW (Bahasa Jawa)", "Jam 1–2",
                    TimeTableData.getTimeRange(true, 1, 2), "Agus Santoso, S.Pd.", "55"));
            list.add(new ScheduleItem("BING (Bahasa Inggris)", "Jam 3–4",
                    TimeTableData.getTimeRange(true, 3, 4), "Sasmitoadi, S.Pd.", "43"));
            list.add(new ScheduleItem("SEJ (Sejarah)", "Jam 5–6",
                    TimeTableData.getTimeRange(true, 5, 6), "Nunuk Sri Haryanti, S.Pd.", "35"));
            list.add(new ScheduleItem("MAT (Matematika)", "Jam 7–8",
                    TimeTableData.getTimeRange(true, 7, 8), "Sunarto, S.Pd.", "24"));
        }

        return list;
    }
}
