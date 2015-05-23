package csu.bryanreilly.partypush.UI;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import csu.bryanreilly.partypush.UI.Login.LoginActivity;
import csu.bryanreilly.partypush.UI.Main.FragmentInfo;
import csu.bryanreilly.partypush.UI.Main.MainActivity;

public class UIManager {
    public static void returnToLogin(Activity callingActivity){
        Intent startLoginActivity = new Intent(callingActivity, LoginActivity.class);
        //Clears the backstack
        startLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        callingActivity.startActivity(startLoginActivity);
    }

    public static void returnToMain(Activity callingActivity){
        int tabToOpen = FragmentInfo.MapFragment;
        returnToMain(callingActivity, tabToOpen);
    }

    public static void returnToMain(Activity callingActivity, int tab){
        Intent startMainActivity = new Intent(callingActivity, MainActivity.class);
        //Clears the backstack
        startMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startMainActivity.putExtra("Tab", tab);
        callingActivity.startActivity(startMainActivity);
    }
}
