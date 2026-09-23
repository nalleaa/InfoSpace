package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.Task;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    private TextView btnBackAddTask;
    private EditText etTaskTitle, etTaskSubject, etTaskDeadline;
    private RadioGroup rgDifficulty;
    private Button btnSaveTask;
    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        prefHelper = new SharedPrefHelper(this);

        btnBackAddTask = findViewById(R.id.btnBackAddTask);
        etTaskTitle = findViewById(R.id.etTaskTitle);
        etTaskSubject = findViewById(R.id.etTaskSubject);
        etTaskDeadline = findViewById(R.id.etTaskDeadline);
        rgDifficulty = findViewById(R.id.rgDifficulty);
        btnSaveTask = findViewById(R.id.btnSaveTask);

        if (btnBackAddTask != null) {
            btnBackAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanDataTugas();
            }
        });
    }

    private void simpanDataTugas() {
        String title = etTaskTitle.getText().toString().trim();
        String subject = etTaskSubject.getText().toString().trim();
        String deadline = etTaskDeadline.getText().toString().trim();

        // 1. Validasi Input Kosong
        if (title.isEmpty()) {
            etTaskTitle.setError("Nama tugas wajib diisi.");
            etTaskTitle.requestFocus();
            return;
        }

        if (subject.isEmpty()) {
            etTaskSubject.setError("Mata pelajaran wajib diisi.");
            etTaskSubject.requestFocus();
            return;
        }

        if (deadline.isEmpty()) {
            etTaskDeadline.setError("Deadline tugas wajib diisi.");
            etTaskDeadline.requestFocus();
            return;
        }

        // 2. Validasi RadioButton Kesulitan
        int selectedRadioId = rgDifficulty.getCheckedRadioButtonId();
        if (selectedRadioId == -1) {
            Toast.makeText(this, "Silakan pilih tingkat kesulitan tugas.", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rbSelected = findViewById(selectedRadioId);
        String difficulty = rbSelected.getText().toString();

        // 3. Ambil Nama Pembuat & Absen dari Sesi Login
        String authorName = prefHelper.getStudentName();
        int authorAbsent = prefHelper.getStudentAbsent();

        // 4. Tanggal Pembuatan
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", new Locale("in", "ID"));
        String createdAt = sdf.format(new Date());

        String taskId = String.valueOf(System.currentTimeMillis());

        // 5. Buat Objek Task & Simpan ke SharedPreferences
        Task newTask = new Task(taskId, title, subject, deadline, difficulty, authorName, authorAbsent, createdAt);
        prefHelper.saveTask(newTask);

        Toast.makeText(this, "Task berhasil ditambahkan.", Toast.LENGTH_SHORT).show();
        finish(); // Kembali ke TaskActivity
    }
}