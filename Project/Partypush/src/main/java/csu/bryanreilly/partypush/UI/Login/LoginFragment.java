package csu.bryanreilly.partypush.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.widget.LoginButton;
import csu.bryanreilly.partypush.Network.Facebook.FacebookFragment;
import csu.bryanreilly.partypush.Network.Facebook.FacebookFragmentManager;
import csu.bryanreilly.partypush.R;

public class LoginFragment extends FacebookFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookFragmentManager.onCreateUpdate(getActivity(), savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        //Allows the fragment, instead of the activity, to handle facebook state changes.
        LoginButton authButton = (LoginButton) rootView.findViewById(R.id.authButton);
        authButton.setFragment(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        FacebookFragmentManager.onResumeUpdate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookFragmentManager.onActivityResultUpdate(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        FacebookFragmentManager.onPauseUpdate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FacebookFragmentManager.onDestroyUpdate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FacebookFragmentManager.onSaveInstanceStateUpdate(outState);
    }
}