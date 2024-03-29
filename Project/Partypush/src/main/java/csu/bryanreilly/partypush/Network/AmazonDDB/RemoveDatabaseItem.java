package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.util.Log;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class RemoveDatabaseItem implements DatabaseTransaction {
    private String tableName;
    private String field;
    private String value;
    private String keyID;
    private String userID;
    private boolean isComplete;

    public RemoveDatabaseItem(String tableName, String field, String value, String keyID){
        this.tableName = tableName;
        this.field = field;
        this.value = value;
        this.keyID = keyID;
        this.userID = AccountManager.getId();
        isComplete = false;
    }

    public RemoveDatabaseItem(String tableName, String field, String value, String keyID, String userID){
        this.tableName = tableName;
        this.field = field;
        Log.i("TransactionManager", "Constructor Value To Remove: " + value);
        this.value = value;
        this.keyID = keyID;
        this.userID = userID;
        isComplete = false;
    }

    @Override
    public void execute() {
        //Get data that already exists in the field
        String current = "";
        try {
            GetDatabaseItem currentFieldRetriever = new GetDatabaseItem(userID, tableName, keyID);
            currentFieldRetriever.startTransaction();
            while(!currentFieldRetriever.isComplete()){
                //Wait for sever answer
            }
            current = currentFieldRetriever.getResult().getItem().get(field).getS();
            Log.i("TransactionManager", "Removing: " + value);
            Log.i("TransactionManager", "Removing From: " + current);
        }
        catch (NullPointerException e){
            //Field did not exist, it will be created
            Log.i("TransactionManager", "Field did not exist, stopping");
            setComplete();
            return;
        }

        //Remove value, then use put to update
        String removedValue = removeValue(current, value);
        Log.i("TransactionManager", "Value After Remove: " + removedValue);
        // Set remove value to "EMPTY," instead of an empty string
        // because database will not update the field if the update
        // value is empty
        if (removedValue == ""){
            removedValue = "EMPTY,";
        }

        PutDatabaseItem itemUpdater = new PutDatabaseItem(tableName, PutDatabaseItem.putType.UPDATE, keyID, userID);
        itemUpdater.addField(field, removedValue);
        itemUpdater.sendItem();
        setComplete();
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