package csu.bryanreilly.partypush.Network.AmazonDDB;

public interface DatabaseTransaction {
    //Method executed by the AsyncTask in a seperate thread. Do not call from outside AsyncTask
    public void execute();
    //Log message printed upon completion of transaction
    public String onComplete();
    //Query to see if transaction is finished
    public boolean isComplete();
    //Set the transaction to be completed
    public void setComplete();
}
