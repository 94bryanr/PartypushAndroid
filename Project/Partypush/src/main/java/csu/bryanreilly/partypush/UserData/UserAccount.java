package csu.bryanreilly.partypush.UserData;

import android.os.AsyncTask;
import android.util.Log;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import csu.bryanreilly.partypush.Network.AmazonDDB.GetDatabaseItem;
import csu.bryanreilly.partypush.Network.AmazonDDB.PutDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserAccount {
    private static String firstName;
    private static String lastName;
    private static String name;
    private static String id;
    private static String username;
    private static String birthday;

    //Holds all of the users known parties
    private Party[] parties;
    //Holds all of the users known friends
    private Friend[] friends;

    public UserAccount() {
        //Get friends
    }

    public static void addFriend(String id){
        //Add a friend to list
        //Update Friends field on amazon db
        //Refresh friends list
    }

    public static void setFirstName(String firstName) {
        UserAccount.firstName = firstName;
    }

    public static void setBirthday(String birthday) {
        UserAccount.birthday = birthday;
    }

    public static void setLastName(String lastName) {
        UserAccount.lastName = lastName;
    }

    public static void setName(String name) {
        UserAccount.name = name;
    }

    public static void setId(String id) {
        UserAccount.id = id;
    }

    public static void setUsername(String username) {
        UserAccount.username = username;
    }

    public static String getName() {
        return name;
    }

    public static String getId() {
        return id;
    }

    public static void login(){
        //Update/Create user information in database.
        Log.i("Running Login", "UserAccount");

        //Must user executeOnExecutor because this task calls other AsyncTasks
        new UpdateUserInfo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public static void logout(){
        //TODO: Implement this, refactor login to do the reverse
        Log.i("UserAccount", "Logout");
    }
}
