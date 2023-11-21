package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hikecw.Database.HikeDB;
import com.example.hikecw.Model.ObservationModel;
import com.example.hikecw.MyAdapter.ObservationAdapter;
import com.example.hikecw.R;

import java.util.ArrayList;

public class ObservationDetails extends AppCompatActivity {
    RecyclerView observationRV;
    ObservationAdapter adapter;

    ArrayList<ObservationModel> ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_details);

        getObservationData();
    }

    private void getObservationData() {
        observationRV = findViewById(R.id.observationRV);
        HikeDB db = new HikeDB(ObservationDetails.this);
        ob = db.getObservationData(getHikeId());
        adapter = new ObservationAdapter(ob, this);
        observationRV.setAdapter(adapter);
        observationRV.setLayoutManager(new LinearLayoutManager(this));
    }

    private int getHikeId() {
        Intent i = getIntent();
        return i.getIntExtra("hike_ref", 0);
    }
}