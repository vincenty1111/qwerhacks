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
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class ForumResults extends AppCompatActivity {
    private DocumentReference reff;
    private PostRecyclerViewAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_results);

        Intent intent = getIntent();
        createNaviBar();
        final String query = intent.getStringExtra("QUERY");
        TextView searchTitle = findViewById(R.id.results_title);
        searchTitle.setText("Search Results for "+ query);
        SearchView searchbar = findViewById(R.id.results_searchbar);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent (ForumResults.this, ForumResults.class);
                intent.putExtra("QUERY", s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        ImageView suppsun = findViewById(R.id.suppsun_tag);
        suppsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForumResults.this, ForumResults.class);
                intent.putExtra("QUERY", "suppsun");
                startActivity(intent);
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
                    for (String key: data.keySet()) {
                        Map<String, Object> post_info = (Map<String, Object>) data.get(key);
                        ArrayList<String> tags = (ArrayList<String>) post_info.get("tags");
                        for (int i = 0; i < tags.size(); i++) {
                            if (tags.get(i).contains(query)) {
                                questions.add(key);
                                answers.add((String) post_info.get("topanswer"));
                                numAns.add((String) post_info.get("answers"));
                                views.add((String) post_info.get("views"));
                                break;
                            }
                        }
                    }
                    listener = new PostRecyclerViewAdapter.RecyclerViewClickListener() {
                        @Override
                        public void onClick(View v, int pos) {
                        }
                    };
                    PostRecyclerViewAdapter adapter = new PostRecyclerViewAdapter(ForumResults.this, questions, answers, numAns, views, listener);
                    RecyclerView post = findViewById(R.id.results_recycler);
                    post.setLayoutManager(new LinearLayoutManager(ForumResults.this, LinearLayoutManager.VERTICAL,
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
                        Intent intent2 = new Intent(getApplicationContext(), ForumActivity.class);
                        startActivity(intent2);
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