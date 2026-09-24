# DOKUMENTASI LENGKAP APLIKASI
## XI PPLG B — INFOSPACE (SMK NEGERI 2 SURAKARTA)

---

### 📌 1. PROFIL APLIKASI
* **Nama Aplikasi**: XI PPLG B — InfoSpace
* **Platform**: Android Native (Java / XML)
* **Paket Utama**: `com.example.xipplgb_infospace`
* **Target Pengguna**: Siswa & Wali Kelas XI PPLG B, SMK Negeri 2 Surakarta
* **Tahun Ajaran**: Semester Ganjil 2026/2027
* **Fungsi Utama**: Manajemen jadwal sistem blok (KK & MPU), direktori tugas kelas, papan informasi/pengumuman, direktori anggota kelas, serta manajemen sesi siswa.

---

### 📂 2. STRUKTUR DAN PENJELASAN SELURUH BERKAS PROYEK

#### A. PACKAGE `model` (POJO / Struktur Objek Data)
1. **`Student.java`**: Menjadikan objek data siswa yang terdiri dari properti `absentNumber` (nomor absen) dan `name` (nama lengkap).
2. **`Teacher.java`**: Menjadikan objek data guru yang terdiri dari `officialCode` (kode resmi guru), `name` (nama lengkap guru), dan `blockCode` (kode ruang/inisial blok).
3. **`ScheduleItem.java`**: Menjadikan objek jam pelajaran harian yang berisi `subjectName` (nama mapel), `periodRange` (jam ke-), `timeRange` (waktu jam), `teacherName`, dan `teacherCode`.
4. **`Task.java`**: Menjadikan objek tugas kelas yang dibuat siswa, berisi `id`, `title`, `subject`, `deadline`, `authorName`, `authorAbsent`, dan `createdAt`.
5. **`InfoItem.java`**: Menjadikan objek pengumuman kelas yang berisi `id`, `title`, `content`, `authorName`, `authorAbsent`, dan `createdAt`.
6. **`BlockInfo.java`**: Menjadikan objek status sistem blok berjalan yang berisi `blockType` ("KK" atau "MPU"), `weekNumber` (minggu ke-), `dateRange` (rentang tanggal), `dayName` (nama hari), dan `fullStatusTitle`.
7. **`TimeSlot.java`**: Menjadikan objek rentang waktu jam pelajaran (jam ke-, jam mulai, dan jam selesai).

#### B. PACKAGE `data` (Repository & Database Lokal)
1. **`StudentData.java`**: Menyimpan daftar **36 Siswa Terdaftar XI PPLG B** beserta nomor absen resmi untuk keperluan otentikasi login.
2. **`TeacherData.java`**: Menyimpan daftar **15 Guru Pengampu** (Guru Produktif PPLG Kode 134–139 dan Guru MPU Kode 5–67). Dilengkapi fungsi pencarian `getTeacherByBlockCode()` dan `getTeacherByOfficialCode()`.
3. **`TimeTableData.java`**: Menghitung alokasi bel jam pelajaran (Jam ke-1 s/d Jam ke-12). Mengatur jadwal waktu reguler (Senin–Kamis) serta jadwal khusus hari Jumat.
4. **`ScheduleKKData.java`**: Menyimpan database jadwal mata pelajaran Konsentrasi Keahlian / Produktif PPLG (Pemrograman Web, Mobile, Database, KIK, KKA, PTGM) untuk **Minggu 1 dan Minggu 2**.
5. **`ScheduleMPUData.java`**: Menyimpan database jadwal mata pelajaran umum / MPU (PABP, PP, BINDO, MAT, BING, PJOK, SEJ, BJW, BK) Senin s/d Jumat.

#### C. PACKAGE `utils` (Logika Sistem & Helper)
1. **`BlockHelper.java`**: Kalkulator otomatis sistem blok yang membaca tanggal HP pengguna (`Calendar.getInstance()`) dan menghitung secara presisi apakah hari ini termasuk Blok KK (Minggu 1/2) atau MPU serta menghitung rentang tanggal mingguan.
2. **`SharedPrefHelper.java`**: Pengelola memori lokal HP (*SharedPreferences*) untuk menyimpan sesi login siswa, data tugas baru, dan pengumuman baru agar tetap tersimpan saat aplikasi ditutup.

#### D. KELAS ACTIVITY (Halaman Utama Aplikasi)
1. **`LoginActivity.java`**: Halaman login untuk memasukkan Nama & Absen dengan validasi data siswa.
2. **`HomeActivity.java`**: Dashboard utama yang menampilkan salam siswa, status blok aktif, serta 7 tombol menu navigasi.
3. **`ScheduleActivity.java`**: Halaman jadwal pelajaran interaktif dengan filter hari (Senin–Jumat) serta filter tipe blok (Blok KK W1, Blok KK W2, dan Blok MPU).
4. **`CalendarActivity.java`**: Halaman direktori kalender siklus minggu blok pembelajaran.
5. **`TaskActivity.java`**: Halaman daftar tugas kelas.
6. **`AddTaskActivity.java`**: Formulir pembuatan tugas kelas baru.
7. **`InfoActivity.java`**: Halaman papan pengumuman/informasi kelas.
8. **`AddInfoActivity.java`**: Formulir penerbitan pengumuman baru.
9. **`MembersActivity.java`**: Halaman direktori 36 anggota siswa XI PPLG B dengan penanda khusus akun aktif.
10. **`ProfileActivity.java`**: Halaman profil pengguna yang menampilkan detail diri, informasi sekolah, dan tombol keluar.

---

### 🎨 3. DOKUMENTASI STRUKTUR `res/drawable`
* **File Ikon PNG**: `menu_jadwal.png`, `menu_jadwal_blok.png`, `menu_tugas.png`, `menu_info.png`, `menu_ceklis.png`, `menu_anggota.png`, `menu_profil.png`, `menu_keluar.png`.
* **File Kartu & Layout**:
  * `bg_banner_gradient.xml`: Gradient Hero Banner (Indigo-Violet).
  * `bg_menu_card_elevated.xml`: Kartu grid menu ber-elevasi halus (`16dp`).
  * `bg_menu_danger_card.xml`: Kartu tombol keluar merah lembut.
  * `bg_card.xml`: Kartu putih standar.
  * `bg_input.xml`: Kotak input form.
  * `bg_schedule_item.xml`: Kartu item daftar jadwal harian.
  * `bg_button_active.xml` & `bg_button_inactive.xml`: Gaya tombol aktif dan tidak aktif.
