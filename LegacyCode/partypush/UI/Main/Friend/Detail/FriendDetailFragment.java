package csu.bryanreilly.partypush.UI.Main.Friend.Detail;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.Party.Detail.PartyDetailActivity;

public class FriendDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.main_friend_detail_fragment_frienddetail, container, false);

        //Find view widgets
        TextView friendUsername = (TextView) rootView.findViewById(R.id.textViewFriendUsername);
        TextView friendPopularity = (TextView) rootView.findViewById(R.id.textViewFriendPopularity);

        //Edit view widgets
        friendUsername.setText(((FriendDetailActivity) this.getActivity()).getFriend().getUsername());
        friendPopularity.setText(((FriendDetailActivity) this.getActivity()).getFriend().getPopularity());

        return rootView;
    }
}
