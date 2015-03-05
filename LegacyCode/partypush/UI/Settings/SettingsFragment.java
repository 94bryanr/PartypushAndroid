package csu.bryanreilly.partypush.UI.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class SettingsFragment extends Fragment{
    //For facebook login debugging purposes
    private static final String TAG = "LoginFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.settings_fragment_settings, container, false);

        //Find view widgets
        TextView currentUsername = (TextView) rootView.findViewById(R.id.textViewCurrentUsername);
        TextView currentPopularity = (TextView) rootView.findViewById(R.id.textViewCurrentPopularity);

        //Edit view widgets
        currentUsername.setText(UserAccount.getUserAccount().getName());
        currentPopularity.setText(Integer.toString(UserAccount.getUserAccount().getPopularity()));

        //For monitoring facebook login state change
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);

        AssignButtonListeners(rootView);
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private void AssignButtonListeners(View rootView) {
        LoginButton authButton = (LoginButton)rootView.findViewById(R.id.authButton);
        authButton.setFragment(this);
    }
}
