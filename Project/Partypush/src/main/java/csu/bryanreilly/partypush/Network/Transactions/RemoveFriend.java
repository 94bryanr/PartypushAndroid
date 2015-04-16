package csu.bryanreilly.partypush.Network.Transactions;

import csu.bryanreilly.partypush.Network.AmazonDDB.RemoveDatabaseItem;
import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.Program.Constants;

public class RemoveFriend {
    public static void run(String id){
        RemoveDatabaseItem removeFriend = new RemoveDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                id + ",",
                Constants.USER_DATABASE_ID);
        removeFriend.execute();
        TransactionManager.updateFriends();
    }
}
