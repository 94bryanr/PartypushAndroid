package csu.bryanreilly.partypush.Network;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.Transactions.SendFriendRequests;
import csu.bryanreilly.partypush.Network.Transactions.AddParty;
import csu.bryanreilly.partypush.Network.Transactions.GetFacebookFriendsWithApp;
import csu.bryanreilly.partypush.Network.Transactions.GetParties;
import csu.bryanreilly.partypush.Network.Transactions.RemoveFriend;
import csu.bryanreilly.partypush.Network.Transactions.UpdateFriends;
import csu.bryanreilly.partypush.Network.Transactions.UpdateUserInfo;
import csu.bryanreilly.partypush.UserData.Friend;
import csu.bryanreilly.partypush.UserData.Party.Party;

public class TransactionManager {
    public static void updateUserInfo(Activity callingActivity){
        new UpdateUserInfo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, callingActivity);
    }

    public static void updateFriendsWithApp(){
        GetFacebookFriendsWithApp.run();
    }

    public static void sendFriendRequests(ArrayList<Friend> friends){
        SendFriendRequests.run(friends);
    }

    public static void addParty(Party party){
        AddParty.run(party);
    }

    public static void updateFriends(){
        UpdateFriends.run();
    }

    public static void removeFriend(String id){
        RemoveFriend.run(id);
    }

    public static void getParties(){
        GetParties parties = new GetParties();
        parties.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public static void acceptFriendRequest(Friend friend){
        Log.i("TransactionManager", "Accept Request " + friend.getName());
    }

    public static void declineFriendRequest(Friend friend){
        Log.i("TransactionManager", "Decline Request " + friend.getName());
    }
}