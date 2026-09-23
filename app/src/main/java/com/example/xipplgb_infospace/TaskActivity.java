package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        // Tombol menuju form tambah tugas (Step 16)
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
        // Muat ulang daftar tugas saat kembali dari halaman AddTaskActivity
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

        for (Task task : tasks) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setBackgroundResource(R.drawable.bg_card);
            card.setElevation(2f);
            card.setPadding(28, 24, 28, 24);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 16);
            card.setLayoutParams(params);

            // Baris Atas: Mapel dan Badge Kesulitan
            LinearLayout topRow = new LinearLayout(this);
            topRow.setOrientation(LinearLayout.HORIZONTAL);

            TextView tvSubject = new TextView(this);
            tvSubject.setText(task.getSubject());
            tvSubject.setTextColor(getResources().getColor(R.color.primary_indigo));
            tvSubject.setTextSize(12);
            tvSubject.setTypeface(null, android.graphics.Typeface.BOLD);
            tvSubject.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView tvDiff = new TextView(this);
            tvDiff.setText(task.getDifficulty());
            tvDiff.setTextSize(11);
            tvDiff.setTypeface(null, android.graphics.Typeface.BOLD);
            tvDiff.setBackgroundResource(R.drawable.bg_badge_block);
            tvDiff.setTextColor(getResources().getColor(R.color.text_kk));
            tvDiff.setPadding(14, 4, 14, 4);

            topRow.addView(tvSubject);
            topRow.addView(tvDiff);

            // Judul Tugas
            TextView tvTitle = new TextView(this);
            tvTitle.setText(task.getTitle());
            tvTitle.setTextColor(getResources().getColor(R.color.text_primary));
            tvTitle.setTextSize(15);
            tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);
            tvTitle.setPadding(0, 8, 0, 4);

            // Deadline
            TextView tvDeadline = new TextView(this);
            tvDeadline.setText("Deadline: " + task.getDeadline());
            tvDeadline.setTextColor(getResources().getColor(R.color.danger_red));
            tvDeadline.setTextSize(13);
            tvDeadline.setPadding(0, 0, 0, 8);

            // Pembuat Tugas
            TextView tvAuthor = new TextView(this);
            tvAuthor.setText("Ditambahkan oleh: " + task.getAuthorName() + " (Absen " + task.getAuthorAbsent() + ")");
            tvAuthor.setTextColor(getResources().getColor(R.color.text_secondary));
            tvAuthor.setTextSize(11);

            card.addView(topRow);
            card.addView(tvTitle);
            card.addView(tvDeadline);
            card.addView(tvAuthor);

            containerTaskList.addView(card);
        }
    }
}