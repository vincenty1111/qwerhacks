package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;

public class MainActivity extends AppCompatActivity {
    private DocumentReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DocumentReference reff;
        createNaviBar();

    }
    private void createNaviBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.botnav_home);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_home:
                        return true;
                    case R.id.botnav_trivia:
                        Intent intent = new Intent(getApplicationContext(), TriviaActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.botnav_profile:
                        Intent intent1 = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}