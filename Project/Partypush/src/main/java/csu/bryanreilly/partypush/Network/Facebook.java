package csu.bryanreilly.partypush.Network;

import android.util.Log;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

//Class to handle all Facebook usage.
public class Facebook {
    /* Listens for Facebook state changes, always passed in to a UILifecycleHelper */
    private Session.StatusCallback callback = new Session.StatusCallback() {
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
    private UiLifecycleHelper uiHelper;

    //Called via a Session.StatusCallback when Facebook state changes(logged in to logged out or vice versa).
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i("Facebook", "Logged In");
        } else if (state.isClosed()) {
            Log.i("Facebook", "Logged Out");
        }
    }
}
