package csu.bryanreilly.partypush.Network.Transactions;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Friend;

public class GetFriendsWithApp{

    public static void makeCall(){
        final ArrayList<Friend> friends = new ArrayList<Friend>();

        /* make the API call */
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
                                Friend currentFriend = new Friend(
                                        friendArray.getJSONObject(friendIndex).getString("name"),
                                        friendArray.getJSONObject(friendIndex).getString("id"));
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
