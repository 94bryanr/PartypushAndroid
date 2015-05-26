package csu.bryanreilly.partypush.UI.Main.Map;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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

import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Location.LocationCallback;
import csu.bryanreilly.partypush.UserData.Location.LocationManager;
import csu.bryanreilly.partypush.UserData.Location.LocationObserver;
import csu.bryanreilly.partypush.UserData.Party.Party;
import csu.bryanreilly.partypush.UserData.Party.PartyCallback;
import csu.bryanreilly.partypush.UserData.Party.PartyObserver;

public class MainMapFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationCallback, PartyCallback{

    private Marker myLocationMarker;
    private GoogleMap map;
    private ArrayList<Marker> partyMarkers = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get GoogleMap to callback to this activity when it starts
        getMapAsync(this);

        LocationObserver.addCallback(this);
        PartyObserver.addCallback(this);
    }

    @Override
    //Google client connection is connected
    public void onConnected(Bundle connectionHint) {}

    @Override
    //Google map is ready
    public void onMapReady(GoogleMap map) {
        this.map = map;
        map.getUiSettings().setRotateGesturesEnabled(false);

        centerCamera();
        updateLocationMarker(LocationManager.getInstance().getCurrentLocation());
        refreshPartyIcons();
    }

    private void centerCamera(){
        LatLng currentLocation = LocationManager.getInstance().getCurrentLocation();
        if (currentLocation != null && map != null)
        {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(currentLocation)
                    .zoom(Constants.MAP_ZOOM_LEVEL)
                    .tilt(50)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    private void updateLocationMarker(LatLng currentLocation){
        if(currentLocation == null)
            return;
        // Remove previous marker
        if (myLocationMarker != null) {
            myLocationMarker.setPosition(currentLocation);
        }
        else if (map != null){
            myLocationMarker = map.addMarker(new MarkerOptions()
                    .position(currentLocation)
                    .title("You")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }

    public void refreshPartyIcons(){
        if(map == null)
            return;
        // Remove old markers to avoid duplicates
        for(Marker marker: partyMarkers){
            marker.remove();
        }
        for (Party party : AccountManager.getParties()) {
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(party.getLocation())
                    .title(party.getDescription())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            partyMarkers.add(marker);
        }
    }

    @Override
    //Google client connection was suspended
    public void onConnectionSuspended(int i) {

    }

    @Override
    // Google client connection has failed
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void locationChanged(Location location) {
        updateLocationMarker(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void locationConnected() {
        centerCamera();
    }

    @Override
    public void partiesChanged() {
        refreshPartyIcons();
    }
}
