package csu.bryanreilly.partypush.UI.Main.Party.Create;

import android.app.Activity;
import android.os.Bundle;

import csu.bryanreilly.partypush.R;

public class CreatePartyActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEV ACTIVITY: Create Party Activity Started");
        setContentView(R.layout.main_party_create_activity_createparty);
        setFragment();
    }

    private void setFragment(){
        getFragmentManager().beginTransaction().replace(R.id.container, new CreatePartyFragment()).commit();
    }
}
