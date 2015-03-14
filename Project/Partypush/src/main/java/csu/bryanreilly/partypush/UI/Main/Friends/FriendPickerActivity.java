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

public class FriendPickerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_picker);
        final TextView text = (TextView)findViewById(R.id.textViewFriends);
        final Map map;
        final ArrayList<String> names = new ArrayList<String>();

        //TODO: Make this data visible
        /* make the API call */
        new Request(
                Session.getActiveSession(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        //Get friend data
                        try {
                            JSONArray friendArray = response.getGraphObject().getInnerJSONObject().getJSONArray("data");
                            for (int friendIndex = 0; friendIndex < friendArray.length(); friendIndex++){
                                Log.i("Friend", friendArray.getJSONObject(friendIndex).getString("name"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    public void addButtonClick(View view) {
        //Add a friend by calling UserAccounts add method
        EditText textBox = (EditText)findViewById(R.id.IDTextBox);
        String ID = textBox.getText().toString();
        AccountManager.addFriend(ID);
    }
}