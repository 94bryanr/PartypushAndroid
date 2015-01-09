package csu.bryanreilly.partypush.UI.Main.Friend.Create;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csu.bryanreilly.partypush.R;

public class CreateFriendFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.main_friend_create_fragment_createfriend, container, false);

        return rootView;
    }
}
