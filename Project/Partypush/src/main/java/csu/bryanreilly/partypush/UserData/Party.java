package csu.bryanreilly.partypush.UserData;

import com.google.android.gms.maps.model.LatLng;

public class Party {
    private String name;
    private String locationDescription;
    private LatLng location;
    private String id;

    public Party(String name, String locationDescription, String id){
        this.name = name;
        this.locationDescription = locationDescription;
        this.id = id;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}