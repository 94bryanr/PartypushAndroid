package csu.bryanreilly.partypush.Network.AmazonDDB;

public interface DatabaseTransaction {
    //Method executed by the AsyncTask in a seperate thread. Do not call from outside AsyncTask
    public void execute();
    //Log message printed upon completion of transaction
    public String completionMessage();
}
