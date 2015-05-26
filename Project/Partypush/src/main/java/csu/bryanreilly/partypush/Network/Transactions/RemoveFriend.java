package csu.bryanreilly.partypush.Network.Transactions;

import android.util.Log;

import csu.bryanreilly.partypush.Network.AmazonDDB.RemoveDatabaseItem;
import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class RemoveFriend {
    public static void run(String id){
        removeFriendFromUserList(id);
        removeFriendFromUserList(id+"_R");
        removeFriendFromUserList(id+"_S");
        removeUserFromOthersList(id, AccountManager.getId());
        removeUserFromOthersList(id, AccountManager.getId()+ "_R");
        removeUserFromOthersList(id, AccountManager.getId()+ "_S");
        TransactionManager.updateFriends();
    }

    public static void removeFriendFromUserList(String id){
        RemoveDatabaseItem removeFriend = new RemoveDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                id + ",",
                Constants.USER_DATABASE_ID);
        removeFriend.execute();
    }

    public static void removeUserFromOthersList(String id, String toRemove){
        RemoveDatabaseItem removeFriend = new RemoveDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                toRemove + ",",
                Constants.USER_DATABASE_ID,
                id);
        removeFriend.execute();
    }
}
