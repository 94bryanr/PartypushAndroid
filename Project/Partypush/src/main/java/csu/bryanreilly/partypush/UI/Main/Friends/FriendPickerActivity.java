package csu.bryanreilly.partypush.UI.Main.Friends;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class FriendPickerActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_picker);
    }

    public void addButtonClick(View view) {
        //Add a friend by calling UserAccounts add method
        EditText textBox = (EditText)findViewById(R.id.IDTextBox);
        String ID = textBox.getText().toString();
        UserAccount.addFriend(ID);
    }
}