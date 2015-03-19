package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import csu.bryanreilly.partypush.UserData.AccountManager;

import java.util.HashMap;
import java.util.Map;

public class GetDatabaseItem implements DatabaseTransaction {
    private AmazonDynamoDBClient client;
    private String tableName;
    private boolean isComplete = false;
    Map<String, AttributeValue> key = new HashMap<>();
    GetItemResult result;

    public GetDatabaseItem(String ID, String tableName){
        this.client = (AmazonDynamoDBClient) AccountManager.getCognitoProvider();
        this.tableName = tableName;
        key.put("UserID", new AttributeValue().withS(ID));
    }

    public GetItemResult startTransaction(){
        new DatabaseThread().execute(this);
        return result;
    }

    @Override
    public void execute() {
        GetItemRequest getItemRequest = new GetItemRequest()
                .withTableName(tableName)
                .withKey(key);
        result = client.getItem(getItemRequest);
        Log.i("GetDBItem Retrieved", result.getItem().toString());
        setComplete();
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void setComplete() {
        isComplete = true;
    }

    @Override
    public String onComplete() {
        return "Get item successful";
    }

    public GetItemResult getResult(){
        return result;
    }
}
