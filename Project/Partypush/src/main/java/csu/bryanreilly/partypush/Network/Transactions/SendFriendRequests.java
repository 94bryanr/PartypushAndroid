package csu.bryanreilly.partypush.Network.Transactions;

import android.util.Log;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.AmazonDDB.AppendDatabaseItem;
import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class SendFriendRequests {
    public static void run(ArrayList<Friend> friends){
        for(Friend friend : friends){
            if (alreadyAdded(friend)) {
                Log.i("TransactionManager", "Friend Added Already!");
                continue;
            }

            // Append friend to users list
            String toAppend = friend.getId() + "_S,";
            AppendDatabaseItem appendUserDatabaseItem = new AppendDatabaseItem(
                    Constants.USER_DATABASE,
                    Constants.USER_DATABASE_FRIENDS,
                    toAppend,
                    true,
                    Constants.USER_DATABASE_ID);
            appendUserDatabaseItem.execute();

            // Append user to friends lists
            toAppend = AccountManager.getId() + "_R,";
            AppendDatabaseItem appendFriendDatabaseItem = new AppendDatabaseItem(
                    Constants.USER_DATABASE,
                    Constants.USER_DATABASE_FRIENDS,
                    toAppend,
                    true,
                    Constants.USER_DATABASE_ID,
                    friend.getId());
            appendFriendDatabaseItem.execute();
        }

        TransactionManager.updateFriends();
    }

    private static boolean alreadyAdded(Friend friend) {
        for (Friend previousFriend : AccountManager.getAddedFriends()) {
            if (friend.getId() == previousFriend.getId()) {
                return false;
            }
        }
        return true;
    }
}
