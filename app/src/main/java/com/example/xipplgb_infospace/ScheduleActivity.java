package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xipplgb_infospace.data.ScheduleKKData;
import com.example.xipplgb_infospace.data.ScheduleMPUData;
import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.model.ScheduleItem;
import com.example.xipplgb_infospace.utils.BlockHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    private TextView btnBackSchedule;
    private TextView tvScheduleBlockTitle;
    private TextView tvScheduleDateRange;
    private TextView tvActiveDayTitle;
    private LinearLayout containerScheduleList;

    private Button btnModeKKW1, btnModeKKW2, btnModeMPU;
    private Button[] modeButtons;

    private Button btnDaySenin, btnDaySelasa, btnDayRabu, btnDayKamis, btnDayJumat, btnDaySabtu, btnDayMinggu;
    private Button[] dayButtons;

    private BlockInfo currentBlock;
    private String selectedMode = "KK1"; // Default mode
    private String selectedDay = "Senin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // 1. Inisialisasi Komponen View
        btnBackSchedule = findViewById(R.id.btnBackSchedule);
        tvScheduleBlockTitle = findViewById(R.id.tvScheduleBlockTitle);
        tvScheduleDateRange = findViewById(R.id.tvScheduleDateRange);
        tvActiveDayTitle = findViewById(R.id.tvActiveDayTitle);
        containerScheduleList = findViewById(R.id.containerScheduleList);

        // Mode Buttons (Filter Tipe Blok)
        btnModeKKW1 = findViewById(R.id.btnModeKKW1);
        btnModeKKW2 = findViewById(R.id.btnModeKKW2);
        btnModeMPU = findViewById(R.id.btnModeMPU);
        modeButtons = new Button[]{btnModeKKW1, btnModeKKW2, btnModeMPU};

        // Day Buttons (Senin s/d Minggu)
        btnDaySenin = findViewById(R.id.btnDaySenin);
        btnDaySelasa = findViewById(R.id.btnDaySelasa);
        btnDayRabu = findViewById(R.id.btnDayRabu);
        btnDayKamis = findViewById(R.id.btnDayKamis);
        btnDayJumat = findViewById(R.id.btnDayJumat);
        btnDaySabtu = findViewById(R.id.btnDaySabtu);
        btnDayMinggu = findViewById(R.id.btnDayMinggu);
        dayButtons = new Button[]{btnDaySenin, btnDaySelasa, btnDayRabu, btnDayKamis, btnDayJumat, btnDaySabtu, btnDayMinggu};

        // 2. Ambil Informasi Blok Berjalan Saat Ini & Mode Pilihan dari Intent
        currentBlock = BlockHelper.getCurrentBlockInfo();

        if (getIntent().hasExtra("TARGET_MODE")) {
            selectedMode = getIntent().getStringExtra("TARGET_MODE");
        } else if (currentBlock.getBlockType().equalsIgnoreCase("KK")) {
            int week = currentBlock.getWeekNumber();
            if (week > 2) week = ((week - 1) % 2) + 1; // Map week 3->1, week 4->2
            selectedMode = "KK" + week;
        } else {
            selectedMode = "MPU";
        }

        // Cek Hari Ini: Jika Sabtu atau Minggu, otomatis pilih Sabtu/Minggu sebagai tampilan awal
        Calendar todayCal = Calendar.getInstance();
        int dayOfWeek = todayCal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY) {
            selectedDay = "Sabtu";
        } else if (dayOfWeek == Calendar.SUNDAY) {
            selectedDay = "Minggu";
        } else {
            String todayName = currentBlock.getDayName();
            if (todayName.equalsIgnoreCase("Selasa") || todayName.equalsIgnoreCase("Rabu") ||
                    todayName.equalsIgnoreCase("Kamis") || todayName.equalsIgnoreCase("Jumat")) {
                selectedDay = todayName;
            } else {
                selectedDay = "Senin";
            }
        }

        // 3. Setup Events & Active Tabs
        setupModeButtons();
        setupDayButtons();

        updateModeSelection(selectedMode);
        updateDaySelection(selectedDay);

        // 4. Tombol Kembali
        if (btnBackSchedule != null) {
            btnBackSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }
    }

    private void setupModeButtons() {
        btnModeKKW1.setOnClickListener(v -> updateModeSelection("KK1"));
        btnModeKKW2.setOnClickListener(v -> updateModeSelection("KK2"));
        btnModeMPU.setOnClickListener(v -> updateModeSelection("MPU"));
    }

    private void setupDayButtons() {
        btnDaySenin.setOnClickListener(v -> updateDaySelection("Senin"));
        btnDaySelasa.setOnClickListener(v -> updateDaySelection("Selasa"));
        btnDayRabu.setOnClickListener(v -> updateDaySelection("Rabu"));
        btnDayKamis.setOnClickListener(v -> updateDaySelection("Kamis"));
        btnDayJumat.setOnClickListener(v -> updateDaySelection("Jumat"));
        btnDaySabtu.setOnClickListener(v -> updateDaySelection("Sabtu"));
        btnDayMinggu.setOnClickListener(v -> updateDaySelection("Minggu"));
    }

    private void updateModeSelection(String mode) {
        selectedMode = mode;

        // Cek apakah mode yang dipilih sama dengan minggu aktif yang sedang dijalani
        boolean isCurrentActiveWeek = false;
        if (currentBlock.getBlockType().equalsIgnoreCase("KK")) {
            int activeWeek = currentBlock.getWeekNumber();
            if (activeWeek > 2) activeWeek = ((activeWeek - 1) % 2) + 1;
            String activeKey = "KK" + activeWeek;
            if (mode.equalsIgnoreCase(activeKey)) {
                isCurrentActiveWeek = true;
            }
        } else {
            if (mode.equalsIgnoreCase("MPU")) {
                isCurrentActiveWeek = true;
            }
        }

        if (mode.startsWith("KK")) {
            int week = Integer.parseInt(mode.replace("KK", ""));
            tvScheduleBlockTitle.setText("BLOK KK — MINGGU KE-" + week);
        } else {
            tvScheduleBlockTitle.setText("BLOK MPU (MATA PELAJARAN UMUM)");
        }

        // Tanggalan HANYA MUNCUL jika minggu yang dipilih adalah minggu yang sedang dijalani!
        if (isCurrentActiveWeek && tvScheduleDateRange != null) {
            tvScheduleDateRange.setVisibility(View.VISIBLE);
            tvScheduleDateRange.setText(currentBlock.getDateRange() + " (Minggu Berjalan)");
        } else if (tvScheduleDateRange != null) {
            tvScheduleDateRange.setVisibility(View.GONE);
        }

        // Highlight tombol mode yang aktif
        for (Button btn : modeButtons) {
            boolean isSelected = false;
            if (mode.equals("KK1") && btn == btnModeKKW1) isSelected = true;
            if (mode.equals("KK2") && btn == btnModeKKW2) isSelected = true;
            if (mode.equals("MPU") && btn == btnModeMPU) isSelected = true;

            if (isSelected) {
                btn.setBackgroundResource(R.drawable.bg_button_active);
                btn.setTextColor(Color.WHITE);
            } else {
                btn.setBackgroundResource(R.drawable.bg_button_inactive);
                btn.setTextColor(Color.parseColor("#475569"));
            }
        }

        tampilkanJadwal(selectedDay);
    }

    private void updateDaySelection(String dayName) {
        selectedDay = dayName;
        tvActiveDayTitle.setText("Jadwal Hari " + dayName);

        for (Button btn : dayButtons) {
            if (btn.getText().toString().equalsIgnoreCase(dayName)) {
                btn.setBackgroundResource(R.drawable.bg_button_active);
                btn.setTextColor(Color.WHITE);
            } else {
                btn.setBackgroundResource(R.drawable.bg_button_inactive);
                btn.setTextColor(Color.parseColor("#475569"));
            }
        }

        tampilkanJadwal(selectedDay);
    }

    private void tampilkanJadwal(String dayName) {
        containerScheduleList.removeAllViews();

        // JIKA HARI SABTU ATAU MINGGU: Tampilkan Kartu Libur Spesial
        if (dayName.equalsIgnoreCase("Sabtu") || dayName.equalsIgnoreCase("Minggu")) {
            LinearLayout weekendCard = new LinearLayout(this);
            weekendCard.setOrientation(LinearLayout.VERTICAL);

            GradientDrawable cardBg = new GradientDrawable();
            cardBg.setColor(Color.parseColor("#EEF2FF")); // Soft Indigo Blue Tint
            cardBg.setCornerRadius(24f);
            cardBg.setStroke(3, Color.parseColor("#C7D2FE"));
            weekendCard.setBackground(cardBg);
            weekendCard.setElevation(4f);
            weekendCard.setPadding(40, 36, 40, 36);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 20);
            weekendCard.setLayoutParams(params);

            TextView tvHolidayTitle = new TextView(this);
            tvHolidayTitle.setText("Hari Libur Pembelajaran");
            tvHolidayTitle.setTextColor(Color.parseColor("#3730A3"));
            tvHolidayTitle.setTextSize(17);
            tvHolidayTitle.setTypeface(null, Typeface.BOLD);

            TextView tvHolidayMsg = new TextView(this);
            if (dayName.equalsIgnoreCase("Sabtu")) {
                tvHolidayMsg.setText("Ini adalah hari Sabtu. Selamat Beristirahat dan Persiapkan pembelajaran pekan depan!");
            } else {
                tvHolidayMsg.setText("Ini adalah hari Minggu. Selamat Beristirahat dan Persiapkan pembelajaran pekan depan!");
            }
            tvHolidayMsg.setTextColor(Color.parseColor("#1E1B4B"));
            tvHolidayMsg.setTextSize(15);
            tvHolidayMsg.setLineSpacing(6f, 1f);
            tvHolidayMsg.setPadding(0, 10, 0, 14);

            TextView tvHint = new TextView(this);
            tvHint.setText("Anda tetap dapat mengetuk tab hari Senin–Jumat atau memilih tipe blok di atas untuk melihat jadwal pembelajaran.");
            tvHint.setTextColor(Color.parseColor("#4F46E5"));
            tvHint.setTextSize(12);

            weekendCard.addView(tvHolidayTitle);
            weekendCard.addView(tvHolidayMsg);
            weekendCard.addView(tvHint);

            containerScheduleList.addView(weekendCard);
            return;
        }

        // UNTUK HARI SENIN - JUMAT: Muat daftar mapel
        ArrayList<ScheduleItem> items;
        if (selectedMode.startsWith("KK")) {
            int weekNum = Integer.parseInt(selectedMode.replace("KK", ""));
            items = ScheduleKKData.getSchedule(weekNum, dayName);
        } else {
            items = ScheduleMPUData.getScheduleForDay(dayName);
        }

        if (items.isEmpty()) {
            LinearLayout emptyCard = new LinearLayout(this);
            emptyCard.setOrientation(LinearLayout.VERTICAL);
            emptyCard.setBackgroundResource(R.drawable.bg_card);
            emptyCard.setPadding(40, 40, 40, 40);

            TextView tvEmpty = new TextView(this);
            tvEmpty.setText("Tidak ada jadwal mata pelajaran untuk hari " + dayName + ".");
            tvEmpty.setTextColor(Color.parseColor("#64748B"));
            tvEmpty.setTextSize(15);
            tvEmpty.setGravity(android.view.Gravity.CENTER);

            emptyCard.addView(tvEmpty);
            containerScheduleList.addView(emptyCard);
            return;
        }

        for (ScheduleItem item : items) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);

            GradientDrawable cardBg = new GradientDrawable();
            cardBg.setColor(Color.WHITE);
            cardBg.setCornerRadius(24f);
            cardBg.setStroke(2, Color.parseColor("#CBD5E1"));
            card.setBackground(cardBg);
            card.setElevation(3f);
            card.setPadding(32, 24, 32, 24);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 18);
            card.setLayoutParams(params);

            // Time & Period Badge (Pill Badge)
            TextView tvTime = new TextView(this);
            tvTime.setText(item.getTimeRange() + " (" + item.getPeriodRange() + ")");
            tvTime.setTextSize(13);
            tvTime.setTypeface(null, Typeface.BOLD);
            tvTime.setTextColor(Color.parseColor("#3730A3"));

            GradientDrawable pillBg = new GradientDrawable();
            pillBg.setColor(Color.parseColor("#EEF2FF"));
            pillBg.setCornerRadius(20f);
            tvTime.setBackground(pillBg);
            tvTime.setPadding(20, 8, 20, 8);

            LinearLayout.LayoutParams pillParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            pillParams.setMargins(0, 0, 0, 10);
            tvTime.setLayoutParams(pillParams);

            // Subject Name
            TextView tvSubject = new TextView(this);
            tvSubject.setText(item.getSubjectName());
            tvSubject.setTextColor(Color.parseColor("#0F172A"));
            tvSubject.setTextSize(16);
            tvSubject.setTypeface(null, Typeface.BOLD);
            tvSubject.setPadding(0, 4, 0, 4);

            // Teacher Info
            TextView tvTeacher = new TextView(this);
            tvTeacher.setText("Pengampu: " + item.getTeacherName() + " — Kode: " + item.getTeacherCode());
            tvTeacher.setTextColor(Color.parseColor("#475569"));
            tvTeacher.setTextSize(13);

            card.addView(tvTime);
            card.addView(tvSubject);
            card.addView(tvTeacher);

            containerScheduleList.addView(card);
        }
    }
}
