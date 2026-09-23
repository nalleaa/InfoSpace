package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.utils.BlockHelper;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private TextView btnBackCalendar;
    private LinearLayout containerCalendarWeeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        btnBackCalendar = findViewById(R.id.btnBackCalendar);
        containerCalendarWeeks = findViewById(R.id.containerCalendarWeeks);

        // Gunakan onBackPressed() agar pasti menutup halaman
        if (btnBackCalendar != null) {
            btnBackCalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        muatDaftarMingguBlok();
    }

    private void muatDaftarMingguBlok() {
        if (containerCalendarWeeks == null) return;
        containerCalendarWeeks.removeAllViews();

        BlockInfo currentInfo = BlockHelper.getCurrentBlockInfo();

        Calendar cal = Calendar.getInstance();
        cal.set(2026, Calendar.SEPTEMBER, 21, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        for (int i = 0; i < 12; i++) {
            Calendar targetCal = (Calendar) cal.clone();
            targetCal.add(Calendar.DAY_OF_YEAR, i * 7);

            BlockInfo info = BlockHelper.getBlockInfoForDate(targetCal);
            boolean isCurrentWeek = info.getDateRange().equals(currentInfo.getDateRange());

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setBackgroundResource(R.drawable.bg_card);
            card.setElevation(2f);
            card.setPadding(32, 24, 32, 24);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 16);
            card.setLayoutParams(params);

            LinearLayout headerRow = new LinearLayout(this);
            headerRow.setOrientation(LinearLayout.HORIZONTAL);

            TextView tvBadge = new TextView(this);
            tvBadge.setPadding(20, 8, 20, 8);
            tvBadge.setTextSize(11);
            tvBadge.setTypeface(null, android.graphics.Typeface.BOLD);

            if (info.getBlockType().equalsIgnoreCase("KK")) {
                tvBadge.setText("BLOK KK — MINGGU KE-" + info.getWeekNumber());
                tvBadge.setBackgroundResource(R.drawable.bg_badge_block);
                tvBadge.setTextColor(getResources().getColor(R.color.text_kk));
            } else {
                tvBadge.setText("BLOK MPU — MINGGU KE-" + info.getWeekNumber());
                tvBadge.setBackgroundResource(R.drawable.bg_badge_mpu);
                tvBadge.setTextColor(getResources().getColor(R.color.text_mpu));
            }
            headerRow.addView(tvBadge);

            if (isCurrentWeek) {
                TextView tvCurrentTag = new TextView(this);
                tvCurrentTag.setText("● Minggu Ini");
                tvCurrentTag.setTextColor(getResources().getColor(R.color.primary_indigo));
                tvCurrentTag.setTextSize(12);
                tvCurrentTag.setTypeface(null, android.graphics.Typeface.BOLD);
                tvCurrentTag.setPadding(24, 8, 0, 0);
                headerRow.addView(tvCurrentTag);
            }

            TextView tvDateRange = new TextView(this);
            tvDateRange.setText(info.getDateRange());
            tvDateRange.setTextColor(getResources().getColor(R.color.text_primary));
            tvDateRange.setTextSize(15);
            tvDateRange.setTypeface(null, android.graphics.Typeface.BOLD);
            tvDateRange.setPadding(0, 12, 0, 0);

            card.addView(headerRow);
            card.addView(tvDateRange);

            containerCalendarWeeks.addView(card);
        }
    }
}