package csu.bryanreilly.partypush.UserData;

public class Friend {
    private String username;
    private String popularity;

    public Friend(String username, String popularity){
        this.username = username;
        this.popularity = popularity;
    }

    public String getUsername() {
        return username;
    }

    public String getPopularity() {
        return popularity;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + " Popularity: " + getPopularity()
                + " Index: " + getIndex();
    }

    public int getIndex() {
        int index = 0;
        for(Friend f : UserAccount.getUserAccount().getFriends()){
            if (this.equals(f)){
                return index;
            }
            index++;
        }
        try {
            throw new Exception("Position of Friend in UserAccount Friend[] not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
