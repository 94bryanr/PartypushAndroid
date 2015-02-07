package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import csu.bryanreilly.partypush.Network.NetworkFragment;

import java.util.HashMap;
import java.util.Map;

public class PutDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private Map<String, AttributeValue> item;
    private String tableName;

    public PutDatabaseItem(String tableName){
        this.client = (AmazonDynamoDBClient)NetworkFragment.getAmazonDatabaseClient();
        this.tableName = tableName;
        item = new HashMap<String, AttributeValue>();
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
        item = new HashMap<String, AttributeValue>();
    }

    public void addField(String field, String value){
        if(!value.isEmpty()) {
            item.put(field, new AttributeValue().withS(value));
        }
    }

    //Method executed by the AsyncTask in a seperate thread. Do not call from outside AsyncTask
    public void execute(){
        PutItemRequest itemRequest = new PutItemRequest().withTableName(tableName).withItem(item);
        client.putItem(itemRequest);
    }

    @Override
    public String completionMessage() {
        return "Put item successful";
    }
}
