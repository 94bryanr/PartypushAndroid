package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

import java.util.HashMap;
import java.util.Map;

import csu.bryanreilly.partypush.UserData.AccountManager;

public class AppendDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private String tableName;
    private String field;
    private String value;
    private boolean isComplete;
    private boolean checkForDuplicates;

    public AppendDatabaseItem(String tableName, String field, String value, boolean checkForDuplicates){
        this.client = (AmazonDynamoDBClient)AccountManager.getCognitoProvider();
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
            Log.i("Appending To", current);
        }
        catch (NullPointerException e){
            //Field did not exist, it will be created
            Log.i("Append", "Field did not exist, creating field.");
        }

        //Add value, then use put to update
        String appendedValue = current + value;
        if(checkForDuplicates)
            appendedValue = appendWithoutDuplicate(current, value);
        Log.i("After Append", appendedValue);

        PutDatabaseItem itemUpdater = new PutDatabaseItem(tableName, PutDatabaseItem.putType.UPDATE);
        itemUpdater.addField(field, appendedValue);
        itemUpdater.sendItem();
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
        //Check to see if toAppend exists in preAppend
        if(preAppend.contains(toAppend)){
            return preAppend;
        }
        else{
            return preAppend + toAppend;
        }
    }
}
