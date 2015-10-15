package com.example.germangirod.rxjavaexample.uis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.api.WeatherForecastLocation;
import com.example.germangirod.rxjavaexample.api.WeatherForecastLocationApi;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import com.example.germangirod.rxjavaexample.api.presenters.MyLocationsCurrentWeatherData;
import com.example.germangirod.rxjavaexample.api.presenters.MyLocationsCurrentWeatherPresenter;
import java.util.List;

/**
 * Created by germangirod on 5/13/15.
 */
public class HomeFragment extends Fragment implements MyLocationsCurrentWeatherPresenter {

    private WeatherForecastLocation api;
    private HomeAdapter homeAdapter;
    private MyLocationsCurrentWeatherData myLocationsCurrentWeatherData;
    @InjectView(R.id.my_recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.loading) ProgressBar loading;

    public static Fragment getInstance() {
        HomeFragment f = new HomeFragment();
        return f;
    }

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, v);
        api = new WeatherForecastLocationApi();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //getWeatherList();

        return v;
    }

    private void getWeatherList(){
        myLocationsCurrentWeatherData = new MyLocationsCurrentWeatherData();
        myLocationsCurrentWeatherData.setView(this);
        myLocationsCurrentWeatherData.getMyCurrentWeatherList("3435910");
    }

    @Override public void getCurrentWeather(final List<WeatherResponse> currentWeathers) {
        homeAdapter = new HomeAdapter(getActivity(), currentWeathers);

        recyclerView.setAdapter(homeAdapter);
        loading.setVisibility(View.GONE);

        homeAdapter.setRowClick(new HomeAdapter.onRowClick() {
            @Override public void clickWeatherRow(View v, int i) {

            }
        });
    }

    @Override public void onError(Throwable throwable) {

    }
}
