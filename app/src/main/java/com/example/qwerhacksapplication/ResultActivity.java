package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.qwerhacksapplication.TriviaActivity.counter_var;
import static com.example.qwerhacksapplication.TriviaActivity.input;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final int counter = intent.getIntExtra(counter_var, 0);
        setContentView(R.layout.activity_result);
        createNaviBar();

        final int given = intent.getIntExtra(input, 0);
        final Long true_ans = intent.getLongExtra("true_answer", 0);
        System.out.println(true_ans);
        if (given == true_ans) {
            TextView corr = ResultActivity.this.findViewById(R.id.right);
            corr.setVisibility(View.VISIBLE);
        }
        else {
            TextView corr = ResultActivity.this.findViewById(R.id.wrong);
            corr.setVisibility(View.VISIBLE);
        }
        Button button = (Button) findViewById(R.id.congrats);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (counter == 3) {
                    Intent final_intent = new Intent(ResultActivity.this, EndActivity.class);
                    startActivity(final_intent);
                    return;
                }
                Intent intent = new Intent(ResultActivity.this, TriviaActivity.class);
                intent.putExtra(counter_var, counter);
                startActivity(intent);
            }
        });
    }
    private void createNaviBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.botnav_trivia);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_trivia:
                        Intent intent0 = new Intent(getApplicationContext(), TriviaActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0, 0);
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
                    case R.id.botnav_profile:
                        Intent intent3 = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}