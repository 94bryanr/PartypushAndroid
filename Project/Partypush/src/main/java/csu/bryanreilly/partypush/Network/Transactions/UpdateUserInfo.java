package csu.bryanreilly.partypush.Network.Transactions;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.facebook.Session;
import com.facebook.SessionState;
import csu.bryanreilly.partypush.Network.AmazonDDB.GetDatabaseItem;
import csu.bryanreilly.partypush.Network.AmazonDDB.PutDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;
import csu.bryanreilly.partypush.UserData.AccountManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

//Class takes a String - UserID as an argument.
//Updates the users information in the database.
//MUST USE .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getId()); because this task calls other AsyncTasks

public class UpdateUserInfo extends AsyncTask<Activity, Void, Activity>{
    private boolean timeout = false;
    @Override
    protected Activity doInBackground(Activity... params) {
        GetDatabaseItem loginInfo = new GetDatabaseItem(AccountManager.getId(), Constants.USER_DATABASE);
        loginInfo.startTransaction();

        //Wait for result to return from the database
        int databaseTimeoutSeconds = Constants.DATABASE_TIMEOUT_SECONDS;
        while(!loginInfo.isComplete()){
            //Times out after specified time
            if(databaseTimeoutSeconds > 0) {
                try {
                    Thread.sleep(1000);
                    databaseTimeoutSeconds--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("Error", "UpdateUserInfo timeout handler interrupted");
                }
            }
            else{
                //Database Timed Out, log user out
                Log.i("UpdateUserInfo", "Database Timeout");
                timeout = true;
                //Break out of task
                return params[0];
            }
        }
        GetItemResult result = loginInfo.getResult();

        //Retrieved result, finishing update/create
        try {
            String stringResult = result.getItem().toString();
        } catch (NullPointerException e){
            //stringResult is null, no user exists
            Log.i("New Login", "Creating Account in Database");
            PutDatabaseItem addUser = new PutDatabaseItem(Constants.USER_DATABASE, PutDatabaseItem.putType.CREATE);
            addUser.addField(Constants.USER_DATABASE_ID, AccountManager.getId());
            addUser.sendItem();
        }

        //Update User Info
        Calendar calendar = new GregorianCalendar();
        PutDatabaseItem updateUser = new PutDatabaseItem(Constants.USER_DATABASE, PutDatabaseItem.putType.UPDATE);
        updateUser.addField(Constants.USER_DATABASE_NAME, AccountManager.getName());
        String date =
                Integer.toString(calendar.get(Calendar.MONTH) + 1) + "-" +
                        Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "-" +
                        Integer.toString(calendar.get(Calendar.YEAR)) + " (" +
                        Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":" +
                        Integer.toString(calendar.get(Calendar.MINUTE)) + ")";
        updateUser.addField(Constants.USER_DATABASE_DATE, date);
        updateUser.sendItem();
        return params[0];
    }

    protected void onPostExecute(Activity param){
        //Log the user out
        if (timeout) {
            Session session = Session.getActiveSession();
            session.closeAndClearTokenInformation();
        }
    }
}