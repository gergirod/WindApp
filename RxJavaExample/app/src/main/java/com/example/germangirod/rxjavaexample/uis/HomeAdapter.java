package com.example.germangirod.rxjavaexample.uis;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import java.util.List;

/**
 * Created by germangirod on 5/23/15.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.WeatherRowHolder> {

    private Context context;
    private List<CurrentWeather> currentWeathers;
    private onRowClick onRowClick;

    public HomeAdapter(Context context, List<CurrentWeather> currentWeathers) {

        this.context = context;
        this.currentWeathers = currentWeathers;
    }

    public void setRowClick(onRowClick onRowClick){
        this.onRowClick=onRowClick;
    }

    @Override public WeatherRowHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather_row, viewGroup, false);
        WeatherRowHolder weatherRowHolder = new WeatherRowHolder(v);




        return weatherRowHolder;
    }

    @Override public void onBindViewHolder(WeatherRowHolder weatherRowHolder, int i) {

        CurrentWeather currentWeather= currentWeathers.get(i);

        weatherRowHolder.stationDistance.setText(currentWeather.getDistance().toString());
        weatherRowHolder.stationName.setText(currentWeather.getStation().getName());



    }

    @Override public int getItemCount() {
        return currentWeathers.size();
    }

    public class WeatherRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @InjectView(R.id.station_name) TextView stationName;
        @InjectView(R.id.station_distance) TextView stationDistance;

        public WeatherRowHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            onRowClick.clickWeatherRow(v,getPosition());
        }
    }

    public interface onRowClick{
        public void clickWeatherRow(View v, int i);
    }
}
