package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        createNaviBar();
    }
    private void createNaviBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.botnav_profile);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_trivia:
                        Intent intent = new Intent(getApplicationContext(), TriviaActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.botnav_forum:
                        Intent intent1 = new Intent(getApplicationContext(), ForumActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.botnav_inspiro:
                        Intent intent3 = new Intent(getApplicationContext(), InspoHubActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.botnav_profile:
                        return true;
                }
                return false;
            }
        });
    }
}