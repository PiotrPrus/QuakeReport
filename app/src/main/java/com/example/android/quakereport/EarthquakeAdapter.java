package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Piotr on 07.11.2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes){

        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag_text_view);

        DecimalFormat formatter = new DecimalFormat("0.0");
        String formattedMagnitude = formatter.format(currentEarthquake.getMagnitude());

        magTextView.setText(formattedMagnitude);

        //TODO: Split the String into 2 parts. One is containing the distance details and vector and second one has localization info.

        TextView offsetLocTextView = (TextView) listItemView.findViewById(R.id.offset_loc_text_view);
        TextView primaryLocTextView = (TextView) listItemView.findViewById(R.id.primary_loc_text_view);
        String fullLocation = currentEarthquake.getEarthquakeLoc();

        if (fullLocation.contains("of")){

            String[] parts = fullLocation.split("(?<=of)");
            String offsetLoc = parts[0];
            String primaryLoc = parts[1];

            offsetLocTextView.setText(offsetLoc);
            primaryLocTextView.setText(primaryLoc);

        } else {
            offsetLocTextView.setText("Near the");
            primaryLocTextView.setText(fullLocation);
        }

        Date dateObject = new Date(currentEarthquake.getEarthquakeDate());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        String formattedDate = formatDate(dateObject);

        dateTextView.setText(formattedDate);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        String formattedTime = formatTime(dateObject);

        timeTextView.setText(formattedTime);

        return listItemView;

    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
        return timeFormat.format(dateObject);
    }

}
