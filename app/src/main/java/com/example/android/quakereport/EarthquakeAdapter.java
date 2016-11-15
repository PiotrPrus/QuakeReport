package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //Format data from UNIX time to dd-mm-yyyy
        DecimalFormat formatter = new DecimalFormat("0.0");
        String formattedMagnitude = formatter.format(currentEarthquake.getMagnitude());

        magTextView.setText(formattedMagnitude);

        //Split the String into 2 parts. One is containing the distance details and vector and second one has localization info.

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

    //getMagnitudeColor - method that is invoking the proper color for the magnitude value.
    //The color is set on mag_text_view with a circle
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorId;

        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorId = R.color.magnitude9;
                break;
            default:
                magnitudeColorId = R.color.magnitude10;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorId);

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
