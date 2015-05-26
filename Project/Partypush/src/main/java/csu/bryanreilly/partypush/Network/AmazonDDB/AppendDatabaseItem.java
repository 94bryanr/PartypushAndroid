package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class AppendDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private String tableName;
    private String field;
    private String value;
    private String keyID;
    private String userID;
    private boolean isComplete;
    private boolean checkForDuplicates;

    public AppendDatabaseItem(String tableName, String field, String value,
                              boolean checkForDuplicates, String keyID){
        Log.i("TransactionManager", "Appending Database Item Constructor");
        this.tableName = tableName;
        this.field = field;
        this.value = value;
        isComplete = false;
        this.checkForDuplicates = checkForDuplicates;
        this.keyID = keyID;
        this.userID = AccountManager.getId();
    }

    public AppendDatabaseItem(String tableName, String field, String value,
                              boolean checkForDuplicates, String keyID, String userID){
        Log.i("TransactionManager", "Appending Database Item Constructor");
        this.tableName = tableName;
        this.field = field;
        this.value = value;
        isComplete = false;
        this.checkForDuplicates = checkForDuplicates;
        this.keyID = keyID;
        this.userID = userID;
    }

    @Override
    public void execute() {
        //Get data that already exists in the field
        String current = "";
        try {
            GetDatabaseItem currentFieldRetriever = new GetDatabaseItem(userID, tableName, keyID);
            Log.i("TransactionManager", "Appending Database Item");
            currentFieldRetriever.startTransaction();
            while(!currentFieldRetriever.isComplete()){
                //Wait for sever answer
            }
            current = currentFieldRetriever.getResult().getItem().get(field).getS();
            Log.i("TransactionManager", "Append Current Field: " + current);
        }
        catch (NullPointerException e){
            //Field did not exist, it will be created
        }

        //Add value, then use put to update
        String appendedValue = current + value;
        if(checkForDuplicates)
            appendedValue = appendWithoutDuplicate(current, value);

        PutDatabaseItem itemUpdater = new PutDatabaseItem(tableName,
                PutDatabaseItem.putType.UPDATE, keyID, userID);
        itemUpdater.addField(field, appendedValue);
        itemUpdater.sendItem();
        setComplete();
    }

    @Override
    public String onComplete() {
        return "Append item successful";
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void setComplete() {
        isComplete = true;
    }

    private String appendWithoutDuplicate(String preAppend, String toAppend){
        //Check to see if the value to add is already contained in the list
        if(preAppend.contains(toAppend)){
            return preAppend;
        }
        else{
            return preAppend + toAppend;
        }
    }
}