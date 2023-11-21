package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hikecw.Database.HikeDB;
import com.example.hikecw.R;

public class AddObservation extends AppCompatActivity {

    EditText editTextObservation, editTextTime, editTextComment;

    Button saveObservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_observation);

        addObservation();
    }

    private int getHikeId() {
        Intent i = getIntent();
        return i.getIntExtra("hike_ref", 0);
    }

    private void addObservation() {
        editTextObservation = (EditText) findViewById(R.id.editTextObservation);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextComment = (EditText) findViewById(R.id.editTextComment);

        saveObservation = (Button) findViewById(R.id.saveObservation);


        saveObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HikeDB db = new HikeDB(AddObservation.this);
                try {
                    db.addObservation(
                            editTextObservation.getText().toString(),
                            editTextTime.getText().toString(),
                            editTextComment.getText().toString(),
                            getHikeId()
                    );
                    finish();
                } catch (Exception e) {
                    Toast.makeText(AddObservation.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}