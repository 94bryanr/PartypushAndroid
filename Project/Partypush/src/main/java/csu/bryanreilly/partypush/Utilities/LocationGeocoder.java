package csu.bryanreilly.partypush.Utilities;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import csu.bryanreilly.partypush.UserData.LocationManager;

public class LocationGeocoder {
    private LatLng absolutePosition;

    public LocationGeocoder(String location, LatLng currentPosition){

        Geocoder geocoder = null;
        try {
            geocoder = new Geocoder(ContextGetter.getInstance().getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LatLng currentLocation = LocationManager.getCurrentLocation();
        int addressSearchSpace = 4;

        if(currentLocation != null){
            try {
                Log.i("LocationGeocoder", "Location non null");
                List<Address> markerLocation = geocoder.getFromLocationName(
                        location,
                        1,
                        currentLocation.latitude - addressSearchSpace,
                        currentLocation.longitude - addressSearchSpace,
                        currentLocation.latitude + addressSearchSpace,
                        currentLocation.longitude + addressSearchSpace);

                Log.i("LocationGeocoder", "Matches: " + markerLocation.size());
                if(markerLocation != null && markerLocation.size() == 0){
                    return;
                }

                LatLng partyLocation = new LatLng(markerLocation.iterator().next().getLatitude(), markerLocation.iterator().next().getLongitude());
                Log.i("LocationGeocoder", "Latitude: " + partyLocation.latitude);
                Log.i("LocationGeocoder", "Longitude: " + partyLocation.longitude);
                absolutePosition = partyLocation;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.i("LocationGeocoder", "Location is null");
            try {
                List<Address> markerLocation = geocoder.getFromLocationName(location, 1);
                Log.i("LocationGeocoder", "Matches: " + markerLocation.size());
                if(markerLocation != null && markerLocation.size() == 0){
                    return;
                }

                LatLng partyLocation = new LatLng(markerLocation.iterator().next().getLatitude(), markerLocation.iterator().next().getLongitude());
                Log.i("LocationGeocoder", "Latitude: " + partyLocation.latitude);
                Log.i("LocationGeocoder", "Longitude: " + partyLocation.longitude);
                absolutePosition = partyLocation;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public LatLng getMatch(){
        return absolutePosition;
    }

    public boolean foundMatch(){
        return absolutePosition != null;
    }
}
