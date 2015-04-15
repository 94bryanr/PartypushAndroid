package csu.bryanreilly.partypush.Network.Transactions;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import csu.bryanreilly.partypush.Network.AmazonDDB.GetDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;
import csu.bryanreilly.partypush.UserData.Party;

public class GetParties extends AsyncTask<Void, Void, ArrayList<Party>> {
    @Override
    protected ArrayList<Party> doInBackground(Void... params) {

        ArrayList<GetDatabaseItem> queries = new ArrayList<GetDatabaseItem>();
        Log.i("PARTIES", "Executing");
        for (Friend friend : AccountManager.getAddedFriends()) {
            String userID = friend.getId();
            if (userID.trim() == "")
                break;
            Log.i("PARTIES", "Checking User " + userID);
            GetDatabaseItem getUserInfo = new GetDatabaseItem(userID, Constants.USER_DATABASE);
            getUserInfo.startTransaction();
            while (!getUserInfo.isComplete()) {

            }
            Log.i("PARTIES", "Complete");
            queries.add(getUserInfo);
        }

        ArrayList<Party> parties = new ArrayList<Party>();

        return parties;
    }

    @Override
    protected void onPostExecute(ArrayList<Party> parties) {
        AccountManager.setParties(parties);
    }
}