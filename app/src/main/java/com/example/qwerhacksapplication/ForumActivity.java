package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class ForumActivity extends AppCompatActivity {
    private DocumentReference reff;
    private PostRecyclerViewAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        createNaviBar();
        SearchView searchbar = findViewById(R.id.forum_searchbar);
        ImageView  suppsun = findViewById(R.id.suppsun_tag);
        suppsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForumActivity.this, ForumResults.class);
                intent.putExtra("QUERY", "suppsun");
                startActivity(intent);
            }
        });
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent (ForumActivity.this, ForumResults.class);
                intent.putExtra("QUERY", s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        reff = FirebaseFirestore.getInstance().collection("SafeSpace").document("0");
        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<String> questions = new ArrayList<>();
                    ArrayList<String> answers = new ArrayList<>();
                    ArrayList<String> views = new ArrayList<>();
                    ArrayList<String> numAns = new ArrayList<>();
                    Map<String, Object> data = documentSnapshot.getData();
                    int counter = 0;
                    for (String key: data.keySet()) {
                        if (counter > 2) {
                            break;
                        }
                        counter++;
                        questions.add(key);
                        Map<String, Object> postData = (Map<String, Object>) data.get(key);
                        answers.add((String) postData.get("topanswer"));
                        views.add((String)postData.get("views"));
                        numAns.add((String)postData.get("answers"));
                    }
                    listener = new PostRecyclerViewAdapter.RecyclerViewClickListener() {
                        @Override
                        public void onClick(View v, int pos) {
                        }
                    };
                    PostRecyclerViewAdapter adapter = new PostRecyclerViewAdapter(ForumActivity.this, questions, answers, numAns, views, listener);
                    RecyclerView post = findViewById(R.id.post_recycler);
                    post.setLayoutManager(new LinearLayoutManager(ForumActivity.this, LinearLayoutManager.VERTICAL,
                            false));
                    post.setAdapter(adapter);
                }
            }
        });

    }
    private void createNaviBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.botnav_forum);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_trivia:
                        Intent intent1 = new Intent(getApplicationContext(), TriviaActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.botnav_forum:
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