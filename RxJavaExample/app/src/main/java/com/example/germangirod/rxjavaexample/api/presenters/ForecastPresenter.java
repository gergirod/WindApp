package com.example.germangirod.rxjavaexample.api.presenters;

import com.example.germangirod.rxjavaexample.api.model.Forecast;

/**
 * Created by germangirod on 10/19/15.
 */
public interface ForecastPresenter {

    void getForecastByCityId(Forecast forecast);

    void onError(Throwable throwable);
}
