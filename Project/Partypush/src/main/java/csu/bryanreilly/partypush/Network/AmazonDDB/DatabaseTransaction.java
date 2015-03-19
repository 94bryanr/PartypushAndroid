package csu.bryanreilly.partypush.Network.AmazonDDB;

import com.amazonaws.services.dynamodbv2.model.GetItemResult;

public interface DatabaseTransaction {
    //Method executed by the AsyncTask in a separate thread. Do not call from outside AsyncTask
    public void execute();
    //Log message printed upon completion of transaction
    public String onComplete();
    //Query to see if transaction is finished
    public boolean isComplete();
    //Set the transaction to be completed
    public void setComplete();
}
