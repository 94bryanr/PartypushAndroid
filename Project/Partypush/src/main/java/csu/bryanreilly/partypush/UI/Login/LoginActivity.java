package csu.bryanreilly.partypush.UI.Login;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.facebook.AppEventsLogger;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.Utilities.ContextGetter;
import csu.bryanreilly.partypush.Utilities.SingletonStarter;

public class LoginActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /* Logs 'install' and 'app activate' App Events.
        Basically allows Facebook to track demographics. */
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /* Logs 'app deactivate' App Event. This forces Facebook
        to STOP tracking demographics and time spent on the app.
        This may be added to any activity where the corresponding
        AppEventLogger.activateApp(this) line also resides. */
        AppEventsLogger.deactivateApp(this);
    }
}