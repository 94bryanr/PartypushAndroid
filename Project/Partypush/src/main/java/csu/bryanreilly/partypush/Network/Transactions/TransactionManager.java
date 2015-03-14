package csu.bryanreilly.partypush.Network.Transactions;

import android.app.Activity;
import android.os.AsyncTask;

//Communicates with databaseManager, handles all transactions
public class TransactionManager {
    public void updateUserInfo(Activity callingActivity){
        //Updates users info in DynamoDB
        new UpdateUserInfo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, callingActivity);
    }

    public void getFriendsWithApp(){
        GetFriendsWithApp.makeCall();
    }
}