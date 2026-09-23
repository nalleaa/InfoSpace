package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.InfoItem;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddInfoActivity extends AppCompatActivity {

    private TextView btnBackAddInfo;
    private EditText etInfoTitle, etInfoContent;
    private Button btnSaveInfo;
    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        prefHelper = new SharedPrefHelper(this);

        btnBackAddInfo = findViewById(R.id.btnBackAddInfo);
        etInfoTitle = findViewById(R.id.etInfoTitle);
        etInfoContent = findViewById(R.id.etInfoContent);
        btnSaveInfo = findViewById(R.id.btnSaveInfo);

        if (btnBackAddInfo != null) {
            btnBackAddInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanDataInfo();
            }
        });
    }

    private void simpanDataInfo() {
        String title = etInfoTitle.getText().toString().trim();
        String content = etInfoContent.getText().toString().trim();

        // 1. Validasi Input Kosong
        if (title.isEmpty()) {
            etInfoTitle.setError("Judul pengumuman wajib diisi.");
            etInfoTitle.requestFocus();
            return;
        }

        if (content.isEmpty()) {
            etInfoContent.setError("Isi pengumuman wajib diisi.");
            etInfoContent.requestFocus();
            return;
        }

        // 2. Ambil Nama Pembuat & Absen dari Sesi
        String authorName = prefHelper.getStudentName();
        int authorAbsent = prefHelper.getStudentAbsent();

        // 3. Tanggal Terbit
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", new Locale("in", "ID"));
        String createdAt = sdf.format(new Date());

        String infoId = String.valueOf(System.currentTimeMillis());

        // 4. Simpan ke SharedPreferences
        InfoItem newInfo = new InfoItem(infoId, title, content, authorName, authorAbsent, createdAt);
        prefHelper.saveInfo(newInfo);

        Toast.makeText(this, "Pengumuman berhasil diterbitkan.", Toast.LENGTH_SHORT).show();
        finish(); // Kembali ke InfoActivity
    }
}