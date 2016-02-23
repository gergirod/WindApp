package com.example.germangirod.rxjavaexample.uis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import com.example.germangirod.rxjavaexample.uis.widget.ArrowView;
import com.example.germangirod.rxjavaexample.util.WeatherImageUtil;
import java.util.List;

/**
 * Created by germangirod on 5/23/15.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherRowHolder> {

    private Context context;
    private List<WeatherResponse> currentWeathers;
    private onRowClick onRowClick;

    public WeatherListAdapter(Context context, List<WeatherResponse> currentWeathers) {

        this.context = context;
        this.currentWeathers = currentWeathers;
    }

    public void setRowClick(onRowClick onRowClick) {
        this.onRowClick = onRowClick;
    }

    @Override public WeatherRowHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather_row, viewGroup, false);
        WeatherRowHolder weatherRowHolder = new WeatherRowHolder(v);

        return weatherRowHolder;
    }

    @Override public void onBindViewHolder(WeatherRowHolder weatherRowHolder, int i) {

        WeatherResponse weatherResponse = currentWeathers.get(i);
        weatherRowHolder.temperature.setText(weatherResponse.getMain().getTemp());
        weatherRowHolder.arrowWind.setAngleRotation(weatherResponse.getWind().getDeg());
        weatherRowHolder.windSpeed.setText(weatherResponse.getWind().getSpeed());
        weatherRowHolder.windDegree.setText(weatherResponse.getWind().degToString());
        weatherRowHolder.city.setText(weatherResponse.getName());

        WeatherImageUtil weatherImageUtil = new WeatherImageUtil(weatherResponse);

        weatherRowHolder.weatherImage.setImageResource(weatherImageUtil.setWeatherImage());
    }

    @Override public int getItemCount() {
        return currentWeathers.size();
    }

    public interface onRowClick {
        void clickWeatherRow(View v, int i);
    }

    public class WeatherRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.arrow_wind) ArrowView arrowWind;
        @InjectView(R.id.weather_image) ImageView weatherImage;
        @InjectView(R.id.wind_speed) TextView windSpeed;
        @InjectView(R.id.wind_dg) TextView windDegree;
        @InjectView(R.id.temperature) TextView temperature;
        @InjectView(R.id.city) TextView city;

        public WeatherRowHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            onRowClick.clickWeatherRow(v, getPosition());
        }
    }
}
