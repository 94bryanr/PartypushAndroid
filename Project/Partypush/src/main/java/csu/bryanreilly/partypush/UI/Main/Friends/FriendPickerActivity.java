package csu.bryanreilly.partypush.UI.Main.Friends;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class FriendPickerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_picker);
        final TextView text = (TextView)findViewById(R.id.textViewFriends);

        //TODO: Make this data visible
        /* make the API call */
        new Request(
                Session.getActiveSession(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        text.setText(response.toString());
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