package csu.bryanreilly.partypush.UserData;

import com.google.android.gms.maps.model.LatLng;

public class Party {
    private String description;
    private String locationDescription;
    private LatLng location;
    private String id;

    public Party(String description, String locationDescription, String id, LatLng location){
        this.description = description;
        this.locationDescription = locationDescription;
        this.id = id;
        this.location = location;
    }

    public String getLocationDescription() { return locationDescription; }

    public String getDescription() { return description; }

    public String getId() { return id; }

    public LatLng getLocation() { return location; }
}