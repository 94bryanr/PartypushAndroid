package csu.bryanreilly.partypush.UI.Main.Friends;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.FacebookFragment;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class MainFriendsFragment extends FacebookFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView friendsList = (ListView)getView().findViewById(R.id.friendList);
        TextView emptyListMessage = (TextView)getView().findViewById(android.R.id.empty);
        friendsList.setEmptyView(emptyListMessage);
        friendsList.setAdapter(new FriendListAdapter());
    }

    //Custom list adapter for regular friend list
    public class FriendListAdapter extends BaseAdapter {
        ArrayList<Friend> friends = AccountManager.getAddedFriends();

        @Override
        public int getCount() {
            return friends.size();
        }

        @Override
        public Object getItem(int position) {
            return friends.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                LayoutInflater inflater = (LayoutInflater) MainFriendsFragment.super.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row_friend, parent, false);
            }

            Friend friend = friends.get(position);

            TextView friendName = (TextView)convertView.findViewById(R.id.friendName);
            friendName.setText(friend.getName());

            return convertView;
        }
    }
}
