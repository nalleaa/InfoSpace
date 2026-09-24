package com.example.xipplgb_infospace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.Task;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    private TextView btnBackTask;
    private Button btnGoAddTask;
    private TextView tvEmptyTaskMessage;
    private LinearLayout containerTaskList;
    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        prefHelper = new SharedPrefHelper(this);

        btnBackTask = findViewById(R.id.btnBackTask);
        btnGoAddTask = findViewById(R.id.btnGoAddTask);
        tvEmptyTaskMessage = findViewById(R.id.tvEmptyTaskMessage);
        containerTaskList = findViewById(R.id.containerTaskList);

        if (btnBackTask != null) {
            btnBackTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        btnGoAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        muatDaftarTugas();
    }

    private void muatDaftarTugas() {
        containerTaskList.removeAllViews();
        ArrayList<Task> tasks = prefHelper.getAllTasks();

        if (tasks.isEmpty()) {
            tvEmptyTaskMessage.setVisibility(View.VISIBLE);
            return;
        }

        tvEmptyTaskMessage.setVisibility(View.GONE);

        for (final Task task : tasks) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);

            GradientDrawable cardBg = new GradientDrawable();
            cardBg.setColor(Color.WHITE);
            cardBg.setCornerRadius(24f);
            cardBg.setStroke(2, task.isCompleted() ? Color.parseColor("#86EFAC") : Color.parseColor("#CBD5E1"));
            card.setBackground(cardBg);
            card.setElevation(3f);
            card.setPadding(32, 24, 32, 24);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 20);
            card.setLayoutParams(params);

            // Baris Atas: Radio Button Status + Mapel & Status Badge
            LinearLayout topRow = new LinearLayout(this);
            topRow.setOrientation(LinearLayout.HORIZONTAL);
            topRow.setGravity(Gravity.CENTER_VERTICAL);

            // RadioButton penanda status tugas
            RadioButton rbStatus = new RadioButton(this);
            rbStatus.setChecked(task.isCompleted());
            rbStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean newStatus = !task.isCompleted();
                    prefHelper.updateTaskStatus(task.getId(), newStatus);
                    muatDaftarTugas();
                    Toast.makeText(TaskActivity.this, newStatus ? "Tugas ditandai SELESAI" : "Tugas ditandai belum selesai.", Toast.LENGTH_SHORT).show();
                }
            });

            TextView tvSubject = new TextView(this);
            tvSubject.setText(task.getSubject());
            tvSubject.setTextColor(Color.parseColor("#2563EB"));
            tvSubject.setTextSize(13);
            tvSubject.setTypeface(null, Typeface.BOLD);
            tvSubject.setPadding(12, 0, 12, 0);
            tvSubject.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            // Badge Status Tugas (Selesai / Belum Selesai)
            TextView tvStatusBadge = new TextView(this);
            if (task.isCompleted()) {
                tvStatusBadge.setText("Selesai");
                tvStatusBadge.setTextColor(Color.parseColor("#166534"));

                GradientDrawable statusBg = new GradientDrawable();
                statusBg.setColor(Color.parseColor("#DCFCE7"));
                statusBg.setCornerRadius(16f);
                tvStatusBadge.setBackground(statusBg);
            } else {
                tvStatusBadge.setText("Belum Selesai");
                tvStatusBadge.setTextColor(Color.parseColor("#B45309"));

                GradientDrawable statusBg = new GradientDrawable();
                statusBg.setColor(Color.parseColor("#FEF3C7"));
                statusBg.setCornerRadius(16f);
                tvStatusBadge.setBackground(statusBg);
            }
            tvStatusBadge.setTextSize(12);
            tvStatusBadge.setTypeface(null, Typeface.BOLD);
            tvStatusBadge.setPadding(20, 6, 20, 6);

            topRow.addView(rbStatus);
            topRow.addView(tvSubject);
            topRow.addView(tvStatusBadge);

            // Judul Tugas
            TextView tvTitle = new TextView(this);
            tvTitle.setText(task.getTitle());
            tvTitle.setTextColor(Color.parseColor("#0F172A"));
            tvTitle.setTextSize(16);
            tvTitle.setTypeface(null, Typeface.BOLD);
            tvTitle.setPadding(0, 10, 0, 6);

            // Deadline
            TextView tvDeadline = new TextView(this);
            tvDeadline.setText("Batas Waktu: " + task.getDeadline());
            tvDeadline.setTextColor(Color.parseColor("#DC2626"));
            tvDeadline.setTextSize(13);
            tvDeadline.setTypeface(null, Typeface.BOLD);
            tvDeadline.setPadding(0, 0, 0, 6);

            // Pembuat Tugas
            TextView tvAuthor = new TextView(this);
            tvAuthor.setText("Ditambahkan oleh: " + task.getAuthorName() + " (Absen " + task.getAuthorAbsent() + ")");
            tvAuthor.setTextColor(Color.parseColor("#64748B"));
            tvAuthor.setTextSize(12);

            // Petunjuk Interaksi
            TextView tvHint = new TextView(this);
            tvHint.setText("Ketuk untuk detail & deskripsi | Tekan lama untuk hapus");
            tvHint.setTextColor(Color.parseColor("#94A3B8"));
            tvHint.setTextSize(11);
            tvHint.setPadding(0, 10, 0, 0);

            card.addView(topRow);
            card.addView(tvTitle);
            card.addView(tvDeadline);
            card.addView(tvAuthor);
            card.addView(tvHint);

            // FITUR KLIK KARTU: Buka Halaman Detail Tugas
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TaskActivity.this, TaskDetailActivity.class);
                    intent.putExtra("TASK_ID", task.getId());
                    startActivity(intent);
                }
            });

            // FITUR LONG PRESS: Menghapus tugas pilihan
            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    tampilkanDialogHapusTugas(task);
                    return true;
                }
            });

            containerTaskList.addView(card);
        }
    }

    private void tampilkanDialogHapusTugas(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Tugas");
        builder.setMessage("Apakah Anda yakin ingin menghapus tugas \"" + task.getTitle() + "\"?");

        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prefHelper.deleteTask(task.getId());
                muatDaftarTugas();
                Toast.makeText(TaskActivity.this, "Tugas berhasil dihapus.", Toast.LENGTH_SHORT).show();
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
