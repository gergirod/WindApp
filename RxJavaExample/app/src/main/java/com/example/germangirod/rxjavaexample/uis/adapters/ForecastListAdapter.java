package com.example.germangirod.rxjavaexample.uis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;

/**
 * Created by germangirod on 5/23/15.
 */
public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastRowHolder> {

    private Context context;
    private List<WeatherResponse> currentWeathers;
    private onRowClick onRowClick;

    public ForecastListAdapter(Context context, List<WeatherResponse> currentWeathers) {

        this.context = context;
        this.currentWeathers = currentWeathers;
    }

    public void setRowClick(onRowClick onRowClick) {
        this.onRowClick = onRowClick;
    }

    @Override public ForecastRowHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather_row, viewGroup, false);
        ForecastRowHolder weatherRowHolder = new ForecastRowHolder(v);

        return weatherRowHolder;
    }

    @Override public void onBindViewHolder(ForecastRowHolder weatherRowHolder, int i) {

        WeatherResponse weatherResponse = currentWeathers.get(i);

        weatherRowHolder.stationName.setText(String.valueOf(weatherResponse.getDt_txt()));
        weatherRowHolder.stationDistance.setText(String.valueOf(weatherResponse.getWind().getSpeed()));
    }

    @Override public int getItemCount() {
        return currentWeathers.size();
    }

    public interface onRowClick {
        void clickWeatherRow(View v, int i);
    }

    public class ForecastRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.station_distance) TextView stationDistance;
        @InjectView(R.id.station_name) TextView stationName;

        public ForecastRowHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            onRowClick.clickWeatherRow(v, getPosition());
        }
    }
}
