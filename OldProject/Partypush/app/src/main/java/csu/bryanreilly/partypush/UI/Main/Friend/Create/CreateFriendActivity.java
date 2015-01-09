package csu.bryanreilly.partypush.UI.Main.Friend.Create;

import android.app.Activity;
import android.os.Bundle;
import csu.bryanreilly.partypush.R;

public class CreateFriendActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEV ACTIVITY: Create Friend Activity Started");
        setContentView(R.layout.main_friend_create_activity_createfriend);
        setFragment();
    }

    private void setFragment(){
        getFragmentManager().beginTransaction().replace(R.id.container, new CreateFriendFragment()).commit();
    }
}