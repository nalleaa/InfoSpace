package com.example.xipplgb_infospace.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.xipplgb_infospace.model.InfoItem;
import com.example.xipplgb_infospace.model.Task;

import java.util.ArrayList;

public class SharedPrefHelper {

    private static final String PREF_NAME = "InfoSpaceSession";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_STUDENT_NAME = "studentName";
    private static final String KEY_STUDENT_ABSENT = "studentAbsent";
    private static final String KEY_TASK_LIST = "taskListData"; // Kunci untuk data tugas
    private static final String KEY_INFO_LIST = "infoListData"; // Kunci untuk data pengumuman

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    // Menyimpan sesi saat login berhasil
    public void saveLoginSession(String name, int absentNumber) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_STUDENT_NAME, name);
        editor.putInt(KEY_STUDENT_ABSENT, absentNumber);
        editor.apply(); // Menyimpan ke memori perangkat
    }

    // Mengecek apakah siswa sudah pernah login
    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Mengambil nama siswa yang sedang login
    public String getStudentName() {
        return preferences.getString(KEY_STUDENT_NAME, "");
    }

    // Mengambil nomor absen siswa yang sedang login
    public int getStudentAbsent() {
        return preferences.getInt(KEY_STUDENT_ABSENT, 0);
    }

    // Menghapus data login (digunakan saat Logout)
    public void logout() {
        editor.clear();
        editor.apply();
    }

    // ==========================================
    // PENYIMPANAN TUGAS (TASK)
    // ==========================================

    // Menyimpan tugas baru ke SharedPreferences
    public void saveTask(Task task) {
        String existingData = preferences.getString(KEY_TASK_LIST, "");
        // Format string per tugas: id###title###subject###deadline###difficulty###authorName###authorAbsent###createdAt
        String serialized = task.getId() + "###" +
                task.getTitle() + "###" +
                task.getSubject() + "###" +
                task.getDeadline() + "###" +
                task.getDifficulty() + "###" +
                task.getAuthorName() + "###" +
                task.getAuthorAbsent() + "###" +
                task.getCreatedAt();

        String updatedData;
        if (existingData.isEmpty()) {
            updatedData = serialized;
        } else {
            updatedData = existingData + "@@@" + serialized;
        }

        editor.putString(KEY_TASK_LIST, updatedData);
        editor.apply();
    }

    // Mengambil seluruh tugas yang tersimpan
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> list = new ArrayList<>();
        String rawData = preferences.getString(KEY_TASK_LIST, "");

        if (rawData.isEmpty()) {
            return list;
        }

        String[] taskItems = rawData.split("@@@");
        for (String item : taskItems) {
            String[] fields = item.split("###");
            if (fields.length >= 8) {
                String id = fields[0];
                String title = fields[1];
                String subject = fields[2];
                String deadline = fields[3];
                String difficulty = fields[4];
                String authorName = fields[5];
                int authorAbsent = Integer.parseInt(fields[6]);
                String createdAt = fields[7];

                list.add(new Task(
                        id, title, subject, deadline, difficulty, authorName, authorAbsent, createdAt
                ));
            }
        }
        return list;
    }

    // ==========================================
    // PENYIMPANAN INFORMASI / PENGUMUMAN (INFO)
    // ==========================================

    // Menyimpan pengumuman baru ke SharedPreferences
    public void saveInfo(InfoItem info) {
        String existingData = preferences.getString(KEY_INFO_LIST, "");
        // Format string per info: id###title###content###authorName###authorAbsent###createdAt
        String serialized = info.getId() + "###" +
                info.getTitle().replace("\n", " ") + "###" +
                info.getContent().replace("\n", "[NEWLINE]") + "###" +
                info.getAuthorName() + "###" +
                info.getAuthorAbsent() + "###" +
                info.getCreatedAt();

        String updatedData;
        if (existingData.isEmpty()) {
            updatedData = serialized;
        } else {
            updatedData = existingData + "@@@" + serialized;
        }

        editor.putString(KEY_INFO_LIST, updatedData);
        editor.apply();
    }

    // Mengambil seluruh pengumuman yang tersimpan
    public ArrayList<InfoItem> getAllInfo() {
        ArrayList<InfoItem> list = new ArrayList<>();
        String rawData = preferences.getString(KEY_INFO_LIST, "");

        if (rawData.isEmpty()) {
            return list;
        }

        String[] infoItems = rawData.split("@@@");
        for (String item : infoItems) {
            String[] fields = item.split("###");
            if (fields.length >= 6) {
                String id = fields[0];
                String title = fields[1];
                String content = fields[2].replace("[NEWLINE]", "\n");
                String authorName = fields[3];
                int authorAbsent = Integer.parseInt(fields[4]);
                String createdAt = fields[5];

                list.add(new InfoItem(
                        id, title, content, authorName, authorAbsent, createdAt
                ));
            }
        }
        return list;
    }
    // ==========================================
    // PENYIMPANAN CHECKLIST HARIAN
    // ==========================================
    public void setChecklistStatus(String keyDate, String itemId, boolean isChecked) {
        String key = "chk_" + getStudentAbsent() + "_" + keyDate + "_" + itemId;
        editor.putBoolean(key, isChecked);
        editor.apply();
    }

    public boolean getChecklistStatus(String keyDate, String itemId) {
        String key = "chk_" + getStudentAbsent() + "_" + keyDate + "_" + itemId;
        return preferences.getBoolean(key, false);
    }
}