package csu.bryanreilly.partypush.UserData;

import com.google.android.gms.maps.model.LatLng;

public class Party {
    private String name;
    private LatLng location;

    public Party(String name, LatLng location){
        this.name = name;
        this. location = location;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
