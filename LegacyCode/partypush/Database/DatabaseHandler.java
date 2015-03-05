package csu.bryanreilly.partypush.Database;

import csu.bryanreilly.partypush.UserData.Friend;
import csu.bryanreilly.partypush.UserData.Party;

public class DatabaseHandler {
    //Singleton class
    private static DatabaseHandler db;

    private DatabaseHandler(){
        //Protects from instantiation.
    }

    public static DatabaseHandler getDataBaseHandler(){
        if (db == null){
            db = new DatabaseHandler();
        }
        return db;
    }

    //Queries the database for the users party list.
    public Party[] getPartyList(){
        //For testing.
        Party[] parties = new Party[]{
                new Party("3"),
                new Party("4"),
                new Party("15")
        };

        return parties;
    }

    //Queries the database for the users friends list.
    public Friend[] getFriendList(){
        //For testing.
        Friend[] friends = new Friend[]{
                new Friend("Jojo", "1435"),
                new Friend("Bobby", "1531")
        };

        return friends;
    }
}