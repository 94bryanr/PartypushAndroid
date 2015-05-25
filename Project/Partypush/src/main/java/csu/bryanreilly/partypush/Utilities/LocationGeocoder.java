package csu.bryanreilly.partypush.Utilities;

import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import csu.bryanreilly.partypush.UserData.Location.LocationManager;

public class LocationGeocoder {
    Geocoder geocoder;
    List<Address> foundAddresses;

    public LocationGeocoder(String location){
        initializeGeocoder();
        LatLng currentLocation = LocationManager.getInstance().getCurrentLocation();

        if(currentLocation != null) {
            foundAddresses = getAddresses(location, currentLocation);
        }
    }

    private void initializeGeocoder(){
        try {
            geocoder = new Geocoder(ContextGetter.getInstance().getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Address> getAddresses(String location, LatLng currentLocation){
        List<Address> foundAddresses = Collections.emptyList();
        int latLngBoundaryBox = 4;
        int resultsToReturn = 4;
        try {
            foundAddresses = geocoder.getFromLocationName(
                    location,
                    resultsToReturn,
                    currentLocation.latitude - latLngBoundaryBox,
                    currentLocation.longitude - latLngBoundaryBox,
                    currentLocation.latitude + latLngBoundaryBox,
                    currentLocation.longitude + latLngBoundaryBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundAddresses;
    }

    public List<Address> getMatchedAddresses(){
        return foundAddresses;
    }

    public boolean foundMatch(){
        return foundAddresses.size() != 0;
    }
}
