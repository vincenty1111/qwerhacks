package com.example.qwerhacksapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class InspoHubActivity extends AppCompatActivity {
    private RecyclerViewAdapter.RecyclerViewClickListener listener;
    private DocumentReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspo_hub);
        createNaviBar();
        createTVRecycler();
        createMusicRecycler();
        createNewsRecycler();
    }

    private void createTVRecycler() {
        reff = FirebaseFirestore.getInstance().collection("InspoHub").document("TV Shows");
        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    RecyclerView tvrecycler = findViewById(R.id.tvshow_recycler);
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> imageUrls = new ArrayList<>();
                    ArrayList<String> platforms = new ArrayList<>();
                    final ArrayList<String> links = new ArrayList<>();
                    Map<String, Object> data = documentSnapshot.getData();
                    for (String key: data.keySet()) {
                        titles.add(key);
                        Map<String, Object> show_info = (Map<String, Object>) data.get(key);
                        imageUrls.add((String) show_info.get("img"));
                        platforms.add((String) show_info.get("platform"));
                        links.add((String) show_info.get("link"));
                    }
                    System.out.println(links);
                    listener = new RecyclerViewAdapter.RecyclerViewClickListener() {
                        @Override
                        public void onClick(View v, int pos) {
                            String link = links.get(pos);
                            System.out.println(link);
                            goToUrl(link);
                        }
                    };
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(InspoHubActivity.this, titles, imageUrls, platforms, listener);
                    System.out.println("OK I AM HERE");
                    System.out.println("RecyclerView");
                    System.out.println(titles);
                    System.out.println(imageUrls);
                    System.out.println(platforms);
                    tvrecycler.setLayoutManager(new LinearLayoutManager(InspoHubActivity.this, LinearLayoutManager.HORIZONTAL, false));

                    tvrecycler.setAdapter(adapter);

                }
            }
        });

    }

    private void createMusicRecycler() {
        reff = FirebaseFirestore.getInstance().collection("InspoHub").document("Music");
        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    RecyclerView musicrecycler = findViewById(R.id.music_recycler);
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> imageUrls = new ArrayList<>();
                    ArrayList<String> platforms = new ArrayList<>();
                    final ArrayList<String> links = new ArrayList<>();
                    Map<String, Object> data = documentSnapshot.getData();
                    for (String key: data.keySet()) {
                        titles.add(key);
                        Map<String, Object> show_info = (Map<String, Object>) data.get(key);
                        imageUrls.add((String) show_info.get("img"));
                        platforms.add((String) show_info.get("platform"));
                        links.add((String) show_info.get("link"));
                    }
                    System.out.println(links);
                    listener = new RecyclerViewAdapter.RecyclerViewClickListener() {
                        @Override
                        public void onClick(View v, int pos) {
                            String link = links.get(pos);
                            System.out.println(link);
                            goToUrl(link);
                        }
                    };
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(InspoHubActivity.this, titles, imageUrls, platforms, listener);
                    System.out.println(titles);
                    System.out.println(imageUrls);
                    System.out.println(platforms);
                    musicrecycler.setLayoutManager(new LinearLayoutManager(InspoHubActivity.this, LinearLayoutManager.HORIZONTAL, false));

                    musicrecycler.setAdapter(adapter);

                }
            }
        });

    }
    private void createNewsRecycler() {
        reff = FirebaseFirestore.getInstance().collection("InspoHub").document("News");
        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    RecyclerView news_recycler = findViewById(R.id.news_recycler);
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> imageUrls = new ArrayList<>();
                    ArrayList<String> platforms = new ArrayList<>();
                    final ArrayList<String> links = new ArrayList<>();
                    Map<String, Object> data = documentSnapshot.getData();
                    for (String key: data.keySet()) {
                        titles.add(key);
                        Map<String, Object> show_info = (Map<String, Object>) data.get(key);
                        imageUrls.add((String) show_info.get("img"));
                        platforms.add((String) show_info.get("platform"));
                        links.add((String) show_info.get("link"));
                    }
                    System.out.println(links);
                    listener = new RecyclerViewAdapter.RecyclerViewClickListener() {
                        @Override
                        public void onClick(View v, int pos) {
                            String link = links.get(pos);
                            System.out.println(link);
                            goToUrl(link);
                        }
                    };
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(InspoHubActivity.this, titles, imageUrls, platforms, listener);
                    System.out.println(titles);
                    System.out.println(imageUrls);
                    System.out.println(platforms);
                    news_recycler.setLayoutManager(new LinearLayoutManager(InspoHubActivity.this, LinearLayoutManager.HORIZONTAL, false));

                    news_recycler.setAdapter(adapter);

                }
            }
        });

    }
    private void goToUrl (final String url) {
        System.out.println("URL is " + url);
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }



    private void createNaviBar() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.botnav_inspiro);
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