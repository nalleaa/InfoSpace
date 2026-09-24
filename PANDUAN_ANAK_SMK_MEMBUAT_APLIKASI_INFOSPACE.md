# 🚀 PANDUAN CARA BIKIN APLIKASI INFOSPACE
## Khas Gaya Anak SMK Jurusan PPLG / RPL

---

### 🎒 BONTOT PENGETAHUAN & TOOLS (SIAPIN ALAT TEMPUR)
* **Software Utama**: Android Studio (Versi Terbaru)
* **Bahasa Pemrograman**: Java & XML
* **Konsep Arsitektur**: **MVC (Model - View - Controller)**
  - **Model**: Format data / wadah variabel.
  - **View**: Tampilan antarmuka layout (`XML`).
  - **Controller/Activity**: Kodingan logika & event (`Java`).

---

### 🛠️ LANGKAH 1: BIKIN PROJECT BARU DI ANDROID STUDIO
1. Buka Android Studio ➔ Klik **New Project**.
2. Pilih templat **Empty Views Activity** ➔ Klik **Next**.
3. Isi data project:
   - **Name**: `XI PPLG B - InfoSpace`
   - **Package Name**: `com.example.xipplgb_infospace`
   - **Language**: `Java`
   - **Minimum SDK**: `API 24 (Android 7.0)`
4. Klik **Finish** dan tunggu Gradle selesai sync.

---

### 🎨 LANGKAH 2: SET WARNA APLIKASI (`colors.xml`)
Buka file `res/values/colors.xml` dan ganti warnanya biar tampilan aplikasi bersih & modern:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="bg_main">#FFFFFF</color>
    <color name="white">#FFFFFF</color>
    <color name="primary_indigo">#4F46E5</color>
    <color name="primary_indigo_dark">#3730A3</color>
    <color name="text_primary">#0F172A</color>
    <color name="text_secondary">#475569</color>
    <color name="danger_red">#EF4444</color>
</resources>
```

---

### 🧱 LANGKAH 3: BIKIN FOLDER PACKAGE & FORMAT DATA (`model`)
Klik kanan pada package `com.example.xipplgb_infospace` ➔ **New** ➔ **Package** ➔ beri nama **`model`**.
Di dalam folder `model`, buat 7 file Java POJO (Format Wadah Data):
1. **Student.java** ➔ Format data siswa (`absentNumber`, `name`).
2. **Teacher.java** ➔ Format data guru (`officialCode`, `name`, `blockCode`).
3. **ScheduleItem.java** ➔ Format item mapel (`subjectName`, `periodRange`, `timeRange`, `teacherName`, `teacherCode`).
4. **Task.java** ➔ Format tugas (`id`, `title`, `subject`, `deadline`, `authorName`, `createdAt`).
5. **InfoItem.java** ➔ Format pengumuman (`id`, `title`, `content`, `authorName`, `createdAt`).
6. **BlockInfo.java** ➔ Format status blok (`blockType`, `weekNumber`, `dateRange`, `dayName`).
7. **TimeSlot.java** ➔ Format jam bel sekolah (`periodNumber`, `startTime`, `endTime`).

---

### 🗄️ LANGKAH 4: BIKIN DATABASE LOKAL (`data`)
Buat package baru bernama **`data`**. Ini ibarat "Database Sementara" tempat nyimpen data asli:
1. **StudentData.java**: Isikan array list berisi **36 Nama & Absen Siswa XI PPLG B** asli.
2. **TeacherData.java**: Isikan array list **15 Guru** (Produktif PPLG Kode 134-139 & MPU Kode 5-67).
3. **TimeTableData.java**: Atur alokasi jam bel sekolah (Jam ke-1 s/d 12) reguler & khusus hari Jumat.
4. **ScheduleKKData.java**: Masukkan jadwal mapel Produktif PPLG (Web, Mobile, Database, KIK, KKA, PTGM) siklus W1 & W2.
5. **ScheduleMPUData.java**: Masukkan jadwal mapel MPU (Agama, PP, Bindo, Mat, Bing, PJOK, Sejarah, B.Jawa, BK).

---

### ⚙️ LANGKAH 5: BIKIN KALKULATOR BLOK & MEMORI HP (`utils`)
Buat package baru bernama **`utils`**:
1. **BlockHelper.java**: **Kalkulator Tanggal Otomatis**. Kodingan ini nge-baca tanggal dari HP kamu (`Calendar.getInstance()`) dan otomatis ngitung minggu ini masuk Blok KK (W1/W2) atau Blok MPU!
2. **SharedPrefHelper.java**: **Brankas Memori HP**. Berfungsi nyimpen sesi login siswa, tugas baru, dan pengumuman baru biar nggak hilang pas aplikasi ditutup.

---

### 🖼️ LANGKAH 6: SIAPIN GAMBAR IKON & DESAIN KARTU (`res/drawable`)
1. **Upload Gambar Ikon**: Copy 7 file gambar PNG tombol menu (`menu_jadwal.png`, `menu_jadwal_blok.png`, `menu_tugas.png`, `menu_info.png`, `menu_anggota.png`, `menu_profil.png`, `menu_keluar.png`) terus paste di folder `res/drawable`.
2. **Bikin File Shape XML Background**:
   - `bg_banner_gradient.xml` (Latar gradient Indigo header)
   - `bg_menu_card_elevated.xml` (Kartu menu grid membulat `16dp`)
   - `bg_menu_danger_card.xml` (Kartu tombol keluar merah muda)
   - `bg_button_active.xml` & `bg_button_inactive.xml`

---

### 📱 LANGKAH 7: BIKIN TAMPILAN XML & KODINGAN JAVA ACTIVITY
1. **Halaman Login (`activity_login.xml` & `LoginActivity.java`)**:
   Bikin input nama & absen. Pas tombol diklik, cek datanya ke `StudentData`. Kalau cocok, simpan sesi & buka `HomeActivity`.
2. **Dashboard Home (`activity_home.xml` & `HomeActivity.java`)**:
   Tampilkan nama siswa, info kelas, hero banner status blok, dan 7 kartu menu utama.
3. **Jadwal Pelajaran (`activity_schedule.xml` & `ScheduleActivity.java`)**:
   Tampilkan filter tipe blok (`Blok KK W1`, `Blok KK W2`, `Blok MPU`) dan tab hari (`Senin`–`Jumat`). Tanggalan cuma dimunculin di minggu yang sedang berjalan aktif.
4. **Halaman Pendukung Lainnya**:
   - Kalender Blok (`CalendarActivity`)
   - Tugas Kelas (`TaskActivity` & `AddTaskActivity`)
   - Info Kelas (`InfoActivity` & `AddInfoActivity`)
   - Anggota Kelas (`MembersActivity`)
   - Profil Saya (`ProfileActivity`)

---

### 🚀 LANGKAH 8: SETTING MANIFEST & RUN APLIKASI!
1. Buka `AndroidManifest.xml`, daftarkan semua Activity.
2. Set **`LoginActivity`** sebagai `LAUNCHER` utama.
3. Klik menu **Build ➔ Rebuild Project**.
4. Colokkan HP Android kamu via kabel USB (aktifkan USB Debugging) ➔ Klik **Run App** (tombol Play hijau ▶️)!
