package csu.bryanreilly.partypush.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class FacebookFragment extends Fragment{
    //Facebook's UILifecycleHelper must be called upon every lifecycle change
    private UiLifecycleHelper status;

    /* Listens for Facebook state changes, always passed in to a UILifecycleHelper */
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session,state,exception);
        }
    };

    // Called when the user logs in or out of Facebook
    public void onSessionStateChange(Session session, SessionState state, Exception exception) {
        Log.i("Facebook Fragment", "onSessionStateChange called");
        if (state.isOpened()) {
            AccountManager.login(getActivity(), session);
        } else if (state.isClosed()) {
            AccountManager.logout(getActivity());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = new UiLifecycleHelper(this.getActivity(), callback);
        status.onCreate(savedInstanceState);
    }

    @Override
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        status.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause(){
        super.onPause();
        status.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        status.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        status.onSaveInstanceState(outState);
    }
}