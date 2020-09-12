package com.example.jaydave_sanchitshah_comp304_lab_assign4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new BaseFragment()).commit();


    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            SharedPreferences.Editor prefs = getApplicationContext().getSharedPreferences(LoginActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
            //prefs.remove("isLoggedIn");
            prefs.remove("login");
            prefs.apply();
            prefs.commit();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_patient:
                    selectedFragment = new PatientFragment();
                    break;
                case R.id.nav_test:
                    selectedFragment = new TestFragment();
                    break;
                case R.id.nav_home:
                    selectedFragment = new BaseFragment();
                    break;
                case R.id.nav_logout:
                    SharedPreferences.Editor prefs = getApplicationContext().getSharedPreferences(LoginActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    prefs.remove("isLoggedIn");
                    prefs.remove("login");
                    prefs.apply();
                    prefs.commit();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };
}