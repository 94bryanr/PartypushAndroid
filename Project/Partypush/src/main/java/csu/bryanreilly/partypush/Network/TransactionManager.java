package csu.bryanreilly.partypush.Network;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.Transactions.AddFriends;
import csu.bryanreilly.partypush.Network.Transactions.GetFriendsWithApp;
import csu.bryanreilly.partypush.Network.Transactions.UpdateUserInfo;
import csu.bryanreilly.partypush.UserData.Friend;

public class TransactionManager {
    public static void updateUserInfo(Activity callingActivity){
        new UpdateUserInfo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, callingActivity);
    }

    public static void getFriendsWithApp(){
        GetFriendsWithApp.makeCall();
    }

    public static void addFriends(ArrayList<Friend> friends){
        AddFriends.run(friends);
    }

    public static void updateFriends(){
        //Todo: Get this to work
    }
}