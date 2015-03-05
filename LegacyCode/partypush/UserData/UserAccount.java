package csu.bryanreilly.partypush.UserData;

public class UserAccount {
    //The single instance of the class. Singleton
    private static UserAccount userAccount;

    private String name = "DEFAULT";

    //Holds all of the users known parties
    private Party[] parties;
    //Holds all of the users known friends
    private Friend[] friends;

    //Initializer
    //Notice, private to keep from external instantiation. Singleton class.
    private UserAccount(){
        //Protects from instantiation.
    }

    public static UserAccount getUserAccount(){
        if(userAccount == null){
            userAccount = new UserAccount();
        }
        return userAccount;
    }

    public void setParties(Party[] parties) {
        this.parties = parties;
    }

    public void setFriends(Friend[] friends) {
        this.friends = friends;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Party[] getParties() {
        return parties;
    }

    public Friend[] getFriends() {
        return friends;
    }

    public String getName() {
        return name;
    }
}
