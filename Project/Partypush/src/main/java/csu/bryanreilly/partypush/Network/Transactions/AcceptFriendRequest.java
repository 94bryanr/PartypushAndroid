package csu.bryanreilly.partypush.Network.Transactions;

import csu.bryanreilly.partypush.Network.AmazonDDB.AppendDatabaseItem;
import csu.bryanreilly.partypush.Network.AmazonDDB.RemoveDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class AcceptFriendRequest {
    public AcceptFriendRequest(Friend friend){
        String idToAccept = friend.getId();

        // Remove the id_R in users friends list
        RemoveDatabaseItem removeRequest = new RemoveDatabaseItem(Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                idToAccept+"_R,",
                Constants.USER_DATABASE_ID);
        removeRequest.execute();
        while(!removeRequest.isComplete()){
            // Wait
        }

        // Add friends id to users friend list
        AppendDatabaseItem appendUserDatabaseItem = new AppendDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                idToAccept + ",",
                true,
                Constants.USER_DATABASE_ID);
        appendUserDatabaseItem.execute();
        while(!appendUserDatabaseItem.isComplete()){
            // Wait
        }

        // Remove the id_S from friends friend list
        RemoveDatabaseItem cleanFriend = new RemoveDatabaseItem(Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                AccountManager.getId()+"_S,",
                Constants.USER_DATABASE_ID,
                idToAccept);
        cleanFriend.execute();
        while(!cleanFriend.isComplete()) {
            // Wait
        }

        // Add users id to senders friend list
        AppendDatabaseItem appendFriendsDatabaseItem = new AppendDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                AccountManager.getId() + ",",
                true,
                Constants.USER_DATABASE_ID,
                idToAccept);
        appendFriendsDatabaseItem.execute();
    }
}
