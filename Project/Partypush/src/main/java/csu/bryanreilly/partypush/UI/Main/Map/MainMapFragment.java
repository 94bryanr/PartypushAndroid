package csu.bryanreilly.partypush.UI.Main.Map;

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

import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.LocationManager;
import csu.bryanreilly.partypush.UserData.Party;

public class MainMapFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Marker myLocationMarker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get GoogleMap to callback to this activity when it starts
        getMapAsync(this);
    }

    @Override
    //Google client connection is connected
    public void onConnected(Bundle connectionHint) {}

    @Override
    //Google map is ready
    public void onMapReady(GoogleMap map) {
        centerCamera(map);
        updateLocationMarker(map);
        refreshPartyIcons(map);
    }

    private void centerCamera(GoogleMap map){
        LatLng currentLocation = LocationManager.getInstance().getCurrentLocation();
        if (currentLocation != null && map != null)
        {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(currentLocation)
                    .zoom(15)
                    .tilt(50)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    private void updateLocationMarker(GoogleMap map){
        // Remove previous marker
        if (myLocationMarker != null) {
            myLocationMarker.remove();
        }

        LatLng currentLocation = LocationManager.getInstance().getCurrentLocation();

        if (currentLocation != null) {
            myLocationMarker = map.addMarker(new MarkerOptions()
                    .position(currentLocation)
                    .title("You")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }

    public static void refreshPartyIcons(GoogleMap map){
        for (Party party : AccountManager.getParties()) {
            map.addMarker(new MarkerOptions()
                    .position(party.getLocation())
                    .title(party.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
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
}
