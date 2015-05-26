package csu.bryanreilly.partypush.Network.Transactions;

import csu.bryanreilly.partypush.Network.AmazonDDB.AppendDatabaseItem;
import csu.bryanreilly.partypush.Network.AmazonDDB.RemoveDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.Friend;

public class AcceptFriendRequest {
    AcceptFriendRequest(Friend friend){
        String idToAccept = friend.getId();

        // Remove the id_R in users friends list
        RemoveDatabaseItem removeRequest = new RemoveDatabaseItem(Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS, idToAccept+"_R", Constants.USER_DATABASE_ID);
        // Add friends id to users friend list
        AppendDatabaseItem appendUserDatabaseItem = new AppendDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                idToAccept + ",",
                true,
                Constants.USER_DATABASE_ID);
        appendUserDatabaseItem.execute();

        // Remove the id_S from friends friend list
        // Add users id to senders friend list
    }
}
