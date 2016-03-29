package com.example.germangirod.rxjavaexample.uis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import com.example.germangirod.rxjavaexample.data.presenters.LocationCurrentWeatherDataPresenter;
import com.example.germangirod.rxjavaexample.uis.adapters.HomePagerAdapter;
import com.example.germangirod.rxjavaexample.util.SearchViewUtil;
import io.fabric.sdk.android.Fabric;
import org.parceler.Parcels;

/**
 * Created by germangirod on 5/13/15.
 */
public class HomeActivity extends BaseActivity implements LocationCurrentWeatherDataPresenter {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.tab_layout) TabLayout tabLayout;
    @InjectView(R.id.pager) ViewPager viewPager;

    private HomePagerAdapter homePagerAdapter;
    private String[] tableName = { "Current Location Weather", "My Locations Weather" };

    @Override public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        Fabric.with(this, new Answers());
        ButterKnife.inject(this);
        setToolbar();
        setTableLayoutTabs();
        setViewPager();
        setTabLayoutOnSelect();
    }

    private void setTableLayoutTabs() {

        for (int i = 0; i < tableName.length; i++) {

            tabLayout.addTab(tabLayout.newTab().setText(tableName[i]));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void setViewPager() {
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setTabLayoutOnSelect() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchViewUtil searchViewUtil = new SearchViewUtil(menu, getApplicationContext(), this);
        searchViewUtil.setUpUI();
        return true;
    }

    @Override public void getCurrentWeather(WeatherResponse currentWeathers) {
        goToForecastActivity(currentWeathers);
    }

    @Override public void onError(Throwable throwable) {

    }

    private void goToForecastActivity(WeatherResponse weatherResponse) {
        Intent i = new Intent(getApplicationContext(), ForecastActivity.class);
        i.putExtra("weather_response", Parcels.wrap(weatherResponse));
        startActivity(i);
    }
}
