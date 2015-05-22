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

import java.util.ArrayList;

import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.LocationManager;
import csu.bryanreilly.partypush.UserData.Party;

public class MainMapFragment extends SupportMapFragment implements LocationListener,
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, android.location.LocationListener {
    private Location mCurrentLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private static GoogleMap map;
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
        createLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        setCamera();
        //TODO: Doesn't work if parties not retrieved
        refreshPartyIcons(AccountManager.getParties());
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
    public void onMapReady(GoogleMap map) {
        this.map = map;
        setCamera();
        //TODO: Doesn't work if parties not retrieved
        refreshPartyIcons(AccountManager.getParties());
    }

    private void setCamera(){
        if (mCurrentLocation != null && map != null)
        {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
                    .zoom(15)
                    .tilt(50)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            updateLocationMarker(mCurrentLocation);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        updateLocationMarker(location);
    }

    private void updateLocationMarker(Location location){
        if (map != null) {
            if (myLocationMarker != null) {
                myLocationMarker.remove();
            }
            LatLng currentLocation = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            myLocationMarker = map.addMarker(new MarkerOptions()
                    .position(currentLocation)
                    .title("You")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            LocationManager.setCurrentLocation(currentLocation);
        }
    }

    public static void refreshPartyIcons(ArrayList<Party> parties){
        //TODO: Never deletes icons
        Log.i("Map", "Attempting to retrieve Parties");
        if(map != null) {
            Log.i("Map", "Map not Null");
            for (Party party : parties) {
                Log.i("Map", "Adding Party " + party.getLocationDescription());
                map.addMarker(new MarkerOptions()
                        .position(party.getLocation())
                        .title(party.getLocationDescription())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            }
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

    }

    @Override
    // Google client connection has failed
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
