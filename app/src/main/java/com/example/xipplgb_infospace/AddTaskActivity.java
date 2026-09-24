package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.Task;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    private TextView btnBackAddTask;
    private EditText etTaskTitle, etTaskSubject, etTaskDeadline, etTaskDescription;
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
        etTaskDescription = findViewById(R.id.etTaskDescription);
        btnSaveTask = findViewById(R.id.btnSaveTask);

        if (btnBackAddTask != null) {
            btnBackAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        // Buka Kalender / DatePickerDialog saat etTaskDeadline diklik
        if (etTaskDeadline != null) {
            etTaskDeadline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tampilkanDatePicker();
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

    private void tampilkanDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(selectedYear, selectedMonth, selectedDay);

                        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", new Locale("in", "ID"));
                        etTaskDeadline.setText(sdf.format(cal.getTime()));
                    }
                }, year, month, day);

        // Batasi tanggal agar tidak bisa memilih tanggal sebelum hari ini (minimal hari ini)
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void simpanDataTugas() {
        String title = etTaskTitle.getText().toString().trim();
        String subject = etTaskSubject.getText().toString().trim();
        String deadline = etTaskDeadline.getText().toString().trim();
        String description = etTaskDescription != null ? etTaskDescription.getText().toString().trim() : "";

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
            etTaskDeadline.setError("Deadline tugas wajib diisi (Pilih dari kalender).");
            etTaskDeadline.requestFocus();
            return;
        }

        // 2. Ambil Nama Pembuat & Absen dari Sesi Login
        String authorName = prefHelper.getStudentName();
        int authorAbsent = prefHelper.getStudentAbsent();

        // 3. Tanggal Pembuatan
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", new Locale("in", "ID"));
        String createdAt = sdf.format(new Date());

        String taskId = String.valueOf(System.currentTimeMillis());

        // 4. Buat Objek Task (Status awal: false / Belum Selesai)
        Task newTask = new Task(taskId, title, subject, deadline, "Biasa", authorName, authorAbsent, createdAt, false, description);
        prefHelper.saveTask(newTask);

        Toast.makeText(this, "Tugas berhasil ditambahkan.", Toast.LENGTH_SHORT).show();
        finish(); // Kembali ke TaskActivity
    }
}
