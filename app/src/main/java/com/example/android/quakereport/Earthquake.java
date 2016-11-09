package com.example.android.quakereport;
/**
 * Created by Piotr on 06.11.2016.
 */


public class Earthquake {

    private double mMagnitude;
    private String mEarthquakeLoc;
    private long mEarthquakeDate;

    public Earthquake(double magnitude, String earthquakeLoc, long earthquakeDate){

        mMagnitude = magnitude;
        mEarthquakeLoc = earthquakeLoc;
        mEarthquakeDate = earthquakeDate;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getEarthquakeLoc() {
        return mEarthquakeLoc;
    }

    public long getEarthquakeDate() {
        return mEarthquakeDate;
    }
}
