package com.example.germangirod.rxjavaexample.data.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.joda.time.DateTime;

/**
 * Created by germangirod on 5/1/15.
 */
public class WeatherResponse {

    public Coords coords;
    public Sys sys;
    public List<Weather> weather;
    public Main main;
    public Wind wind;
    public Rain rain;
    public Clouds clouds;
    public long dt;
    public int id;
    public String name;
    public int cod;
    public String dt_txt;

    public Coords getCoords() {
        return coords;
    }

    public Sys getSys() {
        return sys;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public String getDt() {
        return "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public boolean isDay() {

        Date date = new Date();
        Timestamp actualHour = new Timestamp(date.getTime());
        Timestamp sunriseHour = new Timestamp(Long.valueOf(sys.getSunrise()) * 1000);
        Timestamp sunsetHour = new Timestamp(Long.valueOf(sys.getSunset()) * 1000);

        if (actualHour.getHours() > sunriseHour.getHours() && actualHour.getHours() < sunsetHour.getHours()) {

            return true;
        }

        return false;
    }

    private DateTime setUpDateTime(){
        Timestamp date = new Timestamp(Long.valueOf(dt * 1000));
        DateTime dateTime = new DateTime(date);

        return dateTime;
    }

    private Calendar setUpCalendar(){

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(setUpDateTime().toDate());

        return calendar;

    }

    public String getDay(){

        DateTime dateTime = setUpDateTime();
        Calendar calendar = setUpCalendar();

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        return dayFormat.format(dateTime.toDate())+" "+calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String getHours(){

        DateTime dateTime = setUpDateTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a",Locale.US);

        return dateFormat.format(dateTime.toDate());

    }

    public String getFullDate(){

        return getDay()+" "+getHours();

    }


}
