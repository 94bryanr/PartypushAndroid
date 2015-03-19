package csu.bryanreilly.partypush.Network.Transactions;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.AmazonDDB.AppendDatabaseItem;
import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.Friend;

public class AddFriends {
    public static void run(ArrayList<Friend> friends){
        for(Friend friend : friends){
            String toAppend = friend.getId() + ",";
            AppendDatabaseItem appendDatabaseItem = new AppendDatabaseItem(
                    Constants.USER_DATABASE,
                    Constants.USER_DATABASE_FRIENDS,
                    toAppend,
                    true);
            appendDatabaseItem.execute();
        }
        TransactionManager.updateFriends();
    }
}
