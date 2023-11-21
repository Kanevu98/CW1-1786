package com.example.hikecw.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.hikecw.Model.HikeModel;
import com.example.hikecw.Model.ObservationModel;

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
    private static final String OBSERVATION_ID = "Observation_ID";
    private static final String COLUMN_OBSERVATION_NAME = "Observation";
    private static final String COLUMN_TIME = "Observation_Time";
    private static final String COLUMN_COMMENT = "Observationomment";
    private static final String COLUMN_Hike_REF = "Hike_REF";



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
            + OBSERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_OBSERVATION_NAME + " TEXT, "
            + COLUMN_TIME + " TEXT, "
            + COLUMN_COMMENT + " TEXT, "
            + COLUMN_Hike_REF + " INTEGER, "
            +" FOREIGN KEY (Hike_REF) REFERENCES Hike_table(HikeID));";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(hikeQuery);
        db.execSQL(Observations);
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

        long result = db.insert(HIKE_TABLE, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public void addObservation(String name, String time, String comment, int hike_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_OBSERVATION_NAME, name);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_COMMENT, comment);
        cv.put(COLUMN_Hike_REF, hike_id);

        long result = db.insert(OBSERVATION_TABLE, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<HikeModel> getHikeData() {
        ArrayList<HikeModel> t = new ArrayList<>();

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

                HikeModel h = new HikeModel(id, name, location, date, length, level, description, parking, rate, weather);
                t.add(h);

            }while (c.moveToNext());
        }
        return t;
    }
    public ArrayList<ObservationModel> getObservationData(int hikeRef) {
        ArrayList<ObservationModel> o = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + OBSERVATION_TABLE + " WHERE " + COLUMN_Hike_REF + " = " + hikeRef , null);

        if(c.moveToNext()) {
            do {
                int Observation_ID = c.getInt(0);
                String Observation = c.getString(1);
                String Observation_Time = c.getString(2);
                String Observationomment = c.getString(3);
                int Hike_REF = c.getInt(4);

                ObservationModel ob = new ObservationModel(Observation_ID, Observation, Observation_Time, Observationomment, Hike_REF);
                o.add(ob);
            } while (c.moveToNext());
        }
        return o;
    }

    public void deleteHike(int hikeID){
        SQLiteDatabase sqLiteDatabase =getReadableDatabase();
        sqLiteDatabase.delete(HIKE_TABLE, "HikeID = " + hikeID,null );
    }

    public void updateHike(int id, String name, String location, String date, int parking, Float length, String level, String description, int rate, String weather) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_PARKING, parking);
        contentValues.put(COLUMN_LENGTH, length);
        contentValues.put(COLUMN_LEVEL, level);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_SCENERY_RATING, rate);
        contentValues.put(COLUMN_WEATHER, weather);

        long result = db.update(HIKE_TABLE, contentValues, "HikeID = " + id , null);
        if(result == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Update Success", Toast.LENGTH_LONG).show();
        }


    }


}
