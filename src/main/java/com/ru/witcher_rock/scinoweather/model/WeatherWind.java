package com.ru.witcher_rock.scinoweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Администратор on 25.08.2015.
 */
public class WeatherWind {

    @SerializedName("speed")
    private double mSpeedWind;

    @SerializedName("deg")
    private double mDegWind;

    public double getSpeedWind(){return this.mSpeedWind;}
    public double getDegWind() {return this.mDegWind;}
    public void setSpeedWind(double speed){ this.mSpeedWind = speed;}
    public void setDegWind(double degree){this.mDegWind = degree;}

    @Override
    public String toString() {
        return "speedWind=" + mSpeedWind + "\n"
                + "degreeWind=" + mDegWind;
    }
}
