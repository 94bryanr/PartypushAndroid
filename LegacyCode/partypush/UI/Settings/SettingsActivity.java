package csu.bryanreilly.partypush.UI.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Login.LoginActivity;

public class SettingsActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEV ACTIVITY: Settings Activity Started");
        setContentView(R.layout.settings_activity_settings);
        if (savedInstanceState == null) {
            ChangeCurrentFragmentTo(new SettingsFragment(), false);
        }
    }

    public void ChangeCurrentFragmentTo(Fragment fragment, boolean addToBackStack){
        if(addToBackStack)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack("Stack").commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    //Called from a fragment, starts the Login activity.
    public void StartLoginActivity(){
        Intent startLoginActivity = new Intent(this, LoginActivity.class);
        startLoginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startLoginActivity);
    }
}
