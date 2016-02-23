package com.example.germangirod.rxjavaexample.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.presenters.LocationCurrentWeatherData;
import com.example.germangirod.rxjavaexample.data.presenters.LocationCurrentWeatherDataPresenter;
import java.io.IOException;
import java.util.List;

/**
 * Created by germangirod on 1/24/16.
 */
public class SearchViewUtil {

    private static final String LOG = "SearchViewUtil";
    private Menu menu;
    private Context context;
    private LocationCurrentWeatherData locationCurrentWeatherData;
    private LocationCurrentWeatherDataPresenter locationCurrentWeatherDataPresenter;

    public SearchViewUtil(Menu menu, Context context, LocationCurrentWeatherDataPresenter locationCurrentWeatherDataPresenter) {
        this.menu = menu;
        this.context = context;
        this.locationCurrentWeatherDataPresenter = locationCurrentWeatherDataPresenter;
    }

    public void setUpUI() {
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText editText = (EditText) searchView.findViewById(id);
        final MenuItem menuItem = menu.getItem(0);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                Log.e("LocationSearchSubmit", "");
                final Geocoder geocoder = new Geocoder(context);

                try {
                    List<Address> addresses = geocoder.getFromLocationName(query, 1);
                    locationCurrentWeatherData = new LocationCurrentWeatherData();
                    locationCurrentWeatherData.setView(locationCurrentWeatherDataPresenter);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        locationCurrentWeatherData.getCurrentWeatherData(String.valueOf(address.getLatitude()), String.valueOf(address.getLongitude()));
                    } else {

                    }
                    menuItem.collapseActionView();
                } catch (IOException e) {
                    Log.e(LOG, e.toString());
                }
                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                Log.e("LocationSearchTxtChange", "");
                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
    }
}
