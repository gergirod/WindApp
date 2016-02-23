package com.example.germangirod.rxjavaexample.data.presenters;

import com.example.germangirod.rxjavaexample.data.model.Forecast;

/**
 * Created by germangirod on 10/19/15.
 */
public interface ForecastPresenter {

    void getForecastByCityId(Forecast forecast);

    void onError(Throwable throwable);
}
