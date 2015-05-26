package csu.bryanreilly.partypush.UserData.Friend;

import java.util.ArrayList;

public class FriendObserver {
    private static ArrayList<FriendCallback> callbacks = new ArrayList<>();

    public static void addCallback(FriendCallback callback){
        callbacks.add(callback);
    }

    public static void notifyFriendsChanged(){
        for(FriendCallback callback: callbacks){
            callback.friendsUpdated();
        }
    }
}
