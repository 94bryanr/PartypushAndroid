package csu.bryanreilly.partypush.UserData;

import android.app.Activity;
import android.util.Log;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.UI.UIManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static String firstName;
    private static String lastName;
    private static String name;
    private static String id;
    private static String username;
    private static String birthday;
    private static ArrayList<Friend> friendsWithApp;
    private static ArrayList<Friend> addedFriends;
    private static AmazonDynamoDB amazonDatabaseClient;
    private static boolean loggedInFacebook = false;

    public static void login(Activity callingActivity, Session callingSession){
        if(loggedInFacebook)
            return;

        friendsWithApp = new ArrayList<Friend>();
        addedFriends = new ArrayList<Friend>();
        Log.i("AccountManager", "Logging In");
        initializeCognito(callingActivity);
        UIManager.returnToMain(callingActivity);
        getFacebookUserInfo(callingSession, callingActivity);
        loggedInFacebook = true;
    }

    public static void logout(Activity callingActivity){
        if(!loggedInFacebook)
            return;
        Log.i("AccountManager", "Logout");
        loggedInFacebook = false;
        UIManager.returnToLogin(callingActivity);
    }

    private static void initializeCognito(Activity callingActivity){
        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
                callingActivity,
                "944513736710",
                "us-east-1:bb025c43-3e0a-443c-8af0-b4304394a441",
                //Unathenticated role (none)
                "YOUR UNAUTHENTICATED ARN HERE",
                //Authenticated role
                "arn:aws:iam::944513736710:role/Cognito_PartypushAuth_DefaultRole",
                Regions.US_EAST_1
        );
        Map<String, String> logins = new HashMap<>();
        logins.put("graph.facebook.com", Session.getActiveSession().getAccessToken());
        cognitoProvider.withLogins(logins);
        amazonDatabaseClient = new AmazonDynamoDBClient(cognitoProvider);
    }

    private static void getFacebookUserInfo(final Session session, final Activity callingActivity){
        Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                Log.i("fb", "fb user: " + user.toString());
                AccountManager.setFirstName(user.getFirstName());
                AccountManager.setLastName(user.getLastName());
                AccountManager.setBirthday(user.getBirthday());
                AccountManager.setName(user.getName());
                AccountManager.setId(user.getId());
                AccountManager.setUsername(user.getUsername());
                TransactionManager.updateUserInfo(callingActivity);
                TransactionManager.updateFriendsWithApp();
                TransactionManager.updateFriends();
            }
        });
    }

    public static AmazonDynamoDB getCognitoProvider(){
        return amazonDatabaseClient;
    }

    public static ArrayList<Friend> getAddedFriends(){
        return addedFriends;
    }

    public static void setFriendsWithApp(ArrayList<Friend> friends){
        friendsWithApp = friends;
    }

    public static void setAddedFriends(ArrayList<Friend> addedFriends) {
        AccountManager.addedFriends = addedFriends;
    }

    public static ArrayList<Friend> getFriendsWithApp(){
        return friendsWithApp;
    }

    public static void setFirstName(String firstName) {
        AccountManager.firstName = firstName;
    }

    public static void setBirthday(String birthday) {
        AccountManager.birthday = birthday;
    }

    public static void setLastName(String lastName) {
        AccountManager.lastName = lastName;
    }

    public static void setName(String name) {
        AccountManager.name = name;
    }

    public static void setId(String id) {
        AccountManager.id = id;
    }

    public static void setUsername(String username) {
        AccountManager.username = username;
    }

    public static String getName() {
        return name;
    }

    public static String getId() {
        return id;
    }
}
