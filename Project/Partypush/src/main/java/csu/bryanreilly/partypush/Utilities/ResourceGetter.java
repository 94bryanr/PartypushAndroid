package csu.bryanreilly.partypush.Utilities;

import android.app.Activity;

public class ResourceGetter {
    private Activity getterActivity;
    public ResourceGetter(Activity activity){
        getterActivity = activity;
    }

    public String getString(int id){
        return getterActivity.getString(id);
    }
}
