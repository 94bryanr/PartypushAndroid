package csu.bryanreilly.partypush.UI.Main.Friends;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class FriendPickerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_picker);

        //Make friends names array
        String[] friendsNames = new String[AccountManager.getFriendsWithApp().size()];
        for(int i = 0; i < AccountManager.getFriendsWithApp().size(); i++){
            friendsNames[i] = AccountManager.getFriendsWithApp().get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                friendsNames);

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
            if(convertView==null)
            {
                LayoutInflater inflater = (LayoutInflater) FriendPickerActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row_friend_picker, parent,false);
            }

            Friend friend = friends.get(position);

            TextView friendName = (TextView)convertView.findViewById(R.id.friendName);
            friendName.setText(friend.getName());

            return convertView;
        }
    }
}