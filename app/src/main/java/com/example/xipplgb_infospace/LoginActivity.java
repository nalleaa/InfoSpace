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

    private EditText etNama;
    private EditText etAbsen;
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

        etNama = findViewById(R.id.etNama);
        etAbsen = findViewById(R.id.etAbsen);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiDanLogin();
            }
        });
    }

    private void validasiDanLogin() {
        String inputNama = etNama.getText().toString().trim();
        String inputAbsenStr = etAbsen.getText().toString().trim();

        if (inputNama.isEmpty()) {
            etNama.setError("Nama belum diisi.");
            etNama.requestFocus();
            return;
        }

        if (inputAbsenStr.isEmpty()) {
            etAbsen.setError("Nomor absen belum diisi.");
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

        ArrayList<Student> students = StudentData.getStudents();
        boolean isValid = false;
        String officialName = "";
        int officialAbsent = 0;

        for (Student student : students) {
            if (student.getAbsentNumber() == inputAbsen &&
                    student.getName().equalsIgnoreCase(inputNama)) {
                isValid = true;
                officialName = student.getName();
                officialAbsent = student.getAbsentNumber();
                break;
            }
        }

        if (isValid) {
            // Simpan sesi ke SharedPreferences lokal
            prefHelper.saveLoginSession(officialName, officialAbsent);
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();

            pindahKeHome();
        } else {
            Toast.makeText(this, "Nama atau nomor absen tidak sesuai dengan data XI PPLG B.", Toast.LENGTH_LONG).show();
        }
    }

    private void pindahKeHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Tutup LoginActivity agar saat tombol back ditekan tidak kembali ke login
    }
}