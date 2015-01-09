package csu.bryanreilly.partypush.UI.Main.Friend;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.Friend.Detail.FriendDetailActivity;
import csu.bryanreilly.partypush.UI.Main.MainActivity;
import csu.bryanreilly.partypush.UserData.Friend;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class FriendFragment extends ListFragment {
    //Instance of user account singleton, for friend info.
    UserAccount userAccount = UserAccount.getUserAccount();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.main_friend_fragment_friend, container, false);

        //Sends party info to adapter for row inflation.
        FriendFragmentListAdapter adapter = new FriendFragmentListAdapter(getActivity(), userAccount.getFriends());
        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        Friend friend = (Friend)this.getListAdapter().getItem(position);
        ((MainActivity)(this.getActivity())).StartActivity(FriendDetailActivity.class, friend.getIndex());
    }
}
