package com.example.android.quakereport;
/**
 * Created by Piotr on 06.11.2016.
 */


public class Earthquake {

    private double mMagnitude;
    private String mEarthquakeLoc;
    private long mEarthquakeDate;
    private String mEarthquakeURL;

    public Earthquake(double magnitude, String earthquakeLoc, long earthquakeDate, String earthquakeURL){

        mMagnitude = magnitude;
        mEarthquakeLoc = earthquakeLoc;
        mEarthquakeDate = earthquakeDate;
        mEarthquakeURL = earthquakeURL;
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

    public String getEarthquakeURL() {
        return mEarthquakeURL;
    }
}
