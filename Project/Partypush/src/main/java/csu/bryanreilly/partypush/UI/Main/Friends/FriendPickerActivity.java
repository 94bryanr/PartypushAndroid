package csu.bryanreilly.partypush.UI.Main.Friends;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.FragmentInfo;
import csu.bryanreilly.partypush.UI.UIManager;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class FriendPickerActivity extends FragmentActivity {
    ArrayList<Friend> selectedFriends = new ArrayList<Friend>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_picker);

        //Make friends names array
        String[] friendsNames = new String[AccountManager.getFriendsWithApp().size()];
        for(int i = 0; i < AccountManager.getFriendsWithApp().size(); i++){
            friendsNames[i] = AccountManager.getFriendsWithApp().get(i).getName();
        }

        ListView friendsList = (ListView)findViewById(R.id.friendsListView);
        friendsList.setAdapter(new FriendPickerListAdapter());
    }


    //Custom list adapter for friend picker list
    public class FriendPickerListAdapter extends BaseAdapter {
        ArrayList<Friend> friends = AccountManager.getFriendsWithApp();

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
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) FriendPickerActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row_friend_picker, parent, false);
            }

            Friend friend = friends.get(position);

            TextView friendName = (TextView) convertView.findViewById(R.id.friendName);
            friendName.setText(friend.getName());

            return convertView;
        }
    }

    public void addClicked(View view){
        for(Friend friend: selectedFriends){
            Log.i("Adding Friend", friend.getName());
        }
        TransactionManager.sendFriendRequests(selectedFriends);
        UIManager.returnToMain(this, FragmentInfo.FriendsFragment);
    }

    public void checkClicked(View view){
        //Find the position in the listview
        ListView friendsList = (ListView)findViewById(R.id.friendsListView);
        int position = friendsList.getPositionForView(view);

        if( ((CheckBox)view).isChecked() ){
            //User checked the entry
            Friend friend = (Friend)friendsList.getAdapter().getItem(position);
            selectedFriends.add(friend);
        }
        else{
            //User unchecked the entry
            Friend friend = (Friend)friendsList.getAdapter().getItem(position);
            selectedFriends.remove(friend);
        }
    }
}