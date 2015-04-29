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
import csu.bryanreilly.partypush.UserData.Party;

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

        Party party = new Party(description, location, AccountManager.getId());
        Log.i("PARTIES" , "ADDING");
        TransactionManager.addParty(party);
        //UIManager.returnToMain(this);
    }
}