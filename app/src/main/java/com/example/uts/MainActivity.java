package com.example.uts;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            fragment = new HomeFragment();
        } else if (itemId == R.id.berita) {
            fragment = new BeritaFragment();
        } else if (itemId == R.id.profile) {
            fragment = new ProfileFragment();
        } else {
            return false;
        }

        loadFragment(fragment);
        return true;
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_corner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_tambah_data) {
            // Ketika menu "Tambah Data" diklik, tampilkan TambahDataFragment ke TambahDatFragment
            TambahDataFragment tambahDataFragment = new TambahDataFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.tambahdata, tambahDataFragment)
                    .addToBackStack(null) // Ini akan menambahkan fragmen ke tumpukan kembali, sehingga dapat dikembalikan dengan tombol kembali
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}