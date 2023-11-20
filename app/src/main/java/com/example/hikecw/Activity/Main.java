package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hikecw.R;

public class Main extends AppCompatActivity {

    private Button btnAddHike;
    private Button btnPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddHike = findViewById(R.id.newHikeButton);
        btnAddHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(AddHike.class);
            }
        });

        btnPlan = findViewById(R.id.plannedHikesButton);
        btnPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(ListPlan.class);
            }
        });
    }
    public void changePage(Class<?>target){
        Intent i = new Intent(this, target);
        startActivity(i);
    }
}