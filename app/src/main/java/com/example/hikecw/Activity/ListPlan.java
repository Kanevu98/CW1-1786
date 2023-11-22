package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.hikecw.Database.HikeDB;
import com.example.hikecw.Model.HikeModel;
import com.example.hikecw.MyAdapter.HikeAdapter;
import com.example.hikecw.R;

import java.util.ArrayList;

public class ListPlan extends AppCompatActivity {

    RecyclerView listRV;

    ArrayList<HikeModel> MD;
    HikeAdapter hAdapter;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_plan);

        fetchHikeData();
        search();
    }

    private void fetchHikeData() {
        listRV = findViewById(R.id.rv);
        HikeDB db = new HikeDB(this);
        MD = db.getHikeData();
        hAdapter = new HikeAdapter(this,MD, MD);
        listRV.setAdapter(hAdapter);
        listRV.setLayoutManager( new LinearLayoutManager(this));
    }

    public void search() {
        search = findViewById(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setMaxWidth(Integer.MAX_VALUE);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    public void addHike_click(View view) {
        Intent i = new Intent(ListPlan.this, AddHike.class);
        startActivity(i);
    }


}