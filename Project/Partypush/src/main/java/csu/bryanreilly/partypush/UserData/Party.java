package csu.bryanreilly.partypush.UserData;

import com.google.android.gms.maps.model.LatLng;

public class Party {
    private String name;
    private String locationDescription;
    private LatLng location;
    private String id;

    public Party(String name, String locationDescription, String id, LatLng location){
        this.name = name;
        this.locationDescription = locationDescription;
        this.id = id;
        this.location = location;
    }

    public String getLocationDescription() { return locationDescription; }

    public String getName() { return name; }

    public String getId() { return id; }

    public LatLng getLocation() { return location; }
}