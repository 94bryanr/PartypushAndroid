package csu.bryanreilly.partypush.UserData;

import com.google.android.gms.maps.model.LatLng;

public class Party {
    private String name;
    private String locationDescription;
    private LatLng location;

    public Party(String name, String locationDescription){
        this.name = name;
        this.locationDescription = locationDescription;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getName() {
        return name;
    }
}
