package com.example.xipplgb_infospace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.InfoItem;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

public class InfoDetailActivity extends AppCompatActivity {

    private TextView btnBackInfoDetail;
    private TextView tvInfoDetailDate;
    private TextView tvInfoDetailTitle;
    private TextView tvInfoDetailAuthor;
    private TextView tvInfoDetailContent;
    private Button btnDeleteInfoDetail;

    private SharedPrefHelper prefHelper;
    private String infoId;
    private InfoItem currentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        prefHelper = new SharedPrefHelper(this);

        btnBackInfoDetail = findViewById(R.id.btnBackInfoDetail);
        tvInfoDetailDate = findViewById(R.id.tvInfoDetailDate);
        tvInfoDetailTitle = findViewById(R.id.tvInfoDetailTitle);
        tvInfoDetailAuthor = findViewById(R.id.tvInfoDetailAuthor);
        tvInfoDetailContent = findViewById(R.id.tvInfoDetailContent);
        btnDeleteInfoDetail = findViewById(R.id.btnDeleteInfoDetail);

        infoId = getIntent().getStringExtra("INFO_ID");

        muatDetailInfo();

        if (btnBackInfoDetail != null) {
            btnBackInfoDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        if (btnDeleteInfoDetail != null) {
            btnDeleteInfoDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tampilkanDialogHapusInfo();
                }
            });
        }
    }

    private void muatDetailInfo() {
        if (infoId == null || infoId.isEmpty()) {
            Toast.makeText(this, "ID pengumuman tidak valid.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currentInfo = prefHelper.getInfoById(infoId);
        if (currentInfo == null) {
            Toast.makeText(this, "Pengumuman tidak ditemukan.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvInfoDetailDate.setText(currentInfo.getCreatedAt());
        tvInfoDetailTitle.setText(currentInfo.getTitle());
        tvInfoDetailAuthor.setText("Diumumkan oleh: " + currentInfo.getAuthorName() + " (Absen " + currentInfo.getAuthorAbsent() + ")");
        tvInfoDetailContent.setText(currentInfo.getContent());
    }

    private void tampilkanDialogHapusInfo() {
        if (currentInfo == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Pengumuman");
        builder.setMessage("Apakah Anda yakin ingin menghapus pengumuman \"" + currentInfo.getTitle() + "\"?");

        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prefHelper.deleteInfo(currentInfo.getId());
                Toast.makeText(InfoDetailActivity.this, "Pengumuman berhasil dihapus.", Toast.LENGTH_SHORT).show();
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
