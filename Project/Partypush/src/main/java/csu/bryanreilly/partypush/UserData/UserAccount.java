package csu.bryanreilly.partypush.UserData;

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
}
