package com.ru.witcher_rock.scinoweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Администратор on 25.08.2015.
 */
public class Coordinate {

    @SerializedName("lon")
    private double longitude;

    @SerializedName("lat")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "longitude=" + longitude
                + "latitude=" + latitude;
    }
}
