package csu.bryanreilly.partypush.Network.Transactions;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend.Friend;

public class GetFacebookFriendsWithApp {

    public static void run(){
        final ArrayList<Friend> friends = new ArrayList<Friend>();

        new Request(
                Session.getActiveSession(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        //Get friend data
                        try {
                            JSONArray friendArray = response.getGraphObject().getInnerJSONObject().getJSONArray("data");
                            for (int friendIndex = 0; friendIndex < friendArray.length(); friendIndex++){
                                String name = friendArray.getJSONObject(friendIndex).getString("name");
                                String fullID = friendArray.getJSONObject(friendIndex).getString("id");

                                Friend currentFriend = new Friend(name, fullID);
                                friends.add(currentFriend);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Update the user account
                        AccountManager.setFriendsWithApp(friends);
                    }
                }
        ).executeAsync();
    }

}
