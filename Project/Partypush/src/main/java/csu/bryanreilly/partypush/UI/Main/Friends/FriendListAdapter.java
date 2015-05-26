package csu.bryanreilly.partypush.UI.Main.Friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend.Friend;
import csu.bryanreilly.partypush.Utilities.ContextGetter;

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
        if(convertView==null){
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row_friend, parent, false);
        }

        Friend friend = friends.get(position);

        TextView friendName = (TextView)convertView.findViewById(R.id.friendName);
        friendName.setText(friend.getName());
        TextView friendStatus = (TextView)convertView.findViewById(R.id.statusTextView);
        friendStatus.setText(friend.getCurrentStatusString());

        Button acceptButton = (Button)convertView.findViewById(R.id.accept_button);
        Button declineButton = (Button)convertView.findViewById(R.id.decline_button);
        setClickListeners(acceptButton, declineButton, friend);

        setLayout(friend.getCurrentStatus(), acceptButton, declineButton, friendStatus);


        return convertView;
    }

    private void setClickListeners(Button acceptButton, Button declineButton, final Friend friend){
        acceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                acceptClicked(v, friend);
            }
        });
        declineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                declineClicked(v, friend);
            }
        });
    }

    private void acceptClicked(View view, Friend friend){
        TransactionManager.acceptFriendRequest(friend);
    }

    private void declineClicked(View view, Friend friend){
        TransactionManager.declineFriendRequest(friend);
    }

    private void setLayout(Friend.STATUS status, Button acceptButton,
                           Button declineButton, TextView friendStatus){
        if(status == Friend.STATUS.USER_ADDED){
            try {
                friendStatus.setTextColor(ContextGetter.getInstance().getContext().getResources()
                        .getColor(R.color.yellow_text));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(status == Friend.STATUS.FRIEND){
            try {
                friendStatus.setTextColor(ContextGetter.getInstance().getContext().getResources()
                        .getColor(R.color.green_text));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(status != Friend.STATUS.OTHER_ADDED){
            // Remove buttons
            acceptButton.setVisibility(View.INVISIBLE);
            declineButton.setVisibility(View.INVISIBLE);
        }
    }
}