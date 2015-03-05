package csu.bryanreilly.partypush.UI.Main.Friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.FacebookFragment;

public class MainFriendsFragment extends FacebookFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        return rootView;
    }
}
