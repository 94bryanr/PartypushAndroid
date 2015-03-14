package csu.bryanreilly.partypush.UI.Main.Friends;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

        TextView textBox = (TextView)findViewById(R.id.textViewFriends);
        String display = "";
        for(Friend friend : AccountManager.getFriendsWithApp()){
            display += friend.getName() + " ";
        }
        textBox.setText(display);
    }
}