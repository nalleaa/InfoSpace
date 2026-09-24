package com.example.xipplgb_infospace.data;

import com.example.xipplgb_infospace.model.Teacher;
import java.util.ArrayList;

public class TeacherData {

    public static ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> list = new ArrayList<>();

        // 1. Data Guru Produktif PPLG (R1 - R6)
        list.add(new Teacher(134, "Dra. Mar'atus Sholikhah", "R4"));
        list.add(new Teacher(135, "Sidik Pramono, S.Kom.", "R1"));
        list.add(new Teacher(136, "Wiji Khurniawati, S.Kom", "R2"));
        list.add(new Teacher(137, "Arika Prihastanti Sutami, S.Pd.", "R3"));
        list.add(new Teacher(138, "Wahyudi, S.Kom.", "R5"));
        list.add(new Teacher(139, "Isna Aldila Kusuma Putra, S.Kom.", "R6"));

        // 2. Data Guru MPU (Mata Pelajaran Umum)
        list.add(new Teacher(5, "Syahrun Mubarok, S.Pd.", "PABP"));
        list.add(new Teacher(13, "Budi Yuli Esti, S.Pd.", "PP"));
        list.add(new Teacher(22, "Anita Ramadhani Permatasari, S.Pd.", "BINDO"));
        list.add(new Teacher(24, "Sunarto, S.Pd.", "MAT"));
        list.add(new Teacher(35, "Nunuk Sri Haryanti, S.Pd.", "SEJ"));
        list.add(new Teacher(43, "Sasmitoadi, S.Pd.", "BING"));
        list.add(new Teacher(51, "Arif Widodo, S.Pd.", "PJOK"));
        list.add(new Teacher(55, "Agus Santoso, S.Pd.", "BJW"));
        list.add(new Teacher(67, "Dra. Indah Kris Setyorini", "BK"));

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

    // Fungsi untuk mencari Guru berdasarkan Kode Resmi (misal: 137 atau 5)
    public static Teacher getTeacherByOfficialCode(int officialCode) {
        for (Teacher teacher : getTeachers()) {
            if (teacher.getOfficialCode() == officialCode) {
                return teacher;
            }
        }
        return new Teacher(0, "Guru Tidak Ditemukan", "-");
    }
}
