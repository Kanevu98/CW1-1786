package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hikecw.Database.HikeDB;
import com.example.hikecw.R;

public class EditHike extends AppCompatActivity {

    EditText HikeName_edit, Location_edit, TextDate_edit, Length_edit, Level_edit, Description_edit, Weather_edit;

    RadioGroup radioGroupParking_edit, radioGroupRating_edit;
    RadioButton parkingBtn_edit, ratingBtn_edit, Yesbtn, Nobtn;
    Button buttonUpdateHike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hike);

        updateHike();
    }

    private void updateHike() {
        HikeName_edit = findViewById(R.id.HikeName_edit);
        Location_edit = findViewById(R.id.Location_edit);
        TextDate_edit = findViewById(R.id.TextDate_edit);
        Length_edit = findViewById(R.id.Length_edit);
        Level_edit = findViewById(R.id.Level_edit);
        Description_edit = findViewById(R.id.Description_edit);
        radioGroupParking_edit = findViewById(R.id.radioGroupParking_edit);
        radioGroupRating_edit = findViewById(R.id.radioGroupRating_edit);
        Weather_edit= findViewById(R.id.Weather_edit);

        Yesbtn = findViewById(R.id.Yesbtn);
        Nobtn = findViewById(R.id.Nobtn);
        buttonUpdateHike = findViewById(R.id.buttonUpdateHike);

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        HikeName_edit.setText(i.getStringExtra("name"));
        Location_edit.setText(i.getStringExtra("location"));
        TextDate_edit.setText(i.getStringExtra("date"));
        Length_edit.setText(String.valueOf(i.getFloatExtra("length",0.0f)));
        Level_edit.setText(i.getStringExtra("level"));
        Description_edit.setText(i.getStringExtra("description"));
        Weather_edit.setText(i.getStringExtra("weather"));

        if(i.getIntExtra("parking",0) == 1) {
            Yesbtn.setChecked(true);
        } else {
            Nobtn.setChecked(false);
        }

        int parkingId;
        int parking = radioGroupParking_edit.getCheckedRadioButtonId();
        parkingBtn_edit = findViewById(parking);
        String btnName = parkingBtn_edit.getText().toString();
        if(btnName == "Yes") {
            parkingId = 1;
        } else {
            parkingId = 0;
        }

        int ratingId;
        int rating = radioGroupRating_edit.getCheckedRadioButtonId();
        ratingBtn_edit = findViewById(rating);
        String ratingName = parkingBtn_edit.getText().toString();
        switch (ratingName) {
            case "1s":
                ratingId = 1;
                break;
            case "2s":
                ratingId = 2;
                break;
            case "3s":
                ratingId = 3;
                break;
            case "4s":
                ratingId = 4;
                break;
            case "5s":
                ratingId = 5;
                break;
            default:
                ratingId = 0;
                break;
        }


        buttonUpdateHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HikeDB db = new HikeDB(EditHike.this);
                try {
                    db.updateHike(
                            id,
                            HikeName_edit.getText().toString().trim(),
                            Location_edit.getText().toString().trim(),
                            TextDate_edit.getText().toString().trim(),
                            parkingId,
                            Float.parseFloat(Length_edit.getText().toString().trim()),
                            Level_edit.getText().toString().trim(),
                            Description_edit.getText().toString().trim(),
                            ratingId,
                            Weather_edit.getText().toString().trim()
                    );
                    finish();
                } catch (Exception e){

                }
            }
        });
    }
}