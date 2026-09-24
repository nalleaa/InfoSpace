package com.example.xipplgb_infospace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xipplgb_infospace.data.StudentData;
import com.example.xipplgb_infospace.model.Student;
import com.example.xipplgb_infospace.utils.SharedPrefHelper;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText etAbsen;
    private EditText etDisplayName;
    private Button btnLogin;
    private SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi SharedPreferences
        prefHelper = new SharedPrefHelper(this);

        // AUTO LOGIN CHECK: Jika user sudah login sebelumnya, langsung buka HomeActivity
        if (prefHelper.isLoggedIn()) {
            pindahKeHome();
            return;
        }

        setContentView(R.layout.activity_login);

        etAbsen = findViewById(R.id.etAbsen);
        etDisplayName = findViewById(R.id.etDisplayName);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiDanLogin();
            }
        });
    }

    private void validasiDanLogin() {
        String inputAbsenStr = etAbsen.getText().toString().trim();
        String inputDisplayName = etDisplayName.getText().toString().trim();

        if (inputAbsenStr.isEmpty()) {
            etAbsen.setError("Nomor absen wajib diisi.");
            etAbsen.requestFocus();
            return;
        }

        int inputAbsen;
        try {
            inputAbsen = Integer.parseInt(inputAbsenStr);
        } catch (NumberFormatException e) {
            etAbsen.setError("Nomor absen harus berupa angka.");
            etAbsen.requestFocus();
            return;
        }

        // Cari siswa berdasarkan nomor absen sebagai kunci utama
        ArrayList<Student> students = StudentData.getStudents();
        Student matchedStudent = null;

        for (Student student : students) {
            if (student.getAbsentNumber() == inputAbsen) {
                matchedStudent = student;
                break;
            }
        }

        if (matchedStudent != null) {
            String officialName = matchedStudent.getName();
            int officialAbsent = matchedStudent.getAbsentNumber();

            // Jika Nama Tampilan diisi pengguna, gunakan itu. Jika kosong, gunakan nama panggilan/resmi.
            String finalDisplayName;
            if (!inputDisplayName.isEmpty()) {
                finalDisplayName = inputDisplayName;
            } else {
                // Ambil kata pertama dari nama resmi (misal: FELISIANNA OLIVE DRISANA -> FELISIANNA)
                String[] parts = officialName.split(" ");
                finalDisplayName = parts[0];
            }

            // Simpan sesi ke SharedPreferences lokal
            prefHelper.saveLoginSession(officialName, officialAbsent, finalDisplayName);
            Toast.makeText(this, "Selamat datang, " + finalDisplayName + "!", Toast.LENGTH_SHORT).show();

            pindahKeHome();
        } else {
            Toast.makeText(this, "Nomor absen " + inputAbsen + " tidak terdaftar di kelas XI PPLG B.", Toast.LENGTH_LONG).show();
        }
    }

    private void pindahKeHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
