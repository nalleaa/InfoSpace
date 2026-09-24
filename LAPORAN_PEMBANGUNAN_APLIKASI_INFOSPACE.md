# LAPORAN PEMBANGUNAN APLIKASI INFOSPACE
## KELAS XI PPLG B — SMK NEGERI 2 SURAKARTA

---

## BAB I: PENDAHULUAN

### 1.1 Latar Belakang
Penerapan sistem pembelajaran berbasis blok pada Sekolah Menengah Kejuruan (SMK) memerlukan manajemen informasi jadwal dan akademik yang terstruktur. Aplikasi XI PPLG B — InfoSpace dikembangkan sebagai solusi perangkat lunak berbasis Android yang bertujuan untuk mengelola jadwal pelajaran harian, direktori tugas kelas, papan pengumuman, direktori peserta didik, serta kalkulasi otomatis siklus minggu blok pembelajaran.

### 1.2 Spesifikasi Perangkat Lunak
* **Lingkungan Pengembang (IDE)**: Android Studio (Versi Terbaru)
* **Bahasa Pemrograman**: Java (JDK 17)
* **Bahasa Antarmuka (UI)**: XML (Material Design)
* **Nama Paket (Package Name)**: `com.example.xipplgb_infospace`
* **Batas Minimum SDK**: API 24 (Android 7.0 Nougat)

---

## BAB II: ARSITEKTUR DAN STRUKTUR PERANGKAT LUNAK
Aplikasi ini dibangun menggunakan pola arsitektur *Model-View-Controller* (MVC) untuk memisahkan logika data, tampilan antarmuka, dan pengendali utama:
* **Model (`model`)**: Berisi kelas-kelas entitas yang mendefinisikan variabel dan struktur data objek.
* **View (`res/layout`)**: Tata letak antarmuka pengguna berbasis berkas XML.
* **Controller (`Activity`)**: Pengendali logika interaksi dan alur kerja aplikasi berbasis bahasa Java.
* **Repository (`data`)**: Pusat penyedia data statis seperti daftar siswa, guru, dan jadwal pelajaran.
* **Utilities (`utils`)**: Kelas pembantu logika perhitungan tanggal dan pengelolaan memori lokal.

---

## BAB III: DETAIL IMPLEMENTASI BERKAS KODE

### 3.1 Paket Data dan Entitas (`model` & `data`)
1. **`Student.java`**: Merekam atribut siswa yaitu nomor absen dan nama lengkap.
2. **`Teacher.java`**: Merekam atribut guru yaitu kode resmi, nama lengkap, dan kode blok.
3. **`ScheduleItem.java`**: Merekam informasi satu mata pelajaran pada jadwal harian.
4. **`Task.java`**: Merekam atribut tugas kelas yaitu judul, mapel, deadline, dan pembuat.
5. **`InfoItem.java`**: Merekam atribut pengumuman kelas yaitu judul, isi, dan pembuat.
6. **`BlockInfo.java`**: Merekam status kalkulasi blok aktif (tipe blok, minggu ke-, dan rentang tanggal).
7. **`StudentData.java`**: Menyimpan repositori data 36 siswa terdaftar XI PPLG B.
8. **`TeacherData.java`**: Menyimpan repositori 15 guru pengampu Produktif PPLG dan MPU.
9. **`TimeTableData.java`**: Menghitung alokasi bel jam pelajaran 1–12 untuk hari reguler dan hari Jumat.
10. **`ScheduleKKData.java`**: Menyimpan data jadwal mata pelajaran Konsentrasi Keahlian (PPLG).
11. **`ScheduleMPUData.java`**: Menyimpan data jadwal mata pelajaran umum (MPU).

### 3.2 Paket Pembantu Logika (`utils`)
* **`BlockHelper.java`**: Berfungsi sebagai kalkulator tanggal otomatis yang membaca kalender sistem perangkat (`Calendar.getInstance()`) untuk menentukan rotasi Minggu Blok KK (W1/W2) atau Blok MPU secara presisi.
* **`SharedPrefHelper.java`**: Berfungsi mengelola memori penyimpanan lokal perangkat (*SharedPreferences*) untuk menyimpan sesi otentikasi login, data tugas baru, dan pengumuman baru secara permanen.

### 3.3 Pengendali Utama (Activity)
* **`LoginActivity.java`**: Mengendalikan otentikasi masuk pengguna berdasarkan data nomor absen dan nama siswa.
* **`HomeActivity.java`**: Mengendalikan tampilan dasbor utama, informasi profil siswa, status blok aktif, dan navigasi menu.
* **`ScheduleActivity.java`**: Mengendalikan halaman jadwal pelajaran harian dengan penyaring tipe blok (KK W1, KK W2, MPU) dan penyaring hari (Senin–Jumat). Rentang tanggal hanya ditampilkan pada minggu berjalan aktif.
* **`CalendarActivity.java`**: Mengendalikan tampilan kalender rotasi minggu blok pembelajaran.
* **`TaskActivity.java` & `AddTaskActivity.java`**: Mengendalikan direktori tugas kelas dan formulir pendaftaran tugas baru.
* **`InfoActivity.java` & `AddInfoActivity.java`**: Mengendalikan papan pengumuman dan formulir penerbitan informasi kelas.
* **`MembersActivity.java`**: Mengendalikan daftar direktori 36 peserta didik kelas XI PPLG B.
* **`ProfileActivity.java`**: Mengendalikan tampilan data profil individu siswa dan opsi keluar dari aplikasi.

---

## BAB IV: PETUNJUK TAHAPAN PEMBANGUNAN APLIKASI

### Tahap 1: Inisialisasi Proyek Baru
Langkah awal dilakukan dengan membuat proyek baru pada Android Studio menggunakan templat *Empty Views Activity*, menetapkan bahasa pemrograman Java, nama paket `com.example.xipplgb_infospace`, dan batas minimum SDK API 24.

### Tahap 2: Pengaturan Warna dan Konfigurasi Tema
Konfigurasi warna ditentukan pada berkas `res/values/colors.xml` untuk menetapkan warna latar belakang utama (`#FFFFFF`), warna teks utama (`#0F172A`), dan warna aksen sistem (`#4F46E5`).

### Tahap 3: Konstruksi Model dan Repositori Data
Membuat paket `model` dan mendefinisikan kelas POJO. Selanjutnya membuat paket `data` dan memasukkan data repositori statis siswa terdaftar, data guru pengampu, serta alokasi waktu jam pelajaran.

### Tahap 4: Implementasi Logika Kalkulator dan Penyimpanan
Membuat kelas `BlockHelper` pada paket `utils` untuk kalkulasi siklus blok berbasis tanggal acuan. Selanjutnya membuat kelas `SharedPrefHelper` untuk manajemen otentikasi dan data persisten.

### Tahap 5: Desain Antarmuka Pengguna dan Pengkodean Activity
Menyusun tata letak antarmuka antarhalaman menggunakan berkas XML di dalam folder `res/layout`, menambahkan aset gambar pada folder `res/drawable`, serta mengimplementasikan kode Java pengendali interaksi pada masing-masing kelas Activity.

### Tahap 6: Pendaftaran Berkas Manifest dan Pengujian
Mendaftarkan seluruh komponen Activity pada berkas `AndroidManifest.xml` dan menetapkan `LoginActivity` sebagai titik masuk utama (*LAUNCHER*). Tahap akhir dilakukan dengan menjalankan kompilasi berkas (*Rebuild Project*) dan pengujian pada perangkat Android.
