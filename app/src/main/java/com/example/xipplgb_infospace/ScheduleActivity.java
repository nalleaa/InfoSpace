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

public class ScheduleActivity extends AppCompatActivity {

    private TextView btnBackSchedule;
    private TextView tvScheduleBlockTitle;
    private TextView tvScheduleDateRange;
    private TextView tvActiveDayTitle;
    private LinearLayout containerScheduleList;

    private Button btnDaySenin, btnDaySelasa, btnDayRabu, btnDayKamis, btnDayJumat;
    private Button[] dayButtons;

    private BlockInfo currentBlock;
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

        btnDaySenin = findViewById(R.id.btnDaySenin);
        btnDaySelasa = findViewById(R.id.btnDaySelasa);
        btnDayRabu = findViewById(R.id.btnDayRabu);
        btnDayKamis = findViewById(R.id.btnDayKamis);
        btnDayJumat = findViewById(R.id.btnDayJumat);

        dayButtons = new Button[]{btnDaySenin, btnDaySelasa, btnDayRabu, btnDayKamis, btnDayJumat};

        // 2. Ambil Informasi Blok Berjalan
        currentBlock = BlockHelper.getCurrentBlockInfo();
        tvScheduleBlockTitle.setText(currentBlock.getFullStatusTitle().toUpperCase());
        tvScheduleDateRange.setText(currentBlock.getDateRange());

        // Default: jika hari ini Senin-Jumat, langsung pilih hari ini
        String todayName = currentBlock.getDayName();
        if (todayName.equalsIgnoreCase("Selasa") || todayName.equalsIgnoreCase("Rabu") ||
                todayName.equalsIgnoreCase("Kamis") || todayName.equalsIgnoreCase("Jumat")) {
            selectedDay = todayName;
        } else {
            selectedDay = "Senin";
        }

        updateDaySelection(selectedDay);

        // 3. Tombol Kembali
        if (btnBackSchedule != null) {
            btnBackSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        // 4. Pasang Listener Tombol Hari
        setupDayButton(btnDaySenin, "Senin");
        setupDayButton(btnDaySelasa, "Selasa");
        setupDayButton(btnDayRabu, "Rabu");
        setupDayButton(btnDayKamis, "Kamis");
        setupDayButton(btnDayJumat, "Jumat");
    }

    private void setupDayButton(final Button btn, final String dayName) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = dayName;
                updateDaySelection(selectedDay);
            }
        });
    }

    private void updateDaySelection(String dayName) {
        tvActiveDayTitle.setText("Jadwal Hari " + dayName);

        // Ubah tampilan tombol yang sedang aktif
        for (Button btn : dayButtons) {
            if (btn.getText().toString().equalsIgnoreCase(dayName)) {
                btn.setBackgroundResource(R.drawable.bg_button_active);
                btn.setTextColor(Color.WHITE);
            } else {
                btn.setBackgroundResource(R.drawable.bg_button_inactive);
                btn.setTextColor(Color.parseColor("#475569"));
            }
        }

        // Tampilkan daftar jadwal untuk hari yang dipilih
        tampilkanJadwal(dayName);
    }

    private void tampilkanJadwal(String dayName) {
        containerScheduleList.removeAllViews();

        ArrayList<ScheduleItem> items;
        if (currentBlock.getBlockType().equalsIgnoreCase("KK")) {
            items = ScheduleKKData.getSchedule(currentBlock.getWeekNumber(), dayName);
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

            // Vibrant white card background with crisp rounded corners and stroke
            GradientDrawable cardBg = new GradientDrawable();
            cardBg.setColor(Color.WHITE);
            cardBg.setCornerRadius(28f);
            cardBg.setStroke(3, Color.parseColor("#CBD5E1"));
            card.setBackground(cardBg);
            card.setElevation(4f);
            card.setPadding(36, 28, 36, 28);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 20);
            card.setLayoutParams(params);

            // Time & Period Badge (Pill Badge)
            TextView tvTime = new TextView(this);
            tvTime.setText("⏱️  " + item.getTimeRange() + "  (" + item.getPeriodRange() + ")");
            tvTime.setTextSize(13);
            tvTime.setTypeface(null, Typeface.BOLD);
            tvTime.setTextColor(Color.parseColor("#3730A3"));

            GradientDrawable pillBg = new GradientDrawable();
            pillBg.setColor(Color.parseColor("#EEF2FF"));
            pillBg.setCornerRadius(20f);
            tvTime.setBackground(pillBg);
            tvTime.setPadding(24, 10, 24, 10);

            LinearLayout.LayoutParams pillParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            pillParams.setMargins(0, 0, 0, 12);
            tvTime.setLayoutParams(pillParams);

            // Subject Name (Large and bold)
            TextView tvSubject = new TextView(this);
            tvSubject.setText(item.getSubjectName());
            tvSubject.setTextColor(Color.parseColor("#0F172A"));
            tvSubject.setTextSize(17);
            tvSubject.setTypeface(null, Typeface.BOLD);
            tvSubject.setPadding(0, 4, 0, 6);

            // Teacher Info
            TextView tvTeacher = new TextView(this);
            tvTeacher.setText("👨‍🏫 " + item.getTeacherName() + " — Kode: " + item.getTeacherCode());
            tvTeacher.setTextColor(Color.parseColor("#475569"));
            tvTeacher.setTextSize(14);

            card.addView(tvTime);
            card.addView(tvSubject);
            card.addView(tvTeacher);

            containerScheduleList.addView(card);
        }
    }
}
