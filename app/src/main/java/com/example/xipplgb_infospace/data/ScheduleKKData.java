package com.example.xipplgb_infospace.data;

import com.example.xipplgb_infospace.model.ScheduleItem;
import com.example.xipplgb_infospace.model.Teacher;

import java.util.ArrayList;

public class ScheduleKKData {

    public static ArrayList<ScheduleItem> getSchedule(int weekNumber, String dayName) {
        ArrayList<ScheduleItem> list = new ArrayList<>();

        if (weekNumber == 1) {
            // ================= MINGGU 1 =================
            if (dayName.equalsIgnoreCase("Senin")) {
                Teacher tR3 = TeacherData.getTeacherByBlockCode("R3");
                list.add(new ScheduleItem(
                        "PTGM — Pemrograman Text Grafik Multimedia",
                        "Jam 1–12",
                        TimeTableData.getTimeRange(false, 1, 12),
                        tR3.getName(),
                        "Kode Guru: " + tR3.getOfficialCode() + " (" + tR3.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Selasa")) {
                Teacher tR3 = TeacherData.getTeacherByBlockCode("R3");
                Teacher tR6 = TeacherData.getTeacherByBlockCode("R6");

                list.add(new ScheduleItem(
                        "PTGM — Pemrograman Text Grafik Multimedia",
                        "Jam 1–3",
                        TimeTableData.getTimeRange(false, 1, 3),
                        tR3.getName(),
                        "Kode Guru: " + tR3.getOfficialCode() + " (" + tR3.getBlockCode() + ")"
                ));
                list.add(new ScheduleItem(
                        "PPB — Pemrograman Perangkat Bergerak",
                        "Jam 4–12",
                        TimeTableData.getTimeRange(false, 4, 12),
                        tR6.getName(),
                        "Kode Guru: " + tR6.getOfficialCode() + " (" + tR6.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Rabu")) {
                Teacher tR6 = TeacherData.getTeacherByBlockCode("R6");
                list.add(new ScheduleItem(
                        "PPB — Pemrograman Perangkat Bergerak",
                        "Jam 1–8",
                        TimeTableData.getTimeRange(false, 1, 8),
                        tR6.getName(),
                        "Kode Guru: " + tR6.getOfficialCode() + " (" + tR6.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Kamis")) {
                Teacher tR3 = TeacherData.getTeacherByBlockCode("R3");
                list.add(new ScheduleItem(
                        "MPP — Mata Pelajaran Pilihan",
                        "Jam 1–7",
                        TimeTableData.getTimeRange(false, 1, 7),
                        tR3.getName(),
                        "Kode Guru: " + tR3.getOfficialCode() + " (" + tR3.getBlockCode() + ")"
                ));
                list.add(new ScheduleItem(
                        "KKA — Koding dan Kecerdasan Artifisial",
                        "Jam 8–12",
                        TimeTableData.getTimeRange(false, 8, 12),
                        tR3.getName(),
                        "Kode Guru: " + tR3.getOfficialCode() + " (" + tR3.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Jumat")) {
                Teacher tR3 = TeacherData.getTeacherByBlockCode("R3");
                Teacher tR4 = TeacherData.getTeacherByBlockCode("R4");

                list.add(new ScheduleItem(
                        "KKA — Koding dan Kecerdasan Artifisial",
                        "Jam 1–4",
                        TimeTableData.getTimeRange(true, 1, 4),
                        tR3.getName(),
                        "Kode Guru: " + tR3.getOfficialCode() + " (" + tR3.getBlockCode() + ")"
                ));
                list.add(new ScheduleItem(
                        "KIK — Kreativitas, Inovasi, dan Kewirausahaan",
                        "Jam 5–8",
                        TimeTableData.getTimeRange(true, 5, 8),
                        tR4.getName(),
                        "Kode Guru: " + tR4.getOfficialCode() + " (" + tR4.getBlockCode() + ")"
                ));
            }
        } else if (weekNumber == 2) {
            // ================= MINGGU 2 =================
            if (dayName.equalsIgnoreCase("Senin")) {
                Teacher tR4 = TeacherData.getTeacherByBlockCode("R4");
                list.add(new ScheduleItem(
                        "KIK — Kreativitas, Inovasi, dan Kewirausahaan",
                        "Jam 1–12",
                        TimeTableData.getTimeRange(false, 1, 12),
                        tR4.getName(),
                        "Kode Guru: " + tR4.getOfficialCode() + " (" + tR4.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Selasa")) {
                Teacher tR4 = TeacherData.getTeacherByBlockCode("R4");
                Teacher tR5 = TeacherData.getTeacherByBlockCode("R5");

                list.add(new ScheduleItem(
                        "KIK — Kreativitas, Inovasi, dan Kewirausahaan",
                        "Jam 1–3",
                        TimeTableData.getTimeRange(false, 1, 3),
                        tR4.getName(),
                        "Kode Guru: " + tR4.getOfficialCode() + " (" + tR4.getBlockCode() + ")"
                ));
                list.add(new ScheduleItem(
                        "PW — Pemrograman Web",
                        "Jam 4–10",
                        TimeTableData.getTimeRange(false, 4, 10),
                        tR5.getName(),
                        "Kode Guru: " + tR5.getOfficialCode() + " (" + tR5.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Rabu")) {
                Teacher tR5 = TeacherData.getTeacherByBlockCode("R5");
                list.add(new ScheduleItem(
                        "PW — Pemrograman Web",
                        "Jam 1–12",
                        TimeTableData.getTimeRange(false, 1, 12),
                        tR5.getName(),
                        "Kode Guru: " + tR5.getOfficialCode() + " (" + tR5.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Kamis")) {
                Teacher tR5 = TeacherData.getTeacherByBlockCode("R5");
                Teacher tR2 = TeacherData.getTeacherByBlockCode("R2");

                list.add(new ScheduleItem(
                        "PW — Pemrograman Web",
                        "Jam 1–6",
                        TimeTableData.getTimeRange(false, 1, 6),
                        tR5.getName(),
                        "Kode Guru: " + tR5.getOfficialCode() + " (" + tR5.getBlockCode() + ")"
                ));
                list.add(new ScheduleItem(
                        "BD — Basis Data",
                        "Jam 7–8",
                        TimeTableData.getTimeRange(false, 7, 8),
                        tR2.getName(),
                        "Kode Guru: " + tR2.getOfficialCode() + " (" + tR2.getBlockCode() + ")"
                ));
            } else if (dayName.equalsIgnoreCase("Jumat")) {
                Teacher tR2 = TeacherData.getTeacherByBlockCode("R2");
                list.add(new ScheduleItem(
                        "BD — Basis Data",
                        "Jam 1–6",
                        TimeTableData.getTimeRange(true, 1, 6),
                        tR2.getName(),
                        "Kode Guru: " + tR2.getOfficialCode() + " (" + tR2.getBlockCode() + ")"
                ));
            }
        }

        return list;
    }
}