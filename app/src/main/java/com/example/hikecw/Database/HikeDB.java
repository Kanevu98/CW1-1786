package com.example.hikecw.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.hikecw.Module.HikeMD;

import java.util.ArrayList;

public class HikeDB extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASENAME = "Hike.db";
    private static final int DATABASE_VERSION = 1;
    private static final String HIKE_TABLE = "Hike_table";
    private static final String COLUMN_HIKE_ID = "HikeID";
    private static final String COLUMN_NAME = "Hike_Name";
    private static final String COLUMN_LOCATION = "Hike_Location";
    private static final String COLUMN_DATE = "Hike_Date";
    private static final String COLUMN_PARKING = "Parking_Available";
    private static final String COLUMN_LENGTH = "Hike_Length";
    private static final String COLUMN_LEVEL = "Hike_Level";
    private static final String COLUMN_DESCRIPTION = "Hike_Description";
    private static final String COLUMN_SCENERY_RATING = "Hike_Rate";
    private static final String COLUMN_WEATHER= "Hike_Weather";

    private static final String OBSERVATION_TABLE = "Observation_table";
    //    private static final String COLUMN_OBSERVATION_NAME = "Observation";
    private static final String COLUMN_TIME = "Observation_Time";
    private static final String COLUMN_COMMENT = "Observationomment";



    public HikeDB(@Nullable Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private String hikeQuery = "CREATE TABLE " + HIKE_TABLE + " ("
            + COLUMN_HIKE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_LOCATION + " TEXT, "
            + COLUMN_DATE + " TEXT, "
            + COLUMN_PARKING + " INTEGER, "
            + COLUMN_LENGTH + " FLOAT, "
            + COLUMN_LEVEL + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT, "
            + COLUMN_SCENERY_RATING + " INTEGER, "
            + COLUMN_WEATHER + " TEXT );";

    private String Observations = "CREATE TABLE " + OBSERVATION_TABLE +" ("
            + COLUMN_TIME + " TEXT, "
            + COLUMN_COMMENT + " TEXT, "
            +" FOREIGN KEY (Hike_Id) REFERENCES Hike_table(ID));";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(hikeQuery);
//        db.execSQL(Observations);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i0) {
        db.execSQL("DROP TABLE IF EXISTS " + HIKE_TABLE);
        onCreate(db);
    }
    public void addHike(String name, String location, String date, int parking, Float length, String level, String description, int rate, String weather){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PARKING, parking);
        cv.put(COLUMN_LENGTH, length);
        cv.put(COLUMN_LEVEL, level);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_SCENERY_RATING, rate);
        cv.put(COLUMN_WEATHER, weather);

        long result = db.insert(HIKE_TABLE, null,cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<HikeMD> fetchHData() {
        ArrayList<HikeMD> t = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + HIKE_TABLE + " ORDER BY " + COLUMN_HIKE_ID + " DESC", null);

        if (c.moveToFirst()){
            do{
                int id = c.getInt(0);
                String name = c.getString(1);
                String location = c.getString(2);
                String date = c.getString(3);
                int parking = c.getInt(4);
                float length = c.getFloat(5);
                String level = c.getString(6);
                String description = c.getString(7);
                int rate = c.getInt(8);
                String weather = c.getString(9);

                HikeMD trip = new HikeMD(id, name, location, date, length, level, description, parking, rate, weather);
                t.add(trip);

            }while (c.moveToNext());
        }
        return t;
    }
}
