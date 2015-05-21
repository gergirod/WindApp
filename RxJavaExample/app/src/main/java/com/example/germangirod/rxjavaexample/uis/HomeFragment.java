package com.example.germangirod.rxjavaexample.uis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.api.WeatherForcastLocation;
import com.example.germangirod.rxjavaexample.api.WeatherForcastLocationApi;
import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/**
 * Created by germangirod on 5/13/15.
 */
public class HomeFragment extends Fragment {

    WeatherForcastLocation api;
    @InjectView(R.id.name) TextView name;

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, v);
        api = new WeatherForcastLocationApi();

        /*AppObservable.bindFragment(this, api.getLocationWeather("Buenos Aires"))
                .subscribeOn(Schedulers.io())
                .finallyDo(new Action0() {
            @Override public void call() {

            }
        }).subscribe(new Action1<WeatherResponse>() {
            @Override public void call(WeatherResponse weatherResponse) {
                DateTime dt = new DateTime(1431574057);
                int a = dt.getYear();
                name.setText(String.valueOf(a));
            }
        }, new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {

                name.setText(String.valueOf(throwable));
            }
        });*/

        /*AppObservable.bindFragment(this, api.getLocationForecast("Buenos Aires"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .finallyDo(new Action0() {
                    @Override public void call() {

                    }
                }).subscribe(new Action1<Forecast>() {
            @Override public void call(Forecast forecast) {

                name.setText(String.valueOf(forecast.getCity().getName()));

            }
            },new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {
                name.setText(String.valueOf(throwable));
            }
        });*/

        api.getCurrentWeather("-34.61","-58.37")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CurrentWeather>>() {
                    @Override public void call(List<CurrentWeather> a) {
                        name.setText(String.valueOf(a.get(0).getWeatherResponse().getWind().getSpeed()));
                    }
                },new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        name.setText(String.valueOf(throwable));
                    }
                });




        return v;
    }


}
