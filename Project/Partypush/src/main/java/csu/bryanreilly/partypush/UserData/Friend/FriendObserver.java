package csu.bryanreilly.partypush.UserData.Friend;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.TransactionManager;

public class FriendObserver {
    private static ArrayList<FriendCallback> callbacks = new ArrayList<>();

    public static void addCallback(FriendCallback callback){
        callbacks.add(callback);
    }

    public static void notifyFriendsChanged(){
        for(FriendCallback callback: callbacks){
            callback.friendsUpdated();
        }
        // Update the parties every time the friends list is updated
        TransactionManager.getParties();
    }
}
