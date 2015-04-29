package csu.bryanreilly.partypush.Network.Transactions;
import csu.bryanreilly.partypush.Network.AmazonDDB.AppendDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.Party;

public class AddParty {
    public static void run(Party party) {
        //Append the party id to user
        String toAppend = party.getId() + ",";
        AppendDatabaseItem appendDatabaseItem = new AppendDatabaseItem(
                Constants.USER_DATABASE,
                Constants.USER_DATABASE_FRIENDS,
                toAppend,
                true,
                Constants.USER_DATABASE_ID);
        appendDatabaseItem.execute();

        //Add the party info to the party database
        //TODO: Finish this
    }
}
