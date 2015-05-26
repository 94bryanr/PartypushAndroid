package csu.bryanreilly.partypush.Program;

public class Constants {
    public static String USER_DATABASE = "PartypushUsers";
    public static String USER_DATABASE_ID = "UserID";
    public static String USER_DATABASE_NAME = "Name";
    public static String USER_DATABASE_FRIENDS = "Friends";
    public static String USER_DATABASE_DATE = "LastLogin";
    public static String USER_DATABASE_PARTIES = "Parties";

    public static String PARTY_DATABASE = "PartypushParties";
    public static String PARTY_DATABASE_ID = "PartyID";
    public static String PARTY_DATABASE_NAME = "Name";
    public static String PARTY_DATABASE_LOCATION = "Location";
    public static String PARTY_DATABASE_LATITUDE = "Latitude";
    public static String PARTY_DATABASE_LONGITUDE = "Longitude";


    //How long to wait on database transactions before timeout
    public static int DATABASE_TIMEOUT_SECONDS = 10;

    // How long to wait between database transactions
    public static int DATABASE_TRANSACTION_DELAY = 5000;

    //Map
    public static int MAP_ZOOM_LEVEL = 13;
}
