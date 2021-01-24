package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TriviaActivity extends AppCompatActivity {
    private DocumentReference reff;
    final public static String counter_var = "NUM_QUESTIONS_ATTEMPTED";
    final public static String input = "INPUT";
    int choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final int counter = intent.getIntExtra(counter_var, 0);


        reff = FirebaseFirestore.getInstance().collection("Questions").document("" + counter);
        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Map<String, Object> info = documentSnapshot.getData();
                    String test = (String) info.get("question");
                    TextView quest = findViewById(R.id.quest1);
                    quest.setText(test);
                    System.out.println(test);
                    System.out.println(choice);
                    ArrayList arr = (ArrayList) info.get("choices");
                    Button butt1 = findViewById(R.id.Ans1);
                    Button butt2 = findViewById(R.id.Ans2);
                    Button butt3 = findViewById(R.id.Ans3);
                    Button butt4 = findViewById(R.id.Ans4);
                    butt1.setText((String) arr.get(0));
                    butt2.setText((String) arr.get(1));
                    butt3.setText((String) arr.get(2));
                    butt4.setText((String) arr.get(3));
                }
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        createNaviBar();
        Button ans1 = (Button) findViewById(R.id.Ans1);
        Button ans2 = (Button) findViewById(R.id.Ans2);
        Button ans3 = (Button) findViewById(R.id.Ans3);
        Button ans4 = (Button) findViewById(R.id.Ans4);

        ans1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choice = 0;
            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choice = 1;
            }
        });
        ans3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choice = 2;
            }
        });
        ans4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                choice = 3;
            }
        });
        System.out.println(choice);
        Button button = (Button) findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TriviaActivity.this, ResultActivity.class);
                intent.putExtra(counter_var, counter + 1);
                intent.putExtra(input, choice);
                startActivity(intent);
            }
            });

        }
//        createQuestion();
//        createAnswer();

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
//    private void createQuestion() {
//        String question = "ygygygy";
//        TextView quest = findViewById(R.id.quest1);
//        quest.setText(question);
//
//    }

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