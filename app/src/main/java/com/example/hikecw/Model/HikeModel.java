package com.example.hikecw.Model;

public class HikeModel {
    private  int id;
    private  String hikeName;
    private  String location;
    private  String date;
    private  Float length;
    private  String level;
    private  String description;
    private  int parking;
    private int rate;
    private String weather;

    public HikeModel(int id, String hikeName, String location, String date, Float length, String level, String description, int parking, int rate, String weather) {
        this.id = id;
        this.hikeName = hikeName;
        this.location = location;
        this.date = date;
        this.length = length;
        this.level = level;
        this.description = description;
        this.parking = parking;
        this.rate = rate;
        this.weather = weather;
    }


    public int getId() {
        return id;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHikeName() {
        return hikeName;
    }

    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {return rate;}

    public void setRate(int rate) {this.rate = rate;}

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }


}
