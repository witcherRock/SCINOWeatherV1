package com.ru.witcher_rock.scinoweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Администратор on 25.08.2015.
 */
public class WeatherMain {

    @SerializedName("temp")
    private double temperature;
    private double pressure;
    private int humidity;

    @SerializedName("temp_max")
    private double temperatureMax;

    @SerializedName("temp_min")
    private double temperatureMin;

    @SerializedName("sea_level")
    private double seaLevel;

    @SerializedName("grnd_level")
    private double grndLevel;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(double grndLevel) {
        this.grndLevel = grndLevel;
    }

    @Override
    public String toString() {
        return "temperature=" + temperature + "\n"
                + "pressure=" + pressure + "\n"
                + "humidity=" + humidity + "\n"
                + "temperatureMax=" + temperatureMax + "\n"
                + "temperatureMin=" + temperatureMin + "\n"
                + "seaLevel=" + seaLevel + "\n"
                + "grndLevel=" + grndLevel;
    }
}
