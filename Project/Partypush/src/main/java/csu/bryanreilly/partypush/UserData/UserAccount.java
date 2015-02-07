package csu.bryanreilly.partypush.UserData;

import csu.bryanreilly.partypush.Network.AmazonDDB.PutDatabaseItem;
import csu.bryanreilly.partypush.Program.Constants;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserAccount {
    private static String firstName;
    private static String lastName;
    private static String name;
    private static String id;
    private static String username;
    private static String birthday;

    //Holds all of the users known parties
    private Party[] parties;
    //Holds all of the users known friends
    private Friend[] friends;

    public UserAccount() {
        //Get friends
    }

    public static void addFriend(String id){
        //Add a friend to list
        //friends.add(id)
        //Update Friends field on amazon db
    }

    public static void setFirstName(String firstName) {
        UserAccount.firstName = firstName;
    }

    public static void setBirthday(String birthday) {
        UserAccount.birthday = birthday;
    }

    public static void setLastName(String lastName) {
        UserAccount.lastName = lastName;
    }

    public static void setName(String name) {
        UserAccount.name = name;
    }

    public static void setId(String id) {
        UserAccount.id = id;
    }

    public static void setUsername(String username) {
        UserAccount.username = username;
    }

    public static String getName() {
        return name;
    }

    public static String getId() {
        return id;
    }

    public static void login(){
        Calendar calendar = new GregorianCalendar();
        PutDatabaseItem putNameDate = new PutDatabaseItem(Constants.USER_DATABASE);
        putNameDate.addField(Constants.USER_DATABASE_ID, getId());
        putNameDate.addField(Constants.USER_DATABASE_NAME, getName());
        String date =  Integer.toString(calendar.get(Calendar.MONTH)) + "-" +
                Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + Integer.toString(calendar.get(Calendar.YEAR));
        putNameDate.addField(Constants.USER_DATABASE_DATE, date);
        putNameDate.sendItem();
    }
}
