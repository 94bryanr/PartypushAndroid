package csu.bryanreilly.partypush.UI.Settings;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.Utilities.SingletonStarter;

public class SettingsActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SingletonStarter.getInstance(getApplicationContext());
        setContentView(R.layout.activity_settings);
    }
}
