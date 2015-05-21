package com.example.germangirod.rxjavaexample.api;

import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.api.model.Forecast;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by germangirod on 5/1/15.
 */
public interface Api {

    @GET("/weather")
    Observable<WeatherResponse> getWeather(@Query("q") String cityName);

    @GET("/forecast")
    Observable<Forecast> getForecast(@Query("q") String city);

    @GET("/station/find")
    Observable<List<CurrentWeather>> getCurrentWeather(@Query("lat")String lat, @Query("lon")String lon);


}
