package com.example.xipplgb_infospace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.Task;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

public class TaskDetailActivity extends AppCompatActivity {

    private TextView btnBackTaskDetail;
    private TextView tvDetailSubject;
    private TextView tvDetailStatusBadge;
    private TextView tvDetailTitle;
    private TextView tvDetailDeadline;
    private TextView tvDetailAuthor;

    private EditText etDetailDescription;
    private Button btnSaveDescriptionDetail;
    private Button btnToggleStatusDetail;
    private Button btnDeleteTaskDetail;

    private SharedPrefHelper prefHelper;
    private String taskId;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        prefHelper = new SharedPrefHelper(this);

        btnBackTaskDetail = findViewById(R.id.btnBackTaskDetail);
        tvDetailSubject = findViewById(R.id.tvDetailSubject);
        tvDetailStatusBadge = findViewById(R.id.tvDetailStatusBadge);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailDeadline = findViewById(R.id.tvDetailDeadline);
        tvDetailAuthor = findViewById(R.id.tvDetailAuthor);

        etDetailDescription = findViewById(R.id.etDetailDescription);
        btnSaveDescriptionDetail = findViewById(R.id.btnSaveDescriptionDetail);
        btnToggleStatusDetail = findViewById(R.id.btnToggleStatusDetail);
        btnDeleteTaskDetail = findViewById(R.id.btnDeleteTaskDetail);

        taskId = getIntent().getStringExtra("TASK_ID");

        muatDataDetailTugas();

        if (btnBackTaskDetail != null) {
            btnBackTaskDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        // Tombol Simpan Perubahan Deskripsi
        if (btnSaveDescriptionDetail != null) {
            btnSaveDescriptionDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    simpanDeskripsiTugas();
                }
            });
        }

        // Tombol Ubah Status Selesai/Belum Selesai
        if (btnToggleStatusDetail != null) {
            btnToggleStatusDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleStatusTugas();
                }
            });
        }

        // Tombol Hapus Tugas Ini
        if (btnDeleteTaskDetail != null) {
            btnDeleteTaskDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tampilkanDialogHapusTugas();
                }
            });
        }
    }

    private void muatDataDetailTugas() {
        if (taskId == null || taskId.isEmpty()) {
            Toast.makeText(this, "ID tugas tidak valid.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currentTask = prefHelper.getTaskById(taskId);
        if (currentTask == null) {
            Toast.makeText(this, "Tugas tidak ditemukan.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvDetailSubject.setText(currentTask.getSubject());
        tvDetailTitle.setText(currentTask.getTitle());
        tvDetailDeadline.setText("Batas Waktu: " + currentTask.getDeadline());
        tvDetailAuthor.setText("Ditambahkan oleh: " + currentTask.getAuthorName() + " (Absen " + currentTask.getAuthorAbsent() + ") pada " + currentTask.getCreatedAt());

        etDetailDescription.setText(currentTask.getDescription());

        // Update Status Badge
        if (currentTask.isCompleted()) {
            tvDetailStatusBadge.setText("Selesai");
            tvDetailStatusBadge.setTextColor(Color.parseColor("#166534"));

            GradientDrawable statusBg = new GradientDrawable();
            statusBg.setColor(Color.parseColor("#DCFCE7"));
            statusBg.setCornerRadius(16f);
            tvDetailStatusBadge.setBackground(statusBg);
        } else {
            tvDetailStatusBadge.setText("Belum Selesai");
            tvDetailStatusBadge.setTextColor(Color.parseColor("#B45309"));

            GradientDrawable statusBg = new GradientDrawable();
            statusBg.setColor(Color.parseColor("#FEF3C7"));
            statusBg.setCornerRadius(16f);
            tvDetailStatusBadge.setBackground(statusBg);
        }
    }

    private void simpanDeskripsiTugas() {
        if (currentTask == null) return;
        String newDesc = etDetailDescription.getText().toString().trim();
        prefHelper.updateTaskDescription(currentTask.getId(), newDesc);
        Toast.makeText(this, "Deskripsi tugas berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        muatDataDetailTugas();
    }

    private void toggleStatusTugas() {
        if (currentTask == null) return;
        boolean newStatus = !currentTask.isCompleted();
        prefHelper.updateTaskStatus(currentTask.getId(), newStatus);
        muatDataDetailTugas();
        Toast.makeText(this, newStatus ? "Status tugas diubah menjadi SELESAI!" : "Status tugas diubah menjadi Belum Selesai.", Toast.LENGTH_SHORT).show();
    }

    private void tampilkanDialogHapusTugas() {
        if (currentTask == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Tugas");
        builder.setMessage("Apakah Anda yakin ingin menghapus tugas \"" + currentTask.getTitle() + "\"?");

        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prefHelper.deleteTask(currentTask.getId());
                Toast.makeText(TaskDetailActivity.this, "Tugas berhasil dihapus.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
