package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hikecw.Database.HikeDB;
import com.example.hikecw.Module.HikeMD;
import com.example.hikecw.MyAdapter.HikeAdapter;
import com.example.hikecw.R;

import java.util.ArrayList;

public class ListPlan extends AppCompatActivity {

    RecyclerView listRV;

    ArrayList<HikeMD> MD;

    HikeAdapter hAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_plan);

        fetchHikeData();
    }

    private void fetchHikeData() {
        listRV = findViewById(R.id.rv);
        HikeDB db = new HikeDB(this);
        MD = db.fetchHData();
        hAdapter = new HikeAdapter(this,MD);
        listRV.setAdapter(hAdapter);
        listRV.setLayoutManager( new LinearLayoutManager(this));
    }


}