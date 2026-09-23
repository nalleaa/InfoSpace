package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        for (InfoItem item : list) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setBackgroundResource(R.drawable.bg_card);
            card.setElevation(2f);
            card.setPadding(28, 24, 28, 24);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 16);
            card.setLayoutParams(params);

            TextView tvDate = new TextView(this);
            tvDate.setText(item.getCreatedAt());
            tvDate.setTextColor(getResources().getColor(R.color.text_muted));
            tvDate.setTextSize(11);

            TextView tvTitle = new TextView(this);
            tvTitle.setText(item.getTitle());
            tvTitle.setTextColor(getResources().getColor(R.color.text_primary));
            tvTitle.setTextSize(16);
            tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);
            tvTitle.setPadding(0, 4, 0, 8);

            TextView tvContent = new TextView(this);
            tvContent.setText(item.getContent());
            tvContent.setTextColor(getResources().getColor(R.color.text_secondary));
            tvContent.setTextSize(13);
            tvContent.setLineSpacing(4f, 1f);
            tvContent.setPadding(0, 0, 0, 12);

            TextView tvAuthor = new TextView(this);
            tvAuthor.setText("Diumumkan oleh: " + item.getAuthorName() + " (Absen " + item.getAuthorAbsent() + ")");
            tvAuthor.setTextColor(getResources().getColor(R.color.primary_indigo));
            tvAuthor.setTextSize(11);
            tvAuthor.setTypeface(null, android.graphics.Typeface.BOLD);

            card.addView(tvDate);
            card.addView(tvTitle);
            card.addView(tvContent);
            card.addView(tvAuthor);

            containerInfoList.addView(card);
        }
    }
}