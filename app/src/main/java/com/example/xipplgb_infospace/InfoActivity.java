package com.example.xipplgb_infospace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.InfoItem;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    private TextView btnBackInfo;
    private Button btnGoAddInfo;
    private TextView tvEmptyInfoMessage;
    private LinearLayout containerInfoList;
    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        prefHelper = new SharedPrefHelper(this);

        btnBackInfo = findViewById(R.id.btnBackInfo);
        btnGoAddInfo = findViewById(R.id.btnGoAddInfo);
        tvEmptyInfoMessage = findViewById(R.id.tvEmptyInfoMessage);
        containerInfoList = findViewById(R.id.containerInfoList);

        if (btnBackInfo != null) {
            btnBackInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        btnGoAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, AddInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        muatDaftarPengumuman();
    }

    private void muatDaftarPengumuman() {
        containerInfoList.removeAllViews();
        ArrayList<InfoItem> list = prefHelper.getAllInfo();

        if (list.isEmpty()) {
            tvEmptyInfoMessage.setVisibility(View.VISIBLE);
            return;
        }

        tvEmptyInfoMessage.setVisibility(View.GONE);

        for (final InfoItem item : list) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);

            GradientDrawable cardBg = new GradientDrawable();
            cardBg.setColor(Color.WHITE);
            cardBg.setCornerRadius(24f);
            cardBg.setStroke(2, Color.parseColor("#CBD5E1"));
            card.setBackground(cardBg);
            card.setElevation(3f);
            card.setPadding(32, 24, 32, 24);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 20);
            card.setLayoutParams(params);

            TextView tvDate = new TextView(this);
            tvDate.setText(item.getCreatedAt());
            tvDate.setTextColor(Color.parseColor("#4F46E5"));
            tvDate.setTextSize(12);
            tvDate.setTypeface(null, Typeface.BOLD);

            TextView tvTitle = new TextView(this);
            tvTitle.setText(item.getTitle());
            tvTitle.setTextColor(Color.parseColor("#0F172A"));
            tvTitle.setTextSize(16);
            tvTitle.setTypeface(null, Typeface.BOLD);
            tvTitle.setPadding(0, 8, 0, 8);

            TextView tvContent = new TextView(this);
            tvContent.setText(item.getContent());
            tvContent.setTextColor(Color.parseColor("#475569"));
            tvContent.setTextSize(13);
            tvContent.setMaxLines(3);
            tvContent.setEllipsize(android.text.TextUtils.TruncateAt.END);
            tvContent.setPadding(0, 0, 0, 10);

            TextView tvAuthor = new TextView(this);
            tvAuthor.setText("Diumumkan oleh: " + item.getAuthorName() + " (Absen " + item.getAuthorAbsent() + ")");
            tvAuthor.setTextColor(Color.parseColor("#64748B"));
            tvAuthor.setTextSize(12);

            TextView tvHint = new TextView(this);
            tvHint.setText("Ketuk untuk baca selengkapnya | Tekan lama untuk hapus");
            tvHint.setTextColor(Color.parseColor("#94A3B8"));
            tvHint.setTextSize(11);
            tvHint.setPadding(0, 10, 0, 0);

            card.addView(tvDate);
            card.addView(tvTitle);
            card.addView(tvContent);
            card.addView(tvAuthor);
            card.addView(tvHint);

            // FITUR KLIK KARTU: Buka Detail Pengumuman
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InfoActivity.this, InfoDetailActivity.class);
                    intent.putExtra("INFO_ID", item.getId());
                    startActivity(intent);
                }
            });

            // FITUR LONG PRESS: Menghapus pengumuman
            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    tampilkanDialogHapusInfo(item);
                    return true;
                }
            });

            containerInfoList.addView(card);
        }
    }

    private void tampilkanDialogHapusInfo(final InfoItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Pengumuman");
        builder.setMessage("Apakah Anda yakin ingin menghapus pengumuman \"" + item.getTitle() + "\"?");

        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prefHelper.deleteInfo(item.getId());
                muatDaftarPengumuman();
                Toast.makeText(InfoActivity.this, "Pengumuman berhasil dihapus.", Toast.LENGTH_SHORT).show();
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
