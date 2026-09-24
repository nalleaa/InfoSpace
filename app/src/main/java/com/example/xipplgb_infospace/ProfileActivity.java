package com.example.xipplgb_infospace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.utils.BlockHelper;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

public class ProfileActivity extends AppCompatActivity {

    private TextView btnBackProfile;
    private TextView tvProfileName;
    private TextView tvProfileClassAbsent;
    private TextView tvProfileOfficialName;
    private TextView tvProfileBlockStatus;
    private Button btnEditProfile;
    private Button btnLogoutProfile;
    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefHelper = new SharedPrefHelper(this);

        btnBackProfile = findViewById(R.id.btnBackProfile);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileClassAbsent = findViewById(R.id.tvProfileClassAbsent);
        tvProfileOfficialName = findViewById(R.id.tvProfileOfficialName);
        tvProfileBlockStatus = findViewById(R.id.tvProfileBlockStatus);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogoutProfile = findViewById(R.id.btnLogoutProfile);

        muatDataProfil();

        if (btnBackProfile != null) {
            btnBackProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            });
        }

        // Fitur Edit Profil (Mengubah Nama Tampilan)
        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tampilkanDialogEditProfil();
                }
            });
        }

        if (btnLogoutProfile != null) {
            btnLogoutProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prefHelper.logout();
                    Toast.makeText(ProfileActivity.this, "Berhasil keluar dari sesi.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void muatDataProfil() {
        String officialName = prefHelper.getStudentName();
        String displayName = prefHelper.getDisplayName();
        int absent = prefHelper.getStudentAbsent();

        tvProfileName.setText(displayName);
        tvProfileClassAbsent.setText("XI PPLG B | Absen " + absent);
        if (tvProfileOfficialName != null) {
            tvProfileOfficialName.setText(officialName);
        }

        BlockInfo blockInfo = BlockHelper.getCurrentBlockInfo();
        if (tvProfileBlockStatus != null) {
            tvProfileBlockStatus.setText(blockInfo.getFullStatusTitle().toUpperCase());
        }
    }

    private void tampilkanDialogEditProfil() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Nama Tampilan");

        final EditText etInput = new EditText(this);
        etInput.setHint("Masukkan nama panggilan / username");
        etInput.setText(prefHelper.getDisplayName());
        etInput.setSelection(etInput.getText().length());

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(50, 30, 50, 10);
        container.addView(etInput);

        builder.setView(container);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = etInput.getText().toString().trim();
                if (newName.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Nama tampilan tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                    return;
                }
                prefHelper.saveDisplayName(newName);
                muatDataProfil();
                Toast.makeText(ProfileActivity.this, "Nama tampilan berhasil diperbarui!", Toast.LENGTH_SHORT).show();
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
