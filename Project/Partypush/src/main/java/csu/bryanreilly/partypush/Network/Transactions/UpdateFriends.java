package csu.bryanreilly.partypush.Network.Transactions;

import android.util.Log;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.AmazonDDB.GetDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend.Friend;

public class UpdateFriends {
    public static void run(){
        if(nullData())
            return;

        GetDatabaseItem getFriends = new GetDatabaseItem(AccountManager.getId(),
                Constants.USER_DATABASE, Constants.USER_DATABASE_ID);

        String result = getFriendList(getFriends);
        String[] friendIDList = result.split(",");

        ArrayList<Friend> friendsList = getFriendsList(friendIDList);
        AccountManager.setAddedFriends(friendsList);
    }

    private static boolean nullData(){
        return AccountManager.getId() == null;
    }

    private static String getFriendList(GetDatabaseItem getFriends){
        String result = "";
        try {
            getFriends.startTransaction();
            while (!getFriends.isComplete()) {
                //Wait
            }
            result = getFriends.getResult().getItem().get(Constants.USER_DATABASE_FRIENDS).getS();
            Log.i("TransactionManager", result);
        }
        catch (NullPointerException e){
            // No Friends
        }
        return result;
    }

    private static ArrayList<Friend> getFriendsList(String[] friendIDList){
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

    private static String removeFriendType(String friend){
        String friendNoType = friend;
        if(friend.endsWith("_S") || friend.endsWith("_R")){
            friendNoType = friend.substring(0, friend.length()-2);
        }
        return friendNoType;
    }
}
