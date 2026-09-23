package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xipplgb_infospace.data.StudentData;
import com.example.xipplgb_infospace.model.Student;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.util.ArrayList;

public class MembersActivity extends AppCompatActivity {

    private TextView btnBackMembers;
    private TextView tvMembersCount;
    private LinearLayout containerMembersList;
    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        prefHelper = new SharedPrefHelper(this);

        btnBackMembers = findViewById(R.id.btnBackMembers);
        tvMembersCount = findViewById(R.id.tvMembersCount);
        containerMembersList = findViewById(R.id.containerMembersList);

        if (btnBackMembers != null) {
            btnBackMembers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        muatDaftarAnggota();
    }

    private void muatDaftarAnggota() {
        containerMembersList.removeAllViews();

        ArrayList<Student> students = StudentData.getStudents();
        tvMembersCount.setText("Total: " + students.size() + " Siswa Terdaftar");

        int currentAbsent = prefHelper.getStudentAbsent();

        for (Student student : students) {
            boolean isCurrentUser = (student.getAbsentNumber() == currentAbsent);

            // Card per siswa
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.HORIZONTAL);
            card.setBackgroundResource(R.drawable.bg_card);
            card.setElevation(isCurrentUser ? 4f : 2f);
            card.setGravity(Gravity.CENTER_VERTICAL);
            card.setPadding(20, 16, 20, 16);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 10);
            card.setLayoutParams(params);

            // Kotak Bulat Nomor Absen
            TextView tvBadgeNumber = new TextView(this);
            tvBadgeNumber.setText(String.valueOf(student.getAbsentNumber()));
            tvBadgeNumber.setTextColor(getResources().getColor(isCurrentUser ? R.color.white : R.color.primary_indigo));
            tvBadgeNumber.setBackgroundResource(isCurrentUser ? R.drawable.bg_button : R.drawable.bg_badge_block);
            tvBadgeNumber.setGravity(Gravity.CENTER);
            tvBadgeNumber.setTextSize(14);
            tvBadgeNumber.setTypeface(null, android.graphics.Typeface.BOLD);

            LinearLayout.LayoutParams badgeParams = new LinearLayout.LayoutParams(90, 90);
            badgeParams.setMarginEnd(24);
            tvBadgeNumber.setLayoutParams(badgeParams);

            // Kolom Nama & Identitas
            LinearLayout colInfo = new LinearLayout(this);
            colInfo.setOrientation(LinearLayout.VERTICAL);
            colInfo.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView tvName = new TextView(this);
            tvName.setText(student.getName());
            tvName.setTextColor(getResources().getColor(R.color.text_primary));
            tvName.setTextSize(14);
            tvName.setTypeface(null, android.graphics.Typeface.BOLD);

            TextView tvSub = new TextView(this);
            tvSub.setText(isCurrentUser ? "Siswa Aktif (Sesi Kamu)" : "XI PPLG B");
            tvSub.setTextColor(getResources().getColor(isCurrentUser ? R.color.primary_indigo : R.color.text_secondary));
            tvSub.setTextSize(12);

            colInfo.addView(tvName);
            colInfo.addView(tvSub);

            card.addView(tvBadgeNumber);
            card.addView(colInfo);

            // Label "Kamu" jika siswa yang sedang login
            if (isCurrentUser) {
                TextView tvYou = new TextView(this);
                tvYou.setText("Kamu");
                tvYou.setTextColor(getResources().getColor(R.color.text_kk));
                tvYou.setBackgroundResource(R.drawable.bg_badge_block);
                tvYou.setTextSize(11);
                tvYou.setTypeface(null, android.graphics.Typeface.BOLD);
                tvYou.setPadding(16, 6, 16, 6);
                card.addView(tvYou);
            }

            containerMembersList.addView(card);
        }
    }
}