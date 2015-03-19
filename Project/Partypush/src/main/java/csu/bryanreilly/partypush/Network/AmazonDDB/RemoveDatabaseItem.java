package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class RemoveDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private String tableName;
    private String field;
    private String value;
    private boolean isComplete;
    private boolean checkForDuplicates;

    public RemoveDatabaseItem(String tableName, String field, String value, boolean checkForDuplicates){
        this.tableName = tableName;
        this.field = field;
        this.value = value;
        isComplete = false;
        this.checkForDuplicates = checkForDuplicates;
    }

    @Override
    public void execute() {
        //Get data that already exists in the field
        String current = "";
        try {
            GetDatabaseItem currentFieldRetriever = new GetDatabaseItem(AccountManager.getId(), tableName);
            currentFieldRetriever.startTransaction();
            while(!currentFieldRetriever.isComplete()){
                //Wait for sever answer
            }
            current = currentFieldRetriever.getResult().getItem().get(field).getS();
            Log.i("Removing From", current);
        }
        catch (NullPointerException e){
            //Field did not exist, it will be created
            Log.i("Database Remove", "Field did not exist, stopping");
            setComplete();
            return;
        }

        //Remove value, then use put to update
        String removedValue = removeValue(current, value);
        Log.i("After Remove", removedValue);

        PutDatabaseItem itemUpdater = new PutDatabaseItem(tableName, PutDatabaseItem.putType.UPDATE);
        itemUpdater.addField(field, removedValue);
        itemUpdater.sendItem();
    }

    private String removeValue(String baseString, String toRemove){
        if(baseString.contains(toRemove)){
            //Remove
            int startIndex = baseString.indexOf(toRemove);
            int endIndex = startIndex + toRemove.length();
            String finalString =
                    baseString.substring(0, startIndex) +
                    baseString.substring(endIndex, baseString.length());
            return finalString;
        }
        else{
            //String does not exist in baseString
            return baseString;
        }
    }

    @Override
    public String onComplete() {
        return "Remove item successful";
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void setComplete() {
        isComplete = true;
    }
}