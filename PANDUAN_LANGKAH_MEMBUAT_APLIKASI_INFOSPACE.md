# PANDUAN LANGKAH DEMI LANGKAH MEMBUAT APLIKASI
## XI PPLG B — INFOSPACE (SMK NEGERI 2 SURAKARTA)

---

### 🛠️ LINGKUP & PRASYARAT PEMBANGUNAN
* **Software IDE**: Android Studio (Jellyfish / Koala / Ladybug atau versi terbaru)
* **Bahasa Pemrograman**: Java
* **Tampilan User Interface**: XML (Material Design 3)
* **Minimum SDK**: API 24 (Android 7.0 Nougat)
* **Package Name**: `com.example.xipplgb_infospace`

---

### 📌 LANGKAH 1: INISIALISASI PROYEK BARU DI ANDROID STUDIO
1. Buka Android Studio ➔ Klik **New Project**.
2. Pilih templat **Empty Views Activity** ➔ Klik **Next**.
3. Isi konfigurasi proyek:
   - **Name**: `XI PPLG B - InfoSpace`
   - **Package name**: `com.example.xipplgb_infospace`
   - **Language**: `Java`
   - **Minimum SDK**: `API 24 (Android 7.0)`
4. Klik **Finish** dan tunggu proses Gradle Sync selesai.

---

### 🎨 LANGKAH 2: MENGATUR SKEMA WARNA APLIKASI (`colors.xml`)
Buka file `app/src/main/res/values/colors.xml` dan tentukan skema warna:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="bg_main">#FFFFFF</color>
    <color name="white">#FFFFFF</color>
    <color name="primary_indigo">#4F46E5</color>
    <color name="primary_indigo_dark">#3730A3</color>
    <color name="text_primary">#0F172A</color>
    <color name="text_secondary">#475569</color>
    <color name="text_muted">#64748B</color>
    <color name="danger_red">#EF4444</color>
    <color name="border_light">#CBD5E1</color>
</resources>
```

---

### 🧱 LANGKAH 3: MEMBUAT PACKAGE & CLASS MODEL DATA (`model`)
Buat package baru bernama `model` di bawah `com.example.xipplgb_infospace`, lalu buat 7 class POJO berikut:
1. **`Student.java`**: `int absentNumber`, `String name`.
2. **`Teacher.java`**: `int officialCode`, `String name`, `String blockCode`.
3. **`ScheduleItem.java`**: `subjectName`, `periodRange`, `timeRange`, `teacherName`, `teacherCode`.
4. **`Task.java`**: `id`, `title`, `subject`, `deadline`, `authorName`, `authorAbsent`, `createdAt`.
5. **`InfoItem.java`**: `id`, `title`, `content`, `authorName`, `authorAbsent`, `createdAt`.
6. **`BlockInfo.java`**: `blockType`, `weekNumber`, `dateRange`, `dayName`, `fullStatusTitle`.
7. **`TimeSlot.java`**: `periodNumber`, `startTime`, `endTime`.

---

### 🗄️ LANGKAH 4: MEMBUAT REPOSITORY / DATABASE SEDERHANA (`data`)
Buat package baru bernama `data` dan buat 5 class repository berikut:
1. **`StudentData.java`**: List 36 nama & absen siswa XI PPLG B.
2. **`TeacherData.java`**: List 15 guru (R1–R6 dan MPU Kode 5–67).
3. **`TimeTableData.java`**: Jadwal bel jam pelajaran 1–12 regular & Jumat.
4. **`ScheduleKKData.java`**: Jadwal mapel Produktif PPLG Minggu 1 & 2.
5. **`ScheduleMPUData.java`**: Jadwal mapel MPU Senin s/d Jumat.

---

### ⚙️ LANGKAH 5: MEMBUAT HELPER LOGIKA & MEMORI (`utils`)
Buat package baru bernama `utils` dan buat 2 class helper berikut:
1. **`BlockHelper.java`**: Kalkulator tanggal acuan (`21 Sep 2026`) dengan method `getCurrentBlockInfo()`.
2. **`SharedPrefHelper.java`**: Pengelola memori `SharedPreferences` HP untuk sesi login, tugas baru, dan pengumuman baru.

---

### 🖼️ LANGKAH 6: MENYIAPKAN RESOURCE GAMBAR & SHAPE (`res/drawable`)
1. **Copy-Paste File Gambar Ikon PNG** ke `app/src/main/res/drawable/`:
   `menu_jadwal.png`, `menu_jadwal_blok.png`, `menu_tugas.png`, `menu_info.png`, `menu_anggota.png`, `menu_profil.png`, `menu_keluar.png`.
2. **Buat File XML Shape Layout**:
   `bg_banner_gradient.xml`, `bg_menu_card_elevated.xml`, `bg_menu_danger_card.xml`, `bg_card.xml`, `bg_input.xml`, `bg_button_active.xml`, `bg_button_inactive.xml`.

---

### 📱 LANGKAH 7: MEMBUAT XML LAYOUT & KODE ACTIVITY
1. **Halaman Login**: `activity_login.xml` & `LoginActivity.java`.
2. **Halaman Dashboard Home**: `activity_home.xml` & `HomeActivity.java`.
3. **Halaman Jadwal Pelajaran**: `activity_schedule.xml` & `ScheduleActivity.java`.
4. **Halaman Kalender Blok**: `activity_calendar.xml` & `CalendarActivity.java`.
5. **Halaman Tugas Kelas**: `activity_task.xml`, `activity_add_task.xml`, `TaskActivity.java`, `AddTaskActivity.java`.
6. **Halaman Info Kelas**: `activity_info.xml`, `activity_add_info.xml`, `InfoActivity.java`, `AddInfoActivity.java`.
7. **Halaman Anggota Kelas**: `activity_members.xml` & `MembersActivity.java`.
8. **Halaman Profil Saya**: `activity_profile.xml` & `ProfileActivity.java`.

---

### 🚀 LANGKAH 8: KONFIGURASI ANDROIDMANIFEST & PENGUJIAN
1. Daftarkan seluruh Activity di `AndroidManifest.xml` dengan `LoginActivity` sebagai `LAUNCHER`.
2. Lakukan Rebuild Project: Klik **Build ➔ Rebuild Project**.
3. Jalankan aplikasi di Emulator atau HP Android fisik.
