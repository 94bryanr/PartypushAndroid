package csu.bryanreilly.partypush.UI.Main.Friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.widget.FriendPickerFragment;
import csu.bryanreilly.partypush.Network.NetworkFragment;
import csu.bryanreilly.partypush.R;

public class MainFriendsFragment extends NetworkFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        return rootView;
    }
}
