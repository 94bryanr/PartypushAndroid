package csu.bryanreilly.partypush.Network.AmazonDDB;

import android.os.AsyncTask;
import android.util.Log;

//<Params -     Type of Parameters Sent to the Task upon Execution,
// Progress -   Type of progress units published during background computation,
// Result -     The type of the result of the background computation>
public class DatabaseThread extends AsyncTask<DatabaseTransaction, Void, Void>{
//    When an asynchronous task is executed, the task goes through 4 steps:
//
//    onPreExecute(), invoked on the UI thread before the task is executed.
//      This step is normally used to setup the task, for instance by showing a progress bar in the user interface.

//    doInBackground(Params...), invoked on the background thread immediately after onPreExecute() finishes executing.
//      This step is used to perform background computation that can take a long time.
//      The parameters of the asynchronous task are passed to this step.
//      The result of the computation must be returned by this step and will be passed back to the last step.
//      This step can also use publishProgress(Progress...) to publish one or more units of progress.
//      These values are published on the UI thread, in the onProgressUpdate(Progress...) step.

//    onProgressUpdate(Progress...), invoked on the UI thread after a call to publishProgress(Progress...).
//      The timing of the execution is undefined. This method is used to display any form of progress in the
//      user interface while the background computation is still executing. For instance, it can be used to
//      animate a progress bar or show logs in a text field.

//    onPostExecute(Result), invoked on the UI thread after the background computation finishes.
//      The result of the background computation is passed to this step as a parameter.

    // Run with new WhateverTask().execute(param1, param2, param3);

    private String completionMessage;

    @Override
    protected Void doInBackground(DatabaseTransaction... params) {
        completionMessage = params[0].completionMessage();
        try {
            params[0].execute();
        }
        catch (NullPointerException e){
            Log.i("Exception", "Database Value Does Not Exist");
            completionMessage = "UNSUCCESSFUL";
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("Database Transaction", completionMessage);
    }
}
