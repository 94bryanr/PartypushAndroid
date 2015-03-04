package csu.bryanreilly.partypush.Network.Transactions;

import csu.bryanreilly.partypush.Network.NetworkManager;

//Communicates with databaseManager, handles all transactions
public class TransactionManager {
    public void updateUserInfo(){
        //Updates users info in DynamoDB
        NetworkManager.startTransaction("UpdateUserInfo");
    }
}