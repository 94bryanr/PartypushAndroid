package csu.bryanreilly.partypush.UI.Main.Friend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.Friend;

public class FriendFragmentListAdapter extends ArrayAdapter<Friend> {
    //List data
    private final Context context;
    private final Friend[] friends;

    //Initializer
    public FriendFragmentListAdapter(Context context, Friend[] friends) {
        super(context, R.layout.main_friend_fragment_listrow, friends);
        this.context = context;
        this.friends = friends;
    }

    //Inflates each row of the list. Position is the row number.
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //LayoutInflater for inflating the row layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflating the row layout
        View rowView = inflater.inflate(R.layout.main_friend_fragment_listrow, parent, false);

        //Grabbing any views from the layout
        TextView textViewUsername = (TextView)rowView.findViewById(R.id.textViewUsername);
        TextView textViewPopularity = (TextView)rowView.findViewById(R.id.textViewPopularity);

        //Editing the views
        textViewUsername.setText(friends[position].getUsername());
        textViewPopularity.setText(friends[position].getPopularity());

        //Return the edited row
        return rowView;
    }
}
