package csu.bryanreilly.partypush.Network.AmazonDDB;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;

import csu.bryanreilly.partypush.UserData.AccountManager;

import java.util.HashMap;
import java.util.Map;

public class PutDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private boolean isComplete = false;
    private Map<String, AttributeValue> item; //For creating new items
    private Map<String, AttributeValueUpdate> update; // For updating existing items
    private String tableName;
    private putType type;
    private String id;

    //Create will erase the entry before writing, update will not
    public static enum putType{
        CREATE,
        UPDATE
    }

    public PutDatabaseItem(String tableName, putType type, String keyID){
        this.client = (AmazonDynamoDBClient)AccountManager.getDatabaseProvider();
        this.tableName = tableName;
        item = new HashMap<>();
        update = new HashMap<>();
        this.type = type;
        this.id = keyID;
    }

    public void sendItem(){
        new DatabaseThread().execute(this);
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
                key.put(id, new AttributeValue().withS(AccountManager.getId()));
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