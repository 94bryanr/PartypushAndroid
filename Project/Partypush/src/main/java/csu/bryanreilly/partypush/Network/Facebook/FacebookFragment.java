package csu.bryanreilly.partypush.Network.Facebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import csu.bryanreilly.partypush.UI.Login.LoginActivity;

public abstract class FacebookFragment extends Fragment{
    /* Listens for Facebook state changes, always passed in to a UILifecycleHelper */
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session,state,exception);
        }
    };

    //getActivity null
    private UiLifecycleHelper status;

    public void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i("Facebook", getActivity() + ": Logged In");
        } else if (state.isClosed()) {
            Log.i("Facebook", getActivity() + ": Logged Out");
            //If logged out, return to login screen
            returnToLogin();
        }
    }

    protected void returnToLogin(){
        Intent startLoginActivity = new Intent(getActivity(), LoginActivity.class);
        //Clears the backstack
        startLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startLoginActivity);
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
}
