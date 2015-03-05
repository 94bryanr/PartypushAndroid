package csu.bryanreilly.partypush.UI.Main.Party.Detail;

import android.app.Activity;
import android.os.Bundle;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UserData.Party;
import csu.bryanreilly.partypush.UserData.UserAccount;

public class PartyDetailActivity extends Activity{
    //Party the activity is focusing on
    private Party party;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEV ACTIVITY: Party Detail Activity Started");
        setContentView(R.layout.main_party_detail_activity_partydetail);

        //Grab the intent, it has the index which the party in UserAccount Party[] was clicked.
        int partyIndex = getIntent().getIntExtra("index", -1);
        for(Party p: UserAccount.getUserAccount().getParties()){
            if (p.getIndex() == partyIndex){
                party = p;
            }
        }
        //If the party could not be found throw an exception
        if(party == null){
            try {
                throw new Exception("Party can not be found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setFragment();
    }

    private void setFragment(){
        getFragmentManager().beginTransaction().replace(R.id.container, new PartyDetailFragment()).commit();
    }

    public Party getParty(){
        return party;
    }
}
