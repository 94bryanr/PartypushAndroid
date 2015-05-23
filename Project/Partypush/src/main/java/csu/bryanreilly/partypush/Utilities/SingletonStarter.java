package csu.bryanreilly.partypush.Utilities;

import android.content.Context;

import csu.bryanreilly.partypush.UserData.LocationManager;

public class SingletonStarter {

    private static SingletonStarter singletonStarter;

    private SingletonStarter(Context context){
        LocationManager.getInstance();
        ContextGetter.getInstance().setContext(context);
    }

    public static SingletonStarter getInstance(Context context){
        if (singletonStarter == null){
            singletonStarter = new SingletonStarter(context);
        }
        return singletonStarter;
    }
}
