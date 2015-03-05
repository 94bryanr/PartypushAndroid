package csu.bryanreilly.partypush.Network;

import android.app.Activity;
import csu.bryanreilly.partypush.Network.Transactions.TransactionManager;

//Handles all Networking, communicates with the rest of the app.
public class NetworkManager {
    public static void startTransaction(String transaction, Activity callingActivity){
        //Start a transaction using TransactionManager
        TransactionManager transactionManager = new TransactionManager();
        switch (transaction){
            case "UpdateUserInfo":
                transactionManager.updateUserInfo(callingActivity);
                break;
            default:
                try {
                    throw new Exception("Invalid Transaction Name");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
        }
    }
}
