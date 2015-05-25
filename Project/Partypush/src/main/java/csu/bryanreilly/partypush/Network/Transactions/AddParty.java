package csu.bryanreilly.partypush.Network.Transactions;
import android.util.Log;

import csu.bryanreilly.partypush.Network.AmazonDDB.AppendDatabaseItem;
import csu.bryanreilly.partypush.Network.AmazonDDB.PutDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.Party.Party;

public class AddParty {
    public static void run(Party party) {
        //Append the party id to user
        String toAppend = party.getId() + ",";
        AppendDatabaseItem appendDatabaseItem = new AppendDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_PARTIES,
                toAppend,
                true,
                Constants.USER_DATABASE_ID);
        appendDatabaseItem.execute();

        //Add the party info to the party database
        Log.i("PARTIES", "TransAdd: " + party.getId() + " " + party.getDescription() + " " + party.getLocationDescription());
        PutDatabaseItem partyItem = new PutDatabaseItem(Constants.PARTY_DATABASE, PutDatabaseItem.putType.CREATE, Constants.PARTY_DATABASE_ID);
        partyItem.addField(Constants.PARTY_DATABASE_ID, party.getId());
        partyItem.addField(Constants.PARTY_DATABASE_NAME, party.getDescription());
        partyItem.addField(Constants.PARTY_DATABASE_LOCATION, party.getLocationDescription());
        partyItem.addField(Constants.PARTY_DATABASE_LONGITUDE, Double.toString(party.getLocation().longitude));
        partyItem.addField(Constants.PARTY_DATABASE_LATITUDE, Double.toString(party.getLocation().latitude));
        partyItem.sendItem();
    }
}