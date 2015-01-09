package csu.bryanreilly.partypush.Network.Facebook;

import android.app.Activity;
import android.util.Log;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

//Contains basic Facebook variables and date structures
public class FacebookHandler {
    /* Listens for Facebook state changes, always passed in to a UILifecycleHelper */
    private static Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    /* To ensure that the sessions are set up correctly, your fragment must override the
     fragment lifecycle methods: onCreate(), onResume(), onPause(), onDestroy(), onActivityResult()
     and onSaveInstanceState() and call the corresponding UiLifecycleHelper methods. For example,
     calling the onCreate() method in the UiLifecycleHelper object creates the Facebook session and
     opens it automatically if a cached token is available.
     Initialize in onCreate() method.*/
    private static UiLifecycleHelper uiHelper;

    //Called via a Session.StatusCallback when Facebook state changes(logged in to logged out or vice versa).
    public static void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i("Facebook", "Logged In");
        } else if (state.isClosed()) {
            Log.i("Facebook", "Logged Out");
            //If logged out, return to login screen
            returnToLogin();
        }
    }

    private static void returnToLogin(){
        //TODO: Fill out this method(return to the login activity whenever the user is logged out)
    }

    public static void initializeUiHelper(Activity activity){
        uiHelper = new UiLifecycleHelper(activity, callback);
    }

    public static UiLifecycleHelper getUiHelper() {
        return uiHelper;
    }
}
