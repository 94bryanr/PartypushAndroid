package csu.bryanreilly.partypush.UserData;

public class Party {
    private String distance;
    private String score;

    public Party(String distance){
        this.distance = distance;
        calculateScore();
    }

    //Calculates score based on distance, attendees, friends, etc.
    private void calculateScore() {
        score = "42";
    }

    public String getScore() {
        return score;
    }

    public String getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Score: " + getScore() + " Distance: " + getDistance()
                + " Index: " + getIndex();
    }

    public int getIndex() {
        int index = 0;
        for(Party p : UserAccount.getUserAccount().getParties()){
            if (this.equals(p)){
                return index;
            }
            index++;
        }
        try {
            throw new Exception("Position of Party in UserAccount Party[] not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
