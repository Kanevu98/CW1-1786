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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hike);
        insertData();
    }

    EditText ip_name, ip_location, ip_date, ip_length, ip_level, ip_description, ip_weatherF;
    RadioGroup ip_parking,ip_rate;
    Button btnSave;
    HikeDB DB;

    private void insertData(){

        ip_name =(EditText) findViewById(R.id.editTextHikeName);
        ip_location =(EditText) findViewById(R.id.editTextLocation);
        ip_date =(EditText) findViewById(R.id.editTextDate);
        ip_length =(EditText) findViewById(R.id.editTextLength);
        ip_level =(EditText) findViewById(R.id.editTextLevel);
        ip_description =(EditText) findViewById(R.id.editTextDescription);
        ip_parking =(RadioGroup) findViewById(R.id.radioGroupParking);
        ip_rate =(RadioGroup) findViewById(R.id.radioGroupRating);
        ip_weatherF =(EditText) findViewById(R.id.editTextWeather);

        final String value = ((RadioButton)findViewById(ip_parking.getCheckedRadioButtonId())).getText().toString();
        int parkingR;
        if(value == "Parking: Yes"){
            parkingR = 1;
        }else{
            parkingR = 0;
        }

        final String Rvalue = ((RadioButton)findViewById(ip_rate.getCheckedRadioButtonId())).getText().toString();
        int ratingV = 0;
        if (Rvalue == "1s"){
            ratingV = 1;
        } else if (Rvalue == "2s") {
            ratingV = 2;
        } else if (Rvalue == "3s") {
            ratingV = 3;
        } else if (Rvalue == "4s") {
            ratingV = 4;
        } else if (Rvalue == "5s") {
            ratingV = 5;
        }

        btnSave = (Button) findViewById(R.id.buttonSaveHike);
        int finalRatingValue = ratingV;
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DB = new HikeDB(AddHike.this);
                    DB.addHike(
                            ip_name.getText().toString().trim(),
                            ip_location.getText().toString().trim(),
                            ip_date.getText().toString().trim(),
                            parkingR,
                            Float.parseFloat(ip_length.getText().toString().trim()),
                            ip_level.getText().toString().trim(),
                            ip_description.getText().toString().trim(),
                            finalRatingValue,
                            ip_weatherF.getText().toString().trim()
                    );
                    finish();
                }catch (Exception e){
                    Log.w("myApp", e.toString());
                }
            }
        });

    }

}