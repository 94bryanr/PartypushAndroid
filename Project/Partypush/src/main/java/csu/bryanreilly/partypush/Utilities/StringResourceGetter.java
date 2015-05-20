package csu.bryanreilly.partypush.Utilities;

import android.app.Activity;

public class StringResourceGetter {
    private Activity getterActivity;
    public StringResourceGetter(Activity activity){
        getterActivity = activity;
    }

    public String get(int id){
        return getterActivity.getString(id);
    }
}
