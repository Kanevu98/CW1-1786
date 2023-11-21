package com.example.hikecw.Model;

public class ObservationModel {
    private int id;
    private String observationName;
    private String time;
    private String comment;
    private int hikeRef;

    public ObservationModel(int id, String observationName, String time, String comment, int hikeRef) {
        this.id = id;
        this.observationName = observationName;
        this.time = time;
        this.comment = comment;
        this.hikeRef = hikeRef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservationName() {
        return observationName;
    }

    public void setObservationName(String observationName) {
        this.observationName = observationName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getHikeRef() {
        return hikeRef;
    }

    public void setHikeRef(int hikeRef) {
        this.hikeRef = hikeRef;
    }
}
