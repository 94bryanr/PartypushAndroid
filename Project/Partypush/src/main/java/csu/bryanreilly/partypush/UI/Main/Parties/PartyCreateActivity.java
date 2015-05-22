package csu.bryanreilly.partypush.UI.Main.Parties;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.UIManager;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.LocationManager;
import csu.bryanreilly.partypush.UserData.Party;
import csu.bryanreilly.partypush.Utilities.LocationGeocoder;

public class PartyCreateActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_create);
    }

    public void createClicked(View view){
        EditText descriptionBox = (EditText)findViewById(R.id.editTextDescription);
        EditText locationBox = (EditText)findViewById(R.id.editTextLocation);

        String description = descriptionBox.getText().toString().trim();
        String location = locationBox.getText().toString().trim();

        if(description.equals("") || location.equals("")){
            //Do nothing if the user entered no strings
            return;
        }

        LocationGeocoder locationGeocoder = new LocationGeocoder(location, LocationManager.getCurrentLocation());

        if(locationGeocoder.foundMatch()){
            //TODO: If more than one match show the user the top 5 matches
            Party party = new Party(description, location,
                    AccountManager.getId(), locationGeocoder.getMatch());
            TransactionManager.addParty(party);
            UIManager.returnToMain(this);
        }
        else{
            //No match found
            locationBox.setText("invalid");
            //TODO: What happens when no match is found?
        }
    }
}