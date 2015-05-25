package csu.bryanreilly.partypush.UserData.Party;

import java.util.ArrayList;

public class PartyObserver {
    private static ArrayList<PartyCallback> callbacks = new ArrayList<PartyCallback>();

    public static void notifyPartiesChanged(){
        for (PartyCallback callback: callbacks){
            callback.partiesChanged();
        }
    }

    public static void addCallback(PartyCallback callback){
        callbacks.add(callback);
    }
}
