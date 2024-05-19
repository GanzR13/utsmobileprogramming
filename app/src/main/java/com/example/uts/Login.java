package com.example.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    // Nama file SharedPreferences
    private static final String PREFS_NAME = "UserData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Menginisialisasi view
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword.setTransformationMethod(new PasswordTransformationMethod()); // Mengubah tampilan password menjadi bulatan
        Button buttonLogin = findViewById(R.id.buttonlogin);

        // Menambahkan OnClickListener untuk button login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan nilai email dan password dari EditText
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Memeriksa apakah email dan password sesuai dengan yang di-hardcode
                if (email.equals("rizki123") && password.equals("rizki123")) {
                    // Jika sesuai, menyimpan data pengguna ke SharedPreferences
                    saveUserData(Login.this, "rizkimubin13@gmail.com", "Rizki Awaluddin", "2207411049", "TI 4B");

                    // Jika sesuai, pindah ke MainActivity
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    // Mengakhiri LoginActivity setelah pindah ke MainActivity
                    finish();
                } else {
                    // Jika tidak sesuai, tampilkan pesan kesalahan
                    Toast.makeText(Login.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Metode untuk menyimpan data pengguna ke SharedPreferences
    private void saveUserData(Context context, String email, String nama, String nim, String kelas) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Menggabungkan data pengguna menjadi satu string dengan delimiter tertentu (contoh: tanda koma)
        String userData = email + "," + nama + "," + nim + "," + kelas;
        // Menyimpan data pengguna ke SharedPreferences
        editor.putString("userData", userData);
        editor.apply();
    }
}
