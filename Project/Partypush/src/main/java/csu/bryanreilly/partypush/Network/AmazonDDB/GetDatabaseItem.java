package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import csu.bryanreilly.partypush.Network.NetworkFragment;

import java.util.HashMap;
import java.util.Map;

public class GetDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private String tableName;
    Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
    GetItemResult result;

    public GetDatabaseItem(String id, String tableName){
        this.client = (AmazonDynamoDBClient)NetworkFragment.getAmazonDatabaseClient();
        this.tableName = tableName;
        key.put("UserID", new AttributeValue().withS(id));
    }

    public void getItem(){
        if(NetworkFragment.isLoggedIn()){
            new DatabaseThread().execute(this);
        }
        else{
            Log.i("Network", "Must be logged in to access database");
        }
    }

    @Override
    public void execute() {
        GetItemRequest getItemRequest = new GetItemRequest()
                .withTableName(tableName)
                .withKey(key);
        result = client.getItem(getItemRequest);
        Log.i("Item", result.getItem().toString());
    }

    @Override
    public String completionMessage() {
        return "Get item successful";
    }
}
