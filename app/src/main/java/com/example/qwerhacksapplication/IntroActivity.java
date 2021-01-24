package com.example.qwerhacksapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button triv = findViewById(R.id.trivia);
        triv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TriviaActivity.class);
                startActivity(intent);
            }
        });
        Button safe = findViewById(R.id.safespace);
        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForumActivity.class);
                startActivity(intent);
            }
        });
        Button hub = findViewById(R.id.QueerHub);
        hub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InspoHubActivity.class);
                startActivity(intent);
            }
        });
    }

}