package csu.bryanreilly.partypush.Network.Transactions;

import android.util.Log;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.AmazonDDB.GetDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class UpdateFriends {
    public static void run(){
        GetDatabaseItem friends = new GetDatabaseItem(AccountManager.getId(),
                Constants.USER_DATABASE, Constants.USER_DATABASE_ID);
        String result = "";

        // Get list of friends
        try {
            friends.startTransaction();
            while (!friends.isComplete()) {
                //Wait
            }
            result = friends.getResult().getItem().get(Constants.USER_DATABASE_FRIENDS).getS();
            Log.i("TransactionManager", result);
        }
        catch (NullPointerException e){
            // No Friends
        }
        String[] friendIDList = result.split(",");

        ArrayList<Friend> friendsList = getFriendObjects(friendIDList);
        AccountManager.setAddedFriends(friendsList);
    }

    private static String removeFriendType(String friend){
        String friendNoType = friend;
        if(friend.endsWith("_S") || friend.endsWith("_R")){
            friendNoType = friend.substring(0, friend.length()-2);
        }
        return friendNoType;
    }

    private static ArrayList<Friend> getFriendObjects(String[] friendIDList){
        //Get info for each friend
        ArrayList<Friend> friendsList = new ArrayList<Friend>();
        for (String friendID : friendIDList){
            if(friendID.trim() != "") {
                GetDatabaseItem friendInfo = new GetDatabaseItem(removeFriendType(friendID),
                        Constants.USER_DATABASE, Constants.USER_DATABASE_ID);
                try {
                    friendInfo.startTransaction();
                    while (!friendInfo.isComplete()) {
                        // Wait
                    }
                    String friendName = friendInfo.getResult().getItem().get(Constants.USER_DATABASE_NAME).getS();
                    Log.i("TransactionManager", "Friend Name: " + friendName);
                    friendsList.add(new Friend(friendName, friendID));
                } catch (NullPointerException e) {
                    // No Friends
                    Log.e("UpdateFriends", "Friend does not exist");
                }
            }
        }
        return friendsList;
    }
}
