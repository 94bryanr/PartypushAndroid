package csu.bryanreilly.partypush.UI.Main;

import java.util.Locale;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.SupportMapFragment;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.Friends.FriendPickerActivity;
import csu.bryanreilly.partypush.UI.Main.Friends.MainFriendsFragment;
import csu.bryanreilly.partypush.UI.Main.Map.MainMapFragment;
import csu.bryanreilly.partypush.UI.Main.Parties.MainPartiesFragment;
import csu.bryanreilly.partypush.UI.Main.Parties.PartyCreateActivity;
import csu.bryanreilly.partypush.UI.Settings.SettingsActivity;
import csu.bryanreilly.partypush.UserData.AccountManager;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    // Client for google play services
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // So fragments are not destroyed while navigating on main pager
        int FragmentOffscreenLimit = 2;
        mViewPager.setOffscreenPageLimit(FragmentOffscreenLimit);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        //Get intents
        Intent intent = getIntent();
        int tabLocationIndex = 0;
        int startTab = intent.getIntExtra("Tab", tabLocationIndex);
        mViewPager.setCurrentItem(startTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Handle button clicks
        if (id == R.id.action_new) {
            //Checks to see if we are on the correct fragment
            if(mViewPager.getCurrentItem() == FragmentInfo.FriendsFragment) {
                Intent startFriendPickerActivity = new Intent(this, FriendPickerActivity.class);
                startActivity(startFriendPickerActivity);
            }

            //Checks to see if we are on the correct fragment
            else if(mViewPager.getCurrentItem() == FragmentInfo.PartiesFragment) {
                Intent startPartyCreateActivity = new Intent(this, PartyCreateActivity.class);
                startActivity(startPartyCreateActivity);
            }

            return true;
        }
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        if(tab.getPosition() == FragmentInfo.FriendsFragment){
            updateFriendsList();
        }
        if(tab.getPosition() == FragmentInfo.PartiesFragment){
            updatePartiesList();
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //Need for interface
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if(tab.getPosition() == FragmentInfo.FriendsFragment){
            updateFriendsList();
        }
        if(tab.getPosition() == FragmentInfo.PartiesFragment){
            updatePartiesList();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateFriendsList();
        updatePartiesList();
    }

    public void updateFriendsList(){
        MainFriendsFragment fragment = (MainFriendsFragment)getFragmentAt(FragmentInfo.FriendsFragment);
        if (fragment != null)
            fragment.refreshFriendsList();
    }

    public void updatePartiesList(){
        MainPartiesFragment fragment = (MainPartiesFragment)getFragmentAt(FragmentInfo.PartiesFragment);
        if (fragment != null)
            fragment.refreshPartiesList();
    }

    public Fragment getFragmentAt(int position){
        String name = makeFragmentName(mViewPager.getId(), position);
        Fragment viewPagerFragment = getSupportFragmentManager().findFragmentByTag(name);
        if(viewPagerFragment != null) {
            return viewPagerFragment;
        }
        return null;
    }

    // Adds the auto-generated part of the fragment tab, for use in getFragmentAt()
    private static String makeFragmentName(int viewId, int position) {
        return "android:switcher:" + viewId + ":" + position;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // This is here so we do not make a new instance of the map each time we access it
        public SupportMapFragment map = new MainMapFragment();

        @Override
        public Fragment getItem(int position) {
            // startTransaction is called to instantiate the fragment for the given page.
            switch (position) {
                case FragmentInfo.FriendsFragment:
                    return new MainFriendsFragment();
                case FragmentInfo.PartiesFragment:
                    return new MainPartiesFragment();
                case FragmentInfo.MapFragment:
                    return map;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return FragmentInfo.Count;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case FragmentInfo.FriendsFragment:
                    return getString(R.string.title_section1).toUpperCase(l);
                case FragmentInfo.PartiesFragment:
                    return getString(R.string.title_section2).toUpperCase(l);
                case FragmentInfo.MapFragment:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }
}