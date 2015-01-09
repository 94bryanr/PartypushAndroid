package csu.bryanreilly.partypush.UI.Main.Party;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.Party;

public class PartyFragmentListAdapter extends ArrayAdapter<Party> {
    //List data
    private final Context context;
    private final Party[] values;

    //Initializer
    public PartyFragmentListAdapter(Context context, Party[] parties) {
        super(context, R.layout.main_party_fragment_listrow, parties);
        this.context = context;
        this.values = parties;
    }

    //Inflates each row of the list. Position is the row number.
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //LayoutInflater for inflating the row layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflating the row layout
        View rowView = inflater.inflate(R.layout.main_party_fragment_listrow, parent, false);

        //Grabbing any views from the layout
        TextView textViewScore = (TextView)rowView.findViewById(R.id.textViewScore);
        TextView textViewDistance = (TextView)rowView.findViewById((R.id.textViewDistance));

        //Editing the views
        textViewScore.setText(values[position].getScore());
        textViewDistance.setText(values[position].getDistance());

        //Return the edited row
        return rowView;
    }
}
