package com.ru.witcher_rock.scinoweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Администратор on 25.08.2015.
 */
public class WeatherData {

    @SerializedName("coord")
    private Coordinate coordinate;

    private List<Weather> weather;

    @SerializedName("main")
    private WeatherMain weatherMain;

    @SerializedName("wind")
    private WeatherWind weatherWind;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(WeatherMain weatherMain) {
        this.weatherMain = weatherMain;
    }

    public void setWeatherWind(WeatherWind weatherWind){this.weatherWind = weatherWind;}

    public WeatherWind getWeatherWind(){return this.weatherWind;}
    @Override
    public String toString() {
        return "coordinate=" + coordinate + "\n"
                + "weather=" + weather + "\n"
                + "main=" + weatherMain + "\n"
                + "wind=" + weatherWind;
    }
}
