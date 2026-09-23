package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.model.ChecklistItem;
import com.example.xipplgb_infospace.utils.BlockHelper;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChecklistActivity extends AppCompatActivity {

    private TextView btnBackChecklist;
    private TextView tvChecklistDateTitle;
    private LinearLayout containerChecklistItems;
    private SharedPrefHelper prefHelper;
    private String todayKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        prefHelper = new SharedPrefHelper(this);

        btnBackChecklist = findViewById(R.id.btnBackChecklist);
        tvChecklistDateTitle = findViewById(R.id.tvChecklistDateTitle);
        containerChecklistItems = findViewById(R.id.containerChecklistItems);

        // Format tanggal hari ini sebagai identitas penyimpanan (misal: 2026-09-23)
        SimpleDateFormat sdfKey = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        todayKey = sdfKey.format(new Date());

        BlockInfo info = BlockHelper.getCurrentBlockInfo();
        tvChecklistDateTitle.setText("Checklist: " + info.getDayName() + ", " + info.getFormattedDate());

        if (btnBackChecklist != null) {
            btnBackChecklist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        muatDaftarChecklist();
    }

    private void muatDaftarChecklist() {
        containerChecklistItems.removeAllViews();

        // Template item kesiapan standar PPLG
        ArrayList<ChecklistItem> defaultItems = new ArrayList<>();
        defaultItems.add(new ChecklistItem("item_1", "Membawa Laptop & Charger bawaan", false));
        defaultItems.add(new ChecklistItem("item_2", "Menyiapkan software IDE pembelajaran (Android Studio / VS Code)", false));
        defaultItems.add(new ChecklistItem("item_3", "Mengecek tugas / project sebelum kelas dimulai", false));
        defaultItems.add(new ChecklistItem("item_4", "Membawa Buku Catatan / Modul Praktikum", false));
        defaultItems.add(new ChecklistItem("item_5", "Presensi kehadiran tepat waktu sebelum jam 07.00 WIB", false));

        for (final ChecklistItem item : defaultItems) {
            // Ambil status tersimpan dari SharedPreferences
            boolean savedStatus = prefHelper.getChecklistStatus(todayKey, item.getId());
            item.setChecked(savedStatus);

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.HORIZONTAL);
            card.setBackgroundResource(R.drawable.bg_card);
            card.setElevation(2f);
            card.setPadding(20, 16, 20, 16);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 12);
            card.setLayoutParams(params);

            final CheckBox cb = new CheckBox(this);
            cb.setText(item.getTitle());
            cb.setTextColor(getResources().getColor(R.color.text_primary));
            cb.setTextSize(14);
            cb.setChecked(item.isChecked());

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setChecked(isChecked);
                    prefHelper.setChecklistStatus(todayKey, item.getId(), isChecked);
                    Toast.makeText(ChecklistActivity.this, "Status checklist diperbarui", Toast.LENGTH_SHORT).show();
                }
            });

            card.addView(cb);
            containerChecklistItems.addView(card);
        }
    }
}