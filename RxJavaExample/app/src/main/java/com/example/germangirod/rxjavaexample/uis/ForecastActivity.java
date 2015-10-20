package com.example.germangirod.rxjavaexample.uis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.uis.fragments.LocationWeatherForecastFragment;

/**
 * Created by germangirod on 10/19/15.
 */
public class ForecastActivity extends BaseActivity {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    private FragmentTransaction fragmentTransaction;
    private String cityId;

    public static void goTo(Context context, String placeId) {

        Intent intent = new Intent(context, ForecastActivity.class);
        intent.putExtra("place_id", placeId);
        context.startActivity(intent);
    }

    @Override public int getLayoutId() {
        return R.layout.forecast_activity;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.inject(this);
        setToolbar();
        getCityId();
        setFragment();
    }

    private void getCityId() {
        Bundle bundle = getIntent().getExtras();
        cityId = bundle.getString("place_id");
    }

    private void setFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content, LocationWeatherForecastFragment.getInstance(cityId));
        fragmentTransaction.commit();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
