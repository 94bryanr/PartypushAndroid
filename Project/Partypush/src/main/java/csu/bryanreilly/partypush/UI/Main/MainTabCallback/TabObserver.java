package csu.bryanreilly.partypush.UI.Main.MainTabCallback;

import java.util.ArrayList;

public class TabObserver {
    private static ArrayList<TabCallback> callbacks = new ArrayList<>();

    public static void notifyTabSelected(int selected){
        for (TabCallback callback: callbacks){
            callback.tabSelected(selected);
        }
    }

    public static void notifyTabReselected(int reselected){
        for (TabCallback callback: callbacks){
            callback.tabReselected(reselected);
        }
    }

    public static void addCallback(TabCallback callback){
        callbacks.add(callback);
    }
}
