package csu.bryanreilly.partypush.UserData;

import com.google.android.gms.maps.model.LatLng;

public class LocationManager {
    // TODO: Need to refactor location code out of map class

    private static LatLng currentLocation;

    public static LatLng getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(LatLng currentLocation) {
        LocationManager.currentLocation = currentLocation;
    }
}
