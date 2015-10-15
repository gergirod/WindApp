package com.example.germangirod.rxjavaexample.uis.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by germangirod on 6/4/15.
 */
public abstract class LocationBaseFragment extends Fragment
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleApiClient = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override public void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override public void onConnected(Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(1000);

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override public void onConnectionSuspended(int i) {

    }

    @Override public void onLocationChanged(Location location) {
    }

    @Override public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
