package csu.bryanreilly.partypush.UserData.Location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import csu.bryanreilly.partypush.Utilities.ContextGetter;

public class LocationManager implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, android.location.LocationListener{
    private static LocationManager locationManager;
    private Location mCurrentLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private LocationManager(Context context){
        buildGoogleApiClient(context);
    }

    public static LocationManager getInstance(){
        if (locationManager == null){
            try {
                locationManager = new LocationManager(ContextGetter.getInstance().getContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return locationManager;
    }

    public LatLng getCurrentLocation() {
        if (mCurrentLocation != null) {
            return new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        }
        else {
            return null;
        }
    }

    //Initialize Google Client
    protected synchronized void buildGoogleApiClient(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        createLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        LocationObserver.notifyLocationConnected();
    }

    private void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        final int SIXTY_SECONDS = 60000;
        mLocationRequest.setInterval(SIXTY_SECONDS);
        final int HALF_SECOND = 500;
        mLocationRequest.setFastestInterval(HALF_SECOND);
        // Needs to be high accuracy so we can check if a user is at a party
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        LocationObserver.notifyChangedLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
