package csu.bryanreilly.partypush.UserData.Friend;

import android.util.Log;

public class Friend {
    private String name;
    private String id;
    private STATUS currentStatus;
    public enum STATUS {
        FRIEND,
        USER_ADDED,
        OTHER_ADDED
    }

    public Friend(String name, String id) {
        this.name = name;
        this.id = id;
        Log.i("TransactionManager", "OriginalID: " + this.id);
        this.currentStatus = STATUS.FRIEND;

        if(id.endsWith("_S")){
            this.currentStatus = Friend.STATUS.USER_ADDED;
            this.id = id.substring(0, id.length()-2);
            Log.i("TransactionManager", "AlteredID: " + this.id);
        }
        else if(id.endsWith("_R")){
            this.currentStatus = Friend.STATUS.OTHER_ADDED;
            this.id = id.substring(0, id.length()-2);
            Log.i("TransactionManager", "AlteredID: " + this.id);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentStatusString() {
        switch(currentStatus){
            case FRIEND:
                return "Friend";
            case OTHER_ADDED:
                return "";
            case USER_ADDED:
                return "Added";
            default:
                return "";
        }
    }

    public STATUS getCurrentStatus() {
        return currentStatus;
    }
}
