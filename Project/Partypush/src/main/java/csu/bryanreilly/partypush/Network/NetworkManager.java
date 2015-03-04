package csu.bryanreilly.partypush.Network;

import csu.bryanreilly.partypush.Network.Transactions.TransactionManager;

//Handles all Networking, communicates with the rest of the app.
public class NetworkManager {
    public static void startTransaction(String transaction){
        //Start a transaction using TransactionManager
        TransactionManager transactionManager = new TransactionManager();
        switch (transaction){
            case "UpdateUserInfo":
                transactionManager.updateUserInfo();
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
