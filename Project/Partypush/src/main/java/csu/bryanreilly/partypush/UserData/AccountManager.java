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
    private static String name;
    private static String id;
    private static ArrayList<Friend> friendsWithApp;
    private static ArrayList<Friend> addedFriends;
    private static AmazonDynamoDB amazonDatabaseClient;
    private static boolean loggedInToFacebook = false;
    private static ArrayList<Party> parties;

    public static void attemptLogin(Activity callingActivity, Session callingSession){
        if(!loggedInToFacebook)
            login(callingActivity, callingSession);
    }

    private static void login(Activity callingActivity, Session callingSession){
        initializeAccountData();
        initializeDatabaseClient(callingActivity);
        UIManager.returnToMain(callingActivity);
        updateAccountInfoWithFacebookInfo(callingSession, callingActivity);
        loggedInToFacebook = true;
    }

    private static void initializeAccountData(){
        friendsWithApp = new ArrayList<Friend>();
        addedFriends = new ArrayList<Friend>();
        parties = new ArrayList<Party>();
    }

    public static void attemptLogout(Activity callingActivity){
        if(!loggedInToFacebook)
            logout(callingActivity);
    }

    private static void logout(Activity callingActivity){
        loggedInToFacebook = false;
        UIManager.returnToLogin(callingActivity);
    }

    private static void initializeDatabaseClient(Activity callingActivity){
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

    private static void updateAccountInfoWithFacebookInfo(final Session session, final Activity callingActivity){
        Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                AccountManager.setName(user.getName());
                AccountManager.setId(user.getId());
                runLoginDatabaseUpdates(callingActivity);
            }
        });
    }

    private static void runLoginDatabaseUpdates(Activity callingActivity){
        TransactionManager.updateUserInfo(callingActivity);
        TransactionManager.updateFriendsWithApp();
        TransactionManager.updateFriends();
    }

    public static AmazonDynamoDB getCognitoProvider(){return amazonDatabaseClient;}

    public static void setParties(ArrayList<Party> parties) {AccountManager.parties = parties;}

    public static ArrayList<Party> getParties() {return parties;}

    public static void setAddedFriends(ArrayList<Friend> addedFriends) {
        AccountManager.addedFriends = addedFriends;
        // Parties need to be updated after getting friends list
        TransactionManager.getParties();
    }

    public static ArrayList<Friend> getAddedFriends(){return addedFriends;}

    public static void setFriendsWithApp(ArrayList<Friend> friends){friendsWithApp = friends;}

    public static ArrayList<Friend> getFriendsWithApp(){return friendsWithApp;}

    public static void setName(String name) {AccountManager.name = name;}

    public static String getName() {return name;}

    public static void setId(String id) {AccountManager.id = id;}

    public static String getId() {return id;}
}