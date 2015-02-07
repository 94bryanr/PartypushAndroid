package csu.bryanreilly.partypush.Network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.facebook.*;
import com.facebook.model.GraphUser;
import csu.bryanreilly.partypush.UI.Login.LoginActivity;
import csu.bryanreilly.partypush.UI.Main.MainActivity;
import csu.bryanreilly.partypush.UserData.UserAccount;

import java.util.HashMap;
import java.util.Map;

public abstract class NetworkFragment extends Fragment{
    private CognitoCachingCredentialsProvider cognitoProvider;
    private static AmazonDynamoDB amazonDatabaseClient;
    private static boolean facebookLoggedIn = false;
    private UiLifecycleHelper status;

    /* Listens for Facebook state changes, always passed in to a UILifecycleHelper */
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session,state,exception);
        }
    };

    public void onSessionStateChange(Session session, SessionState state, Exception exception) {
        Log.i("Facebook", "onSessionStateChange called");
        if (state.isOpened()) {
            if(facebookLoggedIn == false){
                //Facebook state changed from previous state.
                facebookLoggedIn = true;
                getFacebookUserInfo(session);
            }
        } else if (state.isClosed()) {
            if(facebookLoggedIn == true){
                //Facebook state changed from previous state.
                facebookLoggedIn = false;
                facebookSignOut();
            }

        }
    }

    private void getFacebookUserInfo(Session session){
        Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                Log.i("fb", "fb user: " + user.toString());
                UserAccount.setFirstName(user.getFirstName());
                UserAccount.setLastName(user.getLastName());
                UserAccount.setBirthday(user.getBirthday());
                UserAccount.setName(user.getName());
                UserAccount.setId(user.getId());
                UserAccount.setUsername(user.getUsername());
                facebookSignIn();
            }
        });
    }

    private void facebookSignIn(){
        Log.i("Facebook", getActivity() + ": Logged In, " + UserAccount.getName());
        initializeCognito();
        returnToMain();
    }

    private void facebookSignOut(){
        Log.i("Facebook", getActivity() + ": Logged Out");
        //If logged out, return to login screen
        returnToLogin();
    }

    private void initializeCognito(){
        cognitoProvider = new CognitoCachingCredentialsProvider(
                this.getActivity(), // get the context for the current activity
                "944513736710",
                "us-east-1:bb025c43-3e0a-443c-8af0-b4304394a441",
                //Unathenticated role (none)
                "YOUR UNAUTHENTICATED ARN HERE",
                //Authenticated role
                "arn:aws:iam::944513736710:role/Cognito_PartypushAuth_DefaultRole",
                Regions.US_EAST_1
        );

        Map<String, String> logins = new HashMap<String, String>();
        logins.put("graph.facebook.com", Session.getActiveSession().getAccessToken());
        cognitoProvider.withLogins(logins);

        amazonDatabaseClient = new AmazonDynamoDBClient(cognitoProvider);
    }

    protected void returnToLogin(){
        Intent startLoginActivity = new Intent(getActivity(), LoginActivity.class);
        //Clears the backstack
        startLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startLoginActivity);
    }

    protected void returnToMain(){
        Intent startMainActivity = new Intent(getActivity(), MainActivity.class);
        //Clears the backstack
        startMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMainActivity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = new UiLifecycleHelper(this.getActivity(), callback);
        status.onCreate(savedInstanceState);
    }

    public void onResume(){
        super.onResume();
        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }
        status.onResume();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        status.onActivityResult(requestCode, resultCode, data);
    }

    public void onPause(){
        super.onPause();
        status.onPause();
    }

    public void onDestroy(){
        super.onDestroy();
        status.onDestroy();
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        status.onSaveInstanceState(outState);
    }

    public static AmazonDynamoDB getAmazonDatabaseClient() {
        return amazonDatabaseClient;
    }

    public static boolean isLoggedIn(){
        if(facebookLoggedIn){
            return true;
        }
        return false;
    }
}
