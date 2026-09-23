package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xipplgb_infospace.model.BlockInfo;
import com.example.xipplgb_infospace.utils.BlockHelper;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

public class ProfileActivity extends AppCompatActivity {

    private TextView btnBackProfile;
    private TextView tvProfileName;
    private TextView tvProfileClassAbsent;
    private TextView tvProfileBlockStatus;
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
        tvProfileBlockStatus = findViewById(R.id.tvProfileBlockStatus);
        btnLogoutProfile = findViewById(R.id.btnLogoutProfile);

        String name = prefHelper.getStudentName();
        int absent = prefHelper.getStudentAbsent();

        tvProfileName.setText(name);
        tvProfileClassAbsent.setText("XI PPLG B | Absen " + absent);

        BlockInfo blockInfo = BlockHelper.getCurrentBlockInfo();
        tvProfileBlockStatus.setText(blockInfo.getFullStatusTitle().toUpperCase());

        if (btnBackProfile != null) {
            btnBackProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
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
}
