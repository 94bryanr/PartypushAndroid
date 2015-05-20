package csu.bryanreilly.partypush.UserData;

import android.app.Activity;
import android.os.AsyncTask;

import com.facebook.Session;

import csu.bryanreilly.partypush.UI.UIManager;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class AccountLogoutService extends AsyncTask<Activity, Void, Activity> {
    protected Activity doInBackground(Activity... params) {
        Session.getActiveSession().close();
        AccountManager.getDatabaseProvider().shutdown();
        return params[0];
    }

    protected void onPostExecute(Activity activity) {
        UIManager.returnToLogin(activity);
    }
}
