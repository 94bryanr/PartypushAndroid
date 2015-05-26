package csu.bryanreilly.partypush.UI.Main.Parties;

//Katie is awesome

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.FacebookFragment;
import csu.bryanreilly.partypush.UserData.Party.PartyCallback;
import csu.bryanreilly.partypush.UserData.Party.PartyObserver;

public class MainPartiesFragment extends FacebookFragment implements PartyCallback{
    PartyListAdapter listAdapter;
    ListView partyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PartyObserver.addCallback(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_parties, container, false);
        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        //Set up the list view
        listAdapter = new PartyListAdapter();
        partyList = (ListView)getView().findViewById(R.id.partiesList);
        TextView emptyListMessage = (TextView)getView().findViewById(android.R.id.empty);
        partyList.setEmptyView(emptyListMessage);
        partyList.setAdapter(listAdapter);
    }

    public void onResume(){
        super.onResume();
        refreshPartiesList();
    }

    public void refreshPartiesList(){
        listAdapter = new PartyListAdapter();
        partyList.setAdapter(listAdapter);
    }

    @Override
    public void partiesChanged() {
        refreshPartiesList();
    }
}
