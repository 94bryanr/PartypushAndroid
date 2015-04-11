package csu.bryanreilly.partypush.UI.Main.Map;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainMapFragment extends SupportMapFragment implements LocationListener,
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, android.location.LocationListener {
    private Location mCurrentLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private GoogleMap map;
    private Marker myLocationMarker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get GoogleMap to callback to this activity when it starts
        getMapAsync(this);
        //Initialize Google API Client
        buildGoogleApiClient();
    }

    //Initialize Google Client
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    //Google client connection is connected
    public void onConnected(Bundle connectionHint) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.i("LOCATION", "CONNECTED!");
        Log.i("LOCATION", mCurrentLocation.toString());
        createLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, this);
        setCamera();
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
    //Google map is ready
    public void onMapReady(GoogleMap map){
        Log.i("LOCATION", "MAP READY");
        this.map = map;
    }

    private void setCamera(){
        if (mCurrentLocation != null)
        {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
                    .zoom(14)
                    .tilt(50)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            updateLocationMarker(mCurrentLocation);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("LOCATION", "Location Updated");
        mCurrentLocation = location;
        updateLocationMarker(location);
    }

    private void updateLocationMarker(Location location){
        if (map != null) {
            if (myLocationMarker != null)
                myLocationMarker.remove();
            myLocationMarker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
                    .title("You")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
        else {
            Log.i("LOCATION", "MAP NULL");
        }
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
    //Google client connection was suspended
    public void onConnectionSuspended(int i) {
        Log.i("LOCATION", "Connection SUSPENDED");
    }

    @Override
    // Google client connection has failed
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOCATION", "Connection FAILED");
    }
}
