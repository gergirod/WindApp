package com.example.germangirod.rxjavaexample.data.api;

import com.example.germangirod.rxjavaexample.data.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.data.model.Forecast;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import java.util.List;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by germangirod on 5/1/15.
 */
public interface Api {

    @GET("/weather")
    Observable<WeatherResponse> getWeatherByCityName(@Query("q") String cityName, @Query("APPID") String key);

    @GET("/weather")
    Observable<WeatherResponse> getWeatherByCityId(@Query("id") String cityId, @Query("APPID") String key);

    @GET("/weather")
    Observable<WeatherResponse> getWeatherByCoordinates(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String key);

    @GET("/forecast")
    Observable<Forecast> getCityForecastById(@Query("id") String cityId, @Query("APPID") String key);

    @GET("/group")
    Observable<CurrentWeather> getCityListById(@Query("id") String cityId, @Query("APPID") String key);

    @GET("/station/find")
    Observable<List<CurrentWeather>> getWeatherStationsByCoordinates(@Query("lat")String lat, @Query("lon")String lon, @Query("APPID") String key);


}
