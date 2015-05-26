package csu.bryanreilly.partypush.Utilities;

import android.content.Context;

import csu.bryanreilly.partypush.UserData.Location.LocationManager;

public class SingletonStarter {

    private static SingletonStarter singletonStarter;

    private SingletonStarter(Context context){
        ContextGetter.getInstance().setContext(context);
        LocationManager.getInstance();
    }

    public static SingletonStarter getInstance(Context context){
        if (singletonStarter == null){
            singletonStarter = new SingletonStarter(context);
        }
        return singletonStarter;
    }
}
