package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.utils.BlockHelper;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcomeGreeting;
    private TextView tvWelcomeClassInfo;
    private LinearLayout cardBlockHero;
    private TextView tvBadgeBlock;
    private TextView tvBlockDateRange;

    private Button btnMenuSchedule;
    private Button btnMenuCalendar;
    private Button btnMenuTask;
    private Button btnMenuInfo;
    private Button btnMenuChecklist;
    private Button btnMenuMembers;
    private Button btnMenuProfile;
    private Button btnLogoutHome;

    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prefHelper = new SharedPrefHelper(this);

        // 1. Inisialisasi komponen UI
        tvWelcomeGreeting = findViewById(R.id.tvWelcomeGreeting);
        tvWelcomeClassInfo = findViewById(R.id.tvWelcomeClassInfo);
        cardBlockHero = findViewById(R.id.cardBlockHero);
        tvBadgeBlock = findViewById(R.id.tvBadgeBlock);
        tvBlockDateRange = findViewById(R.id.tvBlockDateRange);

        btnMenuSchedule = findViewById(R.id.btnMenuSchedule);
        btnMenuCalendar = findViewById(R.id.btnMenuCalendar);
        btnMenuTask = findViewById(R.id.btnMenuTask);
        btnMenuInfo = findViewById(R.id.btnMenuInfo);
        btnMenuChecklist = findViewById(R.id.btnMenuChecklist);
        btnMenuMembers = findViewById(R.id.btnMenuMembers);
        btnMenuProfile = findViewById(R.id.btnMenuProfile);
        btnLogoutHome = findViewById(R.id.btnLogoutHome);

        // 2. Tampilkan Nama & Kelas dengan jelas (Font Besar & Tegas)
        String nama = prefHelper.getStudentName();
        int absen = prefHelper.getStudentAbsent();

        tvWelcomeGreeting.setText("Halo, " + nama + " 👋");
        tvWelcomeClassInfo.setText("Kelas XI PPLG B | Absen " + absen);

        // 3. Tampilkan Banner Status Blok
        BlockInfo info = BlockHelper.getCurrentBlockInfo();
        if (tvBadgeBlock != null) {
            tvBadgeBlock.setText(info.getFullStatusTitle().toUpperCase());
        }
        if (tvBlockDateRange != null) {
            tvBlockDateRange.setText(info.getDateRange());
        }

        // 4. Hero Banner Klik -> Buka Kalender Blok
        if (cardBlockHero != null) {
            cardBlockHero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, CalendarActivity.class));
                }
            });
        }

        // 5. Tombol Menu Jadwal
        if (btnMenuSchedule != null) {
            btnMenuSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, ScheduleActivity.class));
                }
            });
        }

        // 6. Tombol Menu Jadwal Blok (Dedicated Button)
        if (btnMenuCalendar != null) {
            btnMenuCalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, CalendarActivity.class));
                }
            });
        }

        // 7. Tombol Menu Tugas
        if (btnMenuTask != null) {
            btnMenuTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, TaskActivity.class));
                }
            });
        }

        // 8. Tombol Menu Info
        if (btnMenuInfo != null) {
            btnMenuInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, InfoActivity.class));
                }
            });
        }

        // 9. Tombol Menu Checklist
        if (btnMenuChecklist != null) {
            btnMenuChecklist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, ChecklistActivity.class));
                }
            });
        }

        // 10. Tombol Menu Anggota
        if (btnMenuMembers != null) {
            btnMenuMembers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, MembersActivity.class));
                }
            });
        }

        // 11. Tombol Menu Profil
        if (btnMenuProfile != null) {
            btnMenuProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                }
            });
        }

        // 12. Tombol Keluar (Logout)
        if (btnLogoutHome != null) {
            btnLogoutHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prefHelper.logout();
                    Toast.makeText(HomeActivity.this, "Berhasil keluar dari sesi.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
