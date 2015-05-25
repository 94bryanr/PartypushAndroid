package csu.bryanreilly.partypush.UserData.Location;

import android.location.Location;

import java.util.ArrayList;

public class LocationObserver {
    private static ArrayList<LocationCallback> callbacks = new ArrayList<>();

    public static void notifyChangedLocation(Location location){
        for (LocationCallback callback: callbacks){
            callback.locationChanged(location);
        }
    }

    public static void notifyLocationConnected(){
        for (LocationCallback callback: callbacks){
            callback.locationConnected();
        }
    }

    public static void addCallback(LocationCallback callback){
        callbacks.add(callback);
    }
}
