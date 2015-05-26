package csu.bryanreilly.partypush.UserData;

import android.app.Activity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.UIManager;
import csu.bryanreilly.partypush.UserData.Party.Party;
import csu.bryanreilly.partypush.UserData.Party.PartyObserver;
import csu.bryanreilly.partypush.Utilities.ResourceGetter;

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
        loggedInToFacebook = true;
        initializeAccountData();
        initializeDatabaseClient(callingActivity);
        updateAccountInfoWithFacebookInfo(callingSession, callingActivity);
        UIManager.returnToMain(callingActivity);
    }

    private static void initializeAccountData(){
        friendsWithApp = new ArrayList<Friend>();
        addedFriends = new ArrayList<Friend>();
        parties = new ArrayList<Party>();
    }

    private static void initializeDatabaseClient(Activity callingActivity){
        ResourceGetter res = new ResourceGetter(callingActivity);
        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
                callingActivity,
                res.getString(R.string.amazon_cognito_account_id),
                res.getString(R.string.amazon_cognito_identity_pool_id),
                res.getString(R.string.amazon_cognito_authenticated_role_arn),
                res.getString(R.string.amazon_cognito_authenticated_role_arn),
                Regions.US_EAST_1
        );
        Map<String, String> logins = new HashMap<>();
        logins.put(res.getString(R.string.amazon_cognito_facebook_login_provider_name),
                Session.getActiveSession().getAccessToken());
        cognitoProvider.withLogins(logins);
        amazonDatabaseClient = new AmazonDynamoDBClient(cognitoProvider);
    }

    private static void updateAccountInfoWithFacebookInfo(final Session session, final Activity callingActivity){
        Request.newMeRequest(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                AccountManager.setName(user.getName());
                AccountManager.setId(user.getId());
                runLoginDatabaseUpdates(callingActivity);
            }
        }).executeAsync();
    }

    private static void runLoginDatabaseUpdates(Activity callingActivity){
        TransactionManager.updateUserInfo(callingActivity);
        TransactionManager.updateFriendsWithApp();
        TransactionManager.updateFriends();
    }

    public static void attemptLogout(Activity callingActivity){
        if(loggedInToFacebook)
            logout(callingActivity);
    }

    private static void logout(Activity callingActivity){
        loggedInToFacebook = false;
        new AccountLogoutService().execute(callingActivity);
    }

    public static AmazonDynamoDB getDatabaseProvider(){return amazonDatabaseClient;}

    public static void setParties(ArrayList<Party> parties) {
        AccountManager.parties = parties;
        PartyObserver.notifyPartiesChanged();
    }

    public static ArrayList<Party> getParties() {return parties;}

    public static void setAddedFriends(ArrayList<Friend> addedFriends) {
        AccountManager.addedFriends = addedFriends;
        // Parties need to be updated after updating friends list
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