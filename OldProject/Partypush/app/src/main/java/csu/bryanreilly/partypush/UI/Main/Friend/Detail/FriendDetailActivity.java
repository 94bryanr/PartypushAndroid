package csu.bryanreilly.partypush.UI.Main.Friend.Detail;

import android.app.Activity;
import android.os.Bundle;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.Friend.Create.CreateFriendFragment;
import csu.bryanreilly.partypush.UserData.Friend;
import csu.bryanreilly.partypush.UserData.Party;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class FriendDetailActivity extends Activity {
    //Friend the activity is focusing on
    private Friend friend;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEV ACTIVITY: Friend Detail Activity Started");
        setContentView(R.layout.main_friend_detail_activity_frienddetail);
        setFragment();

        //Grab the intent, it has the index which the party in UserAccount Party[] was clicked.
        int friendIndex = getIntent().getIntExtra("index", -1);
        for(Friend p: UserAccount.getUserAccount().getFriends()){
            if (p.getIndex() == friendIndex){
                friend = p;
            }
        }
        //If the party could not be found throw an exception
        if(friend == null){
            try {
                throw new Exception("Friend can not be found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setFragment();
    }

    private void setFragment(){
        getFragmentManager().beginTransaction().replace(R.id.container, new FriendDetailFragment()).commit();
    }

    public Friend getFriend() {
        return friend;
    }
}
