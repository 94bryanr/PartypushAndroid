package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.UserAccount;

import java.util.HashMap;
import java.util.Map;

public class PutDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private boolean isComplete = false;
    private Map<String, AttributeValue> item; //For creating new items
    private Map<String, AttributeValueUpdate> update; // For updating existing items
    private String tableName;

    public static enum putType{
        CREATE,
        UPDATE
    }
    private putType type;

    public PutDatabaseItem(String tableName, putType type){
        this.client = (AmazonDynamoDBClient)NetworkFragment.getAmazonDatabaseClient();
        this.tableName = tableName;
        item = new HashMap<>();
        update = new HashMap<>();
        this.type = type;
    }

    public void sendItem(){
        if(NetworkFragment.isLoggedIn()){
            new DatabaseThread().execute(this);
        }
        else{
            Log.i("Network", "Must be logged in to access database");
        }
    }

    public void clearFields(){
        item = new HashMap<>();
    }

    public void addField(String field, String value){
        if(!value.isEmpty()) {
            item.put(field, new AttributeValue().withS(value));
            update.put(field, new AttributeValueUpdate().withValue(new AttributeValue().withS(value)));
        }
    }

    //Method executed by the AsyncTask in a separate thread. Do not call from outside AsyncTask
    public void execute(){
        switch(type){
            case CREATE:
                PutItemRequest putItemRequest = new PutItemRequest()
                        .withTableName(tableName)
                        .withItem(item);
                client.putItem(putItemRequest);
                break;
            case UPDATE:
                Map<String, AttributeValue> key = new HashMap<>();
                key.put(Constants.USER_DATABASE_ID, new AttributeValue().withS(UserAccount.getId()));
                UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                        .withKey(key)
                        .withTableName(tableName)
                        .withAttributeUpdates(update);
                client.updateItem(updateItemRequest);
                break;
        }
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void setComplete(){
        isComplete = true;
    }

    @Override
    public String onComplete() {
        return "Put item successful";
    }
}
