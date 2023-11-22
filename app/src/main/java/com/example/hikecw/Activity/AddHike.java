package com.example.hikecw.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.hikecw.Database.HikeDB;
import com.example.hikecw.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddHike extends AppCompatActivity {

    EditText ip_name, ip_location, ip_date, ip_length, ip_level, ip_description, ip_weatherF;
    RadioGroup parkingGroup, radioGroupRating;
    Button btnSave;
    HikeDB DB;
    int parking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hike);
        insertData();
    }

    private void insertData(){

        btnSave = (Button) findViewById(R.id.buttonSaveHike);

        ip_name = findViewById(R.id.editTextHikeName);
        ip_location =(EditText) findViewById(R.id.editTextLocation);
        ip_date =(EditText) findViewById(R.id.editTextDate);
        ip_length =(EditText) findViewById(R.id.editTextLength);
        ip_level =(EditText) findViewById(R.id.editTextLevel);
        ip_description =(EditText) findViewById(R.id.editTextDescription);
        ip_weatherF =(EditText) findViewById(R.id.editTextWeather);

        int ratingV = 0;


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ip_name.getText().toString().length() == 0 ||
                        ip_location.getText().toString().length() == 0 ||
                        ip_date.getText().toString().length() == 0 ||
                        ip_length.getText().toString().length() == 0 ||
                        ip_level.getText().toString().length() == 0) {
                    Toast.makeText(AddHike.this, "Fill all information", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        DB = new HikeDB(AddHike.this);
                        DB.addHike(
                                ip_name.getText().toString().trim(),
                                ip_location.getText().toString().trim(),
                                ip_date.getText().toString().trim(),
                                getParking(),
                                Float.parseFloat(ip_length.getText().toString().trim()),
                                ip_level.getText().toString().trim(),
                                ip_description.getText().toString().trim(),
                                getRating(),
                                ip_weatherF.getText().toString().trim()
                        );
                        finish();
                    }catch (Exception e){
                        Toast.makeText(AddHike.this, e.toString(), Toast.LENGTH_LONG);
                    }
                }
            }
        });

    }

    private int getParking() {
        parkingGroup = (RadioGroup) findViewById(R.id.parkingGroup);

        int parkingButtonID = parkingGroup.getCheckedRadioButtonId();
        Button parkingButton = parkingGroup.findViewById(parkingButtonID);
        int idx = parkingGroup.indexOfChild(parkingButton);
        RadioButton p = (RadioButton) parkingGroup.getChildAt(idx);
        String selectedText = p.getText().toString();
//        final String value = ((RadioButton)findViewById(parkingGroup.getCheckedRadioButtonId()))
//                .getText().toString();
        if(selectedText.equals("Parking: Yes")) {
            return  1;
        } else {
            return 0;
        }
    }

    private int getRating() {
        radioGroupRating = (RadioGroup) findViewById(R.id.radioGroupRating);

        int ratingButtonID = radioGroupRating.getCheckedRadioButtonId();
        Button ratingButton = radioGroupRating.findViewById(ratingButtonID);
        int idx = radioGroupRating.indexOfChild(ratingButton);
        RadioButton r = (RadioButton) radioGroupRating.getChildAt(idx);
        String ratingText = r.getText().toString();

        if(ratingText.equals("1s")) {
            return  1;
        }else if(ratingText.equals("2s")) {
            return  2;
        } else if(ratingText.equals("3s")) {
            return  3;
        } else if(ratingText.equals("4s")) {
            return  4;
        } else {
            return 5;
        }
    }

}