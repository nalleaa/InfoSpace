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
    private static final String KEY_DISPLAY_NAME = "displayName";
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
    public void saveLoginSession(String officialName, int absentNumber, String displayName) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_STUDENT_NAME, officialName);
        editor.putInt(KEY_STUDENT_ABSENT, absentNumber);
        if (displayName != null && !displayName.trim().isEmpty()) {
            editor.putString(KEY_DISPLAY_NAME, displayName.trim());
        } else {
            editor.putString(KEY_DISPLAY_NAME, officialName);
        }
        editor.apply();
    }

    public void saveLoginSession(String name, int absentNumber) {
        saveLoginSession(name, absentNumber, name);
    }

    // Mengecek apakah siswa sudah pernah login
    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Mengambil nama resmi siswa yang sedang login
    public String getStudentName() {
        return preferences.getString(KEY_STUDENT_NAME, "");
    }

    // Mengambil nama tampilan / username siswa yang sedang login
    public String getDisplayName() {
        String disp = preferences.getString(KEY_DISPLAY_NAME, "");
        if (disp.isEmpty()) {
            return getStudentName();
        }
        return disp;
    }

    // Mengubah nama tampilan pengguna (Edit Profil)
    public void saveDisplayName(String newDisplayName) {
        if (newDisplayName != null && !newDisplayName.trim().isEmpty()) {
            editor.putString(KEY_DISPLAY_NAME, newDisplayName.trim());
            editor.apply();
        }
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

    public void saveAllTasks(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String desc = task.getDescription() != null ? task.getDescription().replace("\n", "[NEWLINE]") : "";
            String serialized = task.getId() + "###" +
                    task.getTitle() + "###" +
                    task.getSubject() + "###" +
                    task.getDeadline() + "###" +
                    task.getDifficulty() + "###" +
                    task.getAuthorName() + "###" +
                    task.getAuthorAbsent() + "###" +
                    task.getCreatedAt() + "###" +
                    (task.isCompleted() ? "1" : "0") + "###" +
                    desc;
            sb.append(serialized);
            if (i < list.size() - 1) {
                sb.append("@@@");
            }
        }
        editor.putString(KEY_TASK_LIST, sb.toString());
        editor.apply();
    }

    // Menyimpan tugas baru ke SharedPreferences
    public void saveTask(Task task) {
        ArrayList<Task> list = getAllTasks();
        list.add(0, task); // Tambahkan ke paling atas
        saveAllTasks(list);
    }

    // Mengubah status selesai tugas
    public void updateTaskStatus(String taskId, boolean isCompleted) {
        ArrayList<Task> list = getAllTasks();
        for (Task t : list) {
            if (t.getId().equalsIgnoreCase(taskId)) {
                t.setCompleted(isCompleted);
                break;
            }
        }
        saveAllTasks(list);
    }

    // Mengubah deskripsi tugas
    public void updateTaskDescription(String taskId, String newDescription) {
        ArrayList<Task> list = getAllTasks();
        for (Task t : list) {
            if (t.getId().equalsIgnoreCase(taskId)) {
                t.setDescription(newDescription);
                break;
            }
        }
        saveAllTasks(list);
    }

    // Menghapus tugas berdasarkan ID
    public void deleteTask(String taskId) {
        ArrayList<Task> list = getAllTasks();
        ArrayList<Task> newList = new ArrayList<>();
        for (Task t : list) {
            if (!t.getId().equalsIgnoreCase(taskId)) {
                newList.add(t);
            }
        }
        saveAllTasks(newList);
    }

    // Mengambil satu tugas berdasarkan ID
    public Task getTaskById(String taskId) {
        ArrayList<Task> list = getAllTasks();
        for (Task t : list) {
            if (t.getId().equalsIgnoreCase(taskId)) {
                return t;
            }
        }
        return null;
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
                boolean isCompleted = false;
                if (fields.length >= 9) {
                    isCompleted = "1".equals(fields[8]) || "true".equalsIgnoreCase(fields[8]);
                }
                String desc = "Belum ada deskripsi tugas.";
                if (fields.length >= 10) {
                    desc = fields[9].replace("[NEWLINE]", "\n");
                }

                list.add(new Task(
                        id, title, subject, deadline, difficulty, authorName, authorAbsent, createdAt, isCompleted, desc
                ));
            }
        }
        return list;
    }

    // ==========================================
    // PENYIMPANAN INFORMASI / PENGUMUMAN (INFO)
    // ==========================================

    public void saveAllInfo(ArrayList<InfoItem> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            InfoItem info = list.get(i);
            String serialized = info.getId() + "###" +
                    info.getTitle().replace("\n", " ") + "###" +
                    info.getContent().replace("\n", "[NEWLINE]") + "###" +
                    info.getAuthorName() + "###" +
                    info.getAuthorAbsent() + "###" +
                    info.getCreatedAt();
            sb.append(serialized);
            if (i < list.size() - 1) {
                sb.append("@@@");
            }
        }
        editor.putString(KEY_INFO_LIST, sb.toString());
        editor.apply();
    }

    public void saveInfo(InfoItem info) {
        ArrayList<InfoItem> list = getAllInfo();
        list.add(0, info);
        saveAllInfo(list);
    }

    public void deleteInfo(String infoId) {
        ArrayList<InfoItem> list = getAllInfo();
        ArrayList<InfoItem> newList = new ArrayList<>();
        for (InfoItem item : list) {
            if (!item.getId().equalsIgnoreCase(infoId)) {
                newList.add(item);
            }
        }
        saveAllInfo(newList);
    }

    public InfoItem getInfoById(String infoId) {
        ArrayList<InfoItem> list = getAllInfo();
        for (InfoItem item : list) {
            if (item.getId().equalsIgnoreCase(infoId)) {
                return item;
            }
        }
        return null;
    }

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
}
