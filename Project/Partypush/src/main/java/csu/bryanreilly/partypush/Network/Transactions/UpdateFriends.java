package csu.bryanreilly.partypush.Network.Transactions;

import android.util.Log;

import com.amazonaws.AmazonServiceException;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.AmazonDDB.GetDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UI.UIManager;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class UpdateFriends {
    public static void run(){
        GetDatabaseItem friends = new GetDatabaseItem(AccountManager.getId(), Constants.USER_DATABASE);
        String result = "";

        // Get list of friends
        try {
            friends.startTransaction();
            while (!friends.isComplete()) {
                //Wait
            }
            result = friends.getResult().getItem().get(Constants.USER_DATABASE_FRIENDS).getS();
        }
        catch (NullPointerException e){
            // No Friends
        }
        String[] friendIDList = result.split(",");

        //Get info for each friend
        ArrayList<Friend> friendsList = new ArrayList<Friend>();
        for (String friendID : friendIDList){
            Log.i("Updating Friend", friendID);
            if(friendID != "") {
                GetDatabaseItem friendInfo = new GetDatabaseItem(friendID, Constants.USER_DATABASE);
                try {
                    friendInfo.startTransaction();
                    while (!friendInfo.isComplete()) {
                        //Wait
                    }
                    String friendName = friendInfo.getResult().getItem().get(Constants.USER_DATABASE_NAME).getS();
                    friendsList.add(new Friend(friendName, friendID));
                } catch (NullPointerException e) {
                    // No Friends
                    Log.e("UpdateFriends", "Friend does not exist");
                }
            }
        }

        AccountManager.setAddedFriends(friendsList);
        UIManager.refreshFriendsListScreen();
    }
}
