package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.hikecw.R;


public class ViewDetail extends AppCompatActivity {

    TextView txvName,txvLocation, txvDate, txvLength,txvLevel, txvDescription, txvParking, txvRate, txvWeather;

    Button addObservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        getData();
    }

    public void getData(){
        Intent i = getIntent();
        txvName = findViewById(R.id.dataTitle);
        txvLocation = findViewById(R.id.dataLocation);
        txvDate = findViewById(R.id.dataDate);
        txvLength = findViewById(R.id.dataLength);
        txvLevel = findViewById(R.id.dataLevel);
        txvDescription = findViewById(R.id.dataDescription);
        txvParking = findViewById(R.id.dataParking);
        txvRate = findViewById(R.id.dataRate);
        txvWeather= findViewById(R.id.dataWeather);

        txvName.setText(i.getStringExtra("name"));
        txvLocation.setText(i.getStringExtra("location"));
        txvDate.setText(i.getStringExtra("date"));
        txvLength.setText(String.valueOf(i.getFloatExtra("length",0.0f)));
        txvLevel.setText(i.getStringExtra("level"));
        txvDescription.setText(i.getStringExtra("description"));
        txvParking.setText(String.valueOf(i.getIntExtra("parking",0)));
        txvRate.setText(String.valueOf(i.getIntExtra("rate", 0)));
        txvWeather.setText(i.getStringExtra("weather"));

    }

    private int getHikeId() {
        Intent i = getIntent();
        return i.getIntExtra("id", 0);
    }


    public void addObservation(View view) {
        Intent i = new Intent(ViewDetail.this, AddObservation.class);
        i.putExtra("hike_ref", getHikeId());
        startActivity(i);
    }

    public void ViewObservation_click(View view) {
        Intent i = new Intent(ViewDetail.this, ObservationDetails.class);
        i.putExtra("hike_ref", getHikeId());
        startActivity(i);
    }
}