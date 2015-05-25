package csu.bryanreilly.partypush.Network;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.Transactions.AddFriends;
import csu.bryanreilly.partypush.Network.Transactions.AddParty;
import csu.bryanreilly.partypush.Network.Transactions.GetFriendsWithApp;
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
        GetFriendsWithApp.run();
    }

    public static void addFriends(ArrayList<Friend> friends){
        AddFriends.run(friends);
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
}