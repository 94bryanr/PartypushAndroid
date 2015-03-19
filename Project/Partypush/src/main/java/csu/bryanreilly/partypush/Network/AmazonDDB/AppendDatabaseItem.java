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
    private boolean isComplete = false;

    public AppendDatabaseItem(String tableName, String field, String value){
        this.client = (AmazonDynamoDBClient)AccountManager.getCognitoProvider();
        this.tableName = tableName;
        this.field = field;
        this.value = value;
    }

    @Override
    public void execute() {
        //Get data that already exists in the field
        GetDatabaseItem currentField = new GetDatabaseItem(AccountManager.getId(), tableName);
        currentField.startTransaction();
        while(!currentField.isComplete()){
            //Wait
        }
        String current = currentField.getResult().getItem().get(field).getS();
        Log.i("Appending To", current);

        //Add value, then use put to update
        String appendedValue = current + value;
        //TODO: Thread exception
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
}
