package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.Map;

import static com.example.qwerhacksapplication.TriviaActivity.counter_var;
import static com.example.qwerhacksapplication.TriviaActivity.input;

public class ResultActivity extends AppCompatActivity {
    private DocumentReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final int counter = intent.getIntExtra(counter_var, 0);
        setContentView(R.layout.activity_result);
        createNaviBar();
        reff = FirebaseFirestore.getInstance().collection("Questions").document("" + (counter-1));
        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Map<String, Object> info = documentSnapshot.getData();
                    String test = (String) info.get("question");
                    TextView quest = findViewById(R.id.quest1);

                    quest.setText(test);

                    System.out.println(test);
//                    System.out.println(choice);
                    ArrayList arr = (ArrayList) info.get("choices");
                    String fun = (String) info.get("funfact");
                    String perc = (String) info.get("percent");
                    Button butt1 = findViewById(R.id.Ans1);
                    Button butt2 = findViewById(R.id.Ans2);
                    Button butt3 = findViewById(R.id.Ans3);
                    Button butt4 = findViewById(R.id.Ans4);
                    Button qnum = findViewById(R.id.number);

                    TextView funfact = findViewById(R.id.funfact);
                    TextView percentage = findViewById(R.id.percent);
                    butt1.setText((String) arr.get(0));
                    butt2.setText((String) arr.get(1));
                    butt3.setText((String) arr.get(2));
                    butt4.setText((String) arr.get(3));
                    qnum.setText("Q" + counter + " of 10");

                    funfact.setText(fun.replace("\\n", "\n"));
                    percentage.setText(perc);
                }
            }
        });
        final int given = intent.getIntExtra(input, 0);
        final Long true_ans = intent.getLongExtra("true_answer", 0);
        System.out.println(true_ans);
        if (true_ans == 0) {
            Button butt1 = findViewById(R.id.Ans1);
            butt1.setBackgroundColor(Color.parseColor("#B1D7C2"));
        }
        if (true_ans == 1) {
            Button butt1 = findViewById(R.id.Ans2);
            butt1.setBackgroundColor(Color.parseColor("#B1D7C2"));
        }
        if (true_ans == 2) {
            Button butt1 = findViewById(R.id.Ans3);
            butt1.setBackgroundColor(Color.parseColor("#B1D7C2"));
        }
        if (true_ans == 3) {
            Button butt1 = findViewById(R.id.Ans4);
            butt1.setBackgroundColor(Color.parseColor("#B1D7C2"));
        }

        if (given != true_ans) {
            if (given == 0) {
                Button butt1 = findViewById(R.id.Ans1);
                butt1.setBackgroundColor(Color.parseColor("#E99B9E"));
            }
            if (given == 1) {
                Button butt1 = findViewById(R.id.Ans2);
                butt1.setBackgroundColor(Color.parseColor("#E99B9E"));
            }
            if (given == 2) {
                Button butt1 = findViewById(R.id.Ans3);
                butt1.setBackgroundColor(Color.parseColor("#E99B9E"));
            }
            if (given == 3) {
                Button butt1 = findViewById(R.id.Ans4);
                butt1.setBackgroundColor(Color.parseColor("#E99B9E"));
            }

        }

        Button button = (Button) findViewById(R.id.congrats);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (counter == 6) {
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