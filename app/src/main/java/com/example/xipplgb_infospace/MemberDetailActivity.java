package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.data.StudentData;
import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.model.Student;
import com.example.xipplgb_infospace.utils.BlockHelper;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.util.ArrayList;

public class MemberDetailActivity extends AppCompatActivity {

    private TextView btnBackMemberDetail;
    private TextView tvMemberDetailName;
    private TextView tvMemberDetailClassAbsent;
    private TextView tvMemberDetailCurrentUserBadge;
    private TextView tvMemberDetailOfficialName;
    private TextView tvMemberDetailAbsentNumber;
    private TextView tvMemberDetailBlockStatus;

    private SharedPrefHelper prefHelper;
    private int studentAbsent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

        prefHelper = new SharedPrefHelper(this);

        btnBackMemberDetail = findViewById(R.id.btnBackMemberDetail);
        tvMemberDetailName = findViewById(R.id.tvMemberDetailName);
        tvMemberDetailClassAbsent = findViewById(R.id.tvMemberDetailClassAbsent);
        tvMemberDetailCurrentUserBadge = findViewById(R.id.tvMemberDetailCurrentUserBadge);
        tvMemberDetailOfficialName = findViewById(R.id.tvMemberDetailOfficialName);
        tvMemberDetailAbsentNumber = findViewById(R.id.tvMemberDetailAbsentNumber);
        tvMemberDetailBlockStatus = findViewById(R.id.tvMemberDetailBlockStatus);

        studentAbsent = getIntent().getIntExtra("STUDENT_ABSENT", 0);

        muatDetailAnggota();

        if (btnBackMemberDetail != null) {
            btnBackMemberDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }
    }

    private void muatDetailAnggota() {
        if (studentAbsent <= 0) {
            Toast.makeText(this, "Data anggota tidak ditemukan.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Student targetStudent = null;
        ArrayList<Student> students = StudentData.getStudents();
        for (Student s : students) {
            if (s.getAbsentNumber() == studentAbsent) {
                targetStudent = s;
                break;
            }
        }

        if (targetStudent == null) {
            Toast.makeText(this, "Data anggota tidak valid.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        boolean isCurrentUser = (studentAbsent == prefHelper.getStudentAbsent());

        String officialName = targetStudent.getName();
        String headerName = isCurrentUser ? prefHelper.getDisplayName() : officialName;

        tvMemberDetailName.setText(headerName);
        tvMemberDetailClassAbsent.setText("XI PPLG B | Absen " + targetStudent.getAbsentNumber());
        tvMemberDetailOfficialName.setText(officialName);
        tvMemberDetailAbsentNumber.setText("Nomor Absen " + targetStudent.getAbsentNumber());

        if (isCurrentUser) {
            tvMemberDetailCurrentUserBadge.setVisibility(View.VISIBLE);
        } else {
            tvMemberDetailCurrentUserBadge.setVisibility(View.GONE);
        }

        BlockInfo blockInfo = BlockHelper.getCurrentBlockInfo();
        if (tvMemberDetailBlockStatus != null) {
            tvMemberDetailBlockStatus.setText(blockInfo.getFullStatusTitle().toUpperCase());
        }
    }
}
