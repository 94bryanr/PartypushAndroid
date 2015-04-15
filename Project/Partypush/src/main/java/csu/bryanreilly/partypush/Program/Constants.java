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

    //How long to wait on database transactions before timeout
    public static int DATABASE_TIMEOUT_SECONDS = 10;

    //Map
    public static int MAP_ZOOM_LEVEL = 12;
}
