package com.example.germangirod.rxjavaexample.uis;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class HomeFragment extends LocationFragment {

    private WeatherForcastLocation api;
    private HomeAdapter homeAdapter;
    @InjectView(R.id.my_recycler_view) RecyclerView recyclerView;

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, v);
        api = new WeatherForcastLocationApi();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        homeAdapter = new HomeAdapter(getActivity(), null);

        return v;
    }

    @Override public void onLocationChanged(Location location) {
        //super.onLocationChanged(location);
        api.getCurrentWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CurrentWeather>>() {
                    @Override public void call(final List<CurrentWeather> a) {
                        homeAdapter = new HomeAdapter(getActivity(), a);

                        recyclerView.setAdapter(homeAdapter);

                        homeAdapter.setRowClick(new HomeAdapter.onRowClick() {
                            @Override public void clickWeatherRow(View v, int i) {
                                HomeDetail.goTo(getActivity(), a.get(i));
                            }
                        });
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        //name.setText(String.valueOf(throwable));
                    }
                });
    }
}
