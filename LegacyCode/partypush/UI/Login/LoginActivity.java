package csu.bryanreilly.partypush.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import csu.bryanreilly.partypush.Database.DatabaseHandler;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.MainActivity;
import csu.bryanreilly.partypush.UserData.Friend;
import csu.bryanreilly.partypush.UserData.Party;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class LoginActivity extends FragmentActivity implements FragmentSwitcher {

    //User Account
    UserAccount user;

    //For communicating with database
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEV ACTIVITY: Login Activity Started");
        db = DatabaseHandler.getDataBaseHandler();
        user = UserAccount.getUserAccount();
        setContentView(R.layout.login_activity_login);
        if (savedInstanceState == null) {
            ChangeCurrentFragmentTo(new LoginFragment(), false);
        }
    }

    @Override
    public void ChangeCurrentFragmentTo(Fragment fragment, boolean addToBackStack){
        if(addToBackStack)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack("Stack").commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void StartMainActivity(){
        Intent startMainActivity = new Intent(this, MainActivity.class);
        // These flags stop the app from adding the activity to the back stack.
        startMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startMainActivity);
    }
}
