package csu.bryanreilly.partypush.UI.Main.Parties;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Party.Party;

public class PartyListAdapter extends BaseAdapter {
    //Custom list adapter for regular party list
    ArrayList<Party> parties = AccountManager.getParties();

    @Override
    public int getCount() {
            return parties.size();
        }

    @Override
    public Object getItem(int position) {
            return parties.get(position);
        }

    @Override
    public long getItemId(int position) {
            return position;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row_party, parent, false);
        }

        Party party = parties.get(position);

        TextView partyText = (TextView)convertView.findViewById(R.id.partyName);
        partyText.setText(party.getDescription());

        TextView partyLocation = (TextView)convertView.findViewById(R.id.partyLocation);
        partyLocation.setText(party.getLocationDescription());

        return convertView;
    }
}