package csu.bryanreilly.partypush.UI.Main.Party;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.MainActivity;
import csu.bryanreilly.partypush.UI.Main.Party.Detail.PartyDetailActivity;
import csu.bryanreilly.partypush.UserData.Party;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class PartyFragment extends ListFragment {
    //Instance of user account singleton, for party info.
    UserAccount userAccount = UserAccount.getUserAccount();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.main_party_fragment_party, container, false);

        //Sends party info to adapter for row inflation.
        PartyFragmentListAdapter adapter = new PartyFragmentListAdapter(getActivity(), userAccount.getParties());
        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        Party party = (Party)this.getListAdapter().getItem(position);
        ((MainActivity)(this.getActivity())).StartActivity(PartyDetailActivity.class, party.getIndex());
    }
}
