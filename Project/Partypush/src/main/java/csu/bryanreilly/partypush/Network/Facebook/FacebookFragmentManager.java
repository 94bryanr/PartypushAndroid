package csu.bryanreilly.partypush.Network.Facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.Session;

/*Class to handle all Facebook usage.
 *Each fragment must call the corresponding methods for
 *onCreate(), onResume(), onPause(), onDestroy(), onActivityResult() and onSaveInstanceState()*/
public class FacebookFragmentManager {
    public static void onCreateUpdate(Activity activity, Bundle savedInstanceState){
        FacebookHandler.initializeUiHelper(activity);
        FacebookHandler.getUiHelper().onCreate(savedInstanceState);
    }

    public static void onResumeUpdate(){
        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            FacebookHandler.onSessionStateChange(session, session.getState(), null);
        }
        FacebookHandler.getUiHelper().onResume();
    }

    public static void onActivityResultUpdate(int requestCode, int resultCode, Intent data){
        FacebookHandler.getUiHelper().onActivityResult(requestCode, resultCode, data);
    }

    public static void onPauseUpdate(){
        FacebookHandler.getUiHelper().onPause();
    }

    public static void onDestroyUpdate(){
        FacebookHandler.getUiHelper().onDestroy();
    }

    public static void onSaveInstanceStateUpdate(Bundle outState){
        FacebookHandler.getUiHelper().onSaveInstanceState(outState);
    }
}
