package csu.bryanreilly.partypush.UI.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.Friend;
import csu.bryanreilly.partypush.UserData.Party;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class LoginFragment extends Fragment{
    //Handles logging in, allows user to begin creating or recovering accounts.

    //FragmentSwitcher allows the fragment to change the activities current displayed fragment
    private FragmentSwitcher fragmentSwitcher;

    //For facebook login debugging purposes
    private static final String TAG = "LoginFragment";

    //Passed to UiLifecycleHelper to monitor for facebook login changes
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    //Listens for facebook login state change. Calls onSessionStateChange when state changes
    private UiLifecycleHelper uiHelper;

    @Override
    public void onResume() {
        super.onResume();
        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.login_fragment_login, container, false);

        //For monitoring facebook login state change
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);

        AssignButtonListeners(rootView);
        return rootView;
    }

    //Updates active facebook session
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this.getActivity(), requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        //Grabs the fragment switcher from the host activity.
        try{
            fragmentSwitcher = (FragmentSwitcher) activity;
        } catch (ClassCastException castException){
            //The activity does not implement a fragment switcher.
            System.out.println("Activity does not implement fragment switcher");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private void AssignButtonListeners(View rootView){
        //Assigns each button on the view to the click listener.
        //This allows the buttons to trigger onClick() when they are pressed.
        LoginButton authButton = (LoginButton) rootView.findViewById(R.id.authButton);
        authButton.setFragment(this);
    }

    //Handles Login Button Click
    private void Login(){
        ((LoginActivity)getActivity()).StartMainActivity();
        //TODO: Login to AWS
        //UserAccount.getUserAccount().amazonLogin(this.getActivity().getApplicationContext());
    }

    //Called when the user logs in or logs out with facebook
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (UserAccount.getUserAccount().facebookLogin(session, state, exception) == true){
            Login();
            //TODO: Throwing an exception when calling login from here
        }
    }
}