package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TriviaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        createNaviBar();
        createQuestion();
        createAnswer();
    }
    private void createNaviBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.botnav_trivia);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_trivia:
                        return true;
                    case R.id.botnav_forum:
                        Intent intent1 = new Intent(getApplicationContext(), ForumActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.botnav_inspiro:
                        Intent intent = new Intent(getApplicationContext(), InspoHubActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
    private void createQuestion() {
        String question = "ygygygy";
        TextView quest = findViewById(R.id.quest1);
        quest.setText(question);

    }

    private void createAnswer() {
        String Ans1 = "A";
        String Ans2 = "B";
        String Ans3 = "C";
        String Ans4 = "D";

        Button butt1 = findViewById(R.id.Ans1);
        Button butt2 = findViewById(R.id.Ans2);
        Button butt3 = findViewById(R.id.Ans3);
        Button butt4 = findViewById(R.id.Ans4);
        butt1.setText(Ans1);
        butt2.setText(Ans2);
        butt3.setText(Ans3);
        butt4.setText(Ans4);
    }

}