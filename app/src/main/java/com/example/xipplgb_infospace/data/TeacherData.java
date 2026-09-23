package com.example.xipplgb_infospace.data;

import com.example.xipplgb_infospace.model.Teacher;
import java.util.ArrayList;

public class TeacherData {

    public static ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> list = new ArrayList<>();

        // 1. Data Guru Produktif PPLG (R1 - R6) Semester Ganjil 2026/2027
        list.add(new Teacher(134, "Dra. Mar'atus Sholikhah", "R4"));
        list.add(new Teacher(135, "Sidik Pramono, S.Kom.", "R1"));
        list.add(new Teacher(136, "Wiji Khurniawati, S.Kom", "R2"));
        list.add(new Teacher(137, "Arika Prihastanti Sutami, S.Pd.", "R3"));
        list.add(new Teacher(138, "Wahyudi, S.Kom.", "R5"));
        list.add(new Teacher(139, "Isna Aldila Kusuma Putra, S.Kom.", "R6"));

        return list;
    }

    // Fungsi untuk mencari Guru berdasarkan kode R (misal: "R3")
    public static Teacher getTeacherByBlockCode(String blockCode) {
        for (Teacher teacher : getTeachers()) {
            if (teacher.getBlockCode().equalsIgnoreCase(blockCode)) {
                return teacher;
            }
        }
        return new Teacher(0, "Guru Tidak Ditemukan", "-");
    }

    // Fungsi untuk mencari Guru berdasarkan Kode Resmi (misal: 137)
    public static Teacher getTeacherByOfficialCode(int officialCode) {
        for (Teacher teacher : getTeachers()) {
            if (teacher.getOfficialCode() == officialCode) {
                return teacher;
            }
        }
        return new Teacher(0, "Guru Tidak Ditemukan", "-");
    }
}