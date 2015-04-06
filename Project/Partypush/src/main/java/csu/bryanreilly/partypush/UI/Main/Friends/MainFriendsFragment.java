package csu.bryanreilly.partypush.UI.Main.Friends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.FacebookFragment;
import csu.bryanreilly.partypush.UI.Main.MainActivity;
import csu.bryanreilly.partypush.UserData.Friend;

public class MainFriendsFragment extends FacebookFragment implements PropertyChangeListener {
    FriendListAdapter listAdapter;
    ListView friendsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        listAdapter = new FriendListAdapter();
        friendsList = (ListView)getView().findViewById(R.id.friendList);
        TextView emptyListMessage = (TextView)getView().findViewById(android.R.id.empty);
        friendsList.setEmptyView(emptyListMessage);
        friendsList.setAdapter(listAdapter);

        //Listener to handle list clicks
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend selectedFriend = (Friend)parent.getItemAtPosition(position);
                Log.i("FriendList Selection", selectedFriend.getName());
                openFriendDialogBox(selectedFriend);
            }
        };
        friendsList.setOnItemClickListener(listener);
    }

    public void onResume(){
        super.onResume();
        refreshFriendsList();
    }

    public void refreshFriendsList(){
        listAdapter = new FriendListAdapter();
        friendsList.setAdapter(listAdapter);
        Log.i("Friends List", "List Adapter Updated");
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        System.out.println("Changed property: " + event.getPropertyName() + " [old -> "
                + event.getOldValue() + "] | [new -> " + event.getNewValue() +"]");
    }

    private void openFriendDialogBox(final Friend friend){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    //Delete was pressed
                    Log.i("Deleted Friend", friend.getName());
                    Toast.makeText(
                            MainFriendsFragment.this.getActivity(),
                            "Deleted " + friend.getName(),
                            Toast.LENGTH_LONG)
                            .show();
                    TransactionManager.removeFriend(friend.getId());

                    //Update friends list
                    ((MainActivity)getActivity()).updateFriendsList();
                }
            }
        };

        builder.setItems(R.array.friend_dialog, listener)
                .setTitle(friend.getName());

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}