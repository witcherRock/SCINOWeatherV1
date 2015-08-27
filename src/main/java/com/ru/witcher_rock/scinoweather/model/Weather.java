package com.ru.witcher_rock.scinoweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Администратор on 25.08.2015.
 */
public class Weather {
    private long id;

    @SerializedName("main")
    private String weatherMain;
    private String description;
    private String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "id=" + id + "\n"
                + "weatherMain=" + weatherMain + "\n"
                + "description=" + description + "\n"
                + "icon=" + icon;
    }
}
