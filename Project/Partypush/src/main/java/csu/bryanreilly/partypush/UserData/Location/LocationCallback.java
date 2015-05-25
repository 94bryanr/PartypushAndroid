package csu.bryanreilly.partypush.UserData.Location;

import android.location.Location;

public interface LocationCallback {
    void locationChanged(Location location);
    void locationConnected();
}
