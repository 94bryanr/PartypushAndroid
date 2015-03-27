package csu.bryanreilly.partypush.UI.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.widget.LoginButton;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.FacebookFragment;

import java.util.Arrays;

public class LoginFragment extends FacebookFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        //Allows the fragment, instead of the activity, to handle facebook state changes.
        LoginButton authButton = (LoginButton) rootView.findViewById(R.id.authButton);



        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("user_friends"));
    // this is the transition for the logo at the start of the app
        ImageView log_in = (ImageView) rootView.findViewById(R.id.log_in_header) ;
        log_in.animate().translationY(50.5f);
        log_in.animate().setDuration(2000).start();




        return rootView;
    }
}