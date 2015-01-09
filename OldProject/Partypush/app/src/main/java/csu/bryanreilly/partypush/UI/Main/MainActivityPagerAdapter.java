package csu.bryanreilly.partypush.UI.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import csu.bryanreilly.partypush.UI.Main.Friend.FriendFragment;
import csu.bryanreilly.partypush.UI.Main.Map.MapFragment;
import csu.bryanreilly.partypush.UI.Main.Party.PartyFragment;

public class MainActivityPagerAdapter extends FragmentPagerAdapter {
    private int NUMBER_OF_TABS = 3;

    public MainActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Party fragment activity
                return new PartyFragment();
            case 1:
                // Map fragment activity
                return new MapFragment();
            case 2:
                // Friends fragment activity
                return new FriendFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }
}
