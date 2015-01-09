package csu.bryanreilly.partypush.UI.Main.Party.Detail;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import csu.bryanreilly.partypush.R;

public class PartyDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.main_party_detail_fragment_partydetail, container, false);

        //Find view widgets
        TextView partyScore = (TextView) rootView.findViewById(R.id.textViewPartyScore);
        TextView partyDistance = (TextView) rootView.findViewById(R.id.textViewPartyDistance);


        //Edit view widgets
        partyScore.setText(((PartyDetailActivity) this.getActivity()).getParty().getScore());
        partyDistance.setText(((PartyDetailActivity) this.getActivity()).getParty().getDistance());

        return rootView;
    }
}
