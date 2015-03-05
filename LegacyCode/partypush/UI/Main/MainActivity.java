package csu.bryanreilly.partypush.UI.Main;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.Main.Friend.Create.CreateFriendActivity;
import csu.bryanreilly.partypush.UI.Main.Party.Create.CreatePartyActivity;
import csu.bryanreilly.partypush.UI.Settings.SettingsActivity;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener{
    //Main activity for parties, maps, friends sliding fragments.
    //TabListener allows activity to monitor for view swipes

    //TODO: On Release: Get Google Maps release key and set up release version.
    //TODO: On Release: Get Facebook release key and set up release version.

    //View for containing swipe-able fragments
    private ViewPager viewPager;
    //Adapter for ViewPager that allows static tabs
    private MainActivityPagerAdapter pagerAdapter;
    //Action bar grabber
    private ActionBar actionBar;
    //Titles for the tabs on the action bar
    private String[] tabTitles = new String[3];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEV ACTIVITY: Main Activity Started");
        setContentView(R.layout.main_activity_main);

        InitializeView();

        InitializeActionBar();

        InitializeViewPageChangeListener();
    }

    //Inflates the buttons on the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_new:
                AddButtonPressed();
                return true;
            case R.id.action_settings:
                StartActivity(SettingsActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Called when the add button on the action bar is pressed
    private void AddButtonPressed(){
        //If the add button is pressed while viewing the party fragment or map fragment, open add party page
        if(viewPager.getCurrentItem() == 0 || viewPager.getCurrentItem() == 1)
            StartActivity(CreatePartyActivity.class);
        //If the add button is pressed while viewing the friends fragment, open add friend page
        if(viewPager.getCurrentItem() == 2)
            StartActivity(CreateFriendActivity.class);
    }

    public void StartActivity(Class activity){
        Intent startActivity = new Intent(this, activity);
        startActivity(startActivity);
    }

    //For starting a detail fragment
    public void StartActivity(Class activity, int index){
        Intent startActivity = new Intent(this, activity);
        startActivity.putExtra("index", index);
        startActivity(startActivity);
    }

    private void InitializeView(){
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }

    private void InitializeActionBar(){
        tabTitles[0] = getString(R.string.party_tab);
        tabTitles[1] = getString(R.string.map_tab);
        tabTitles[2] = getString(R.string.friends_tab);
        for (String tab_name : tabTitles) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
    }

    private void InitializeViewPageChangeListener(){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            //Used to change selected tab to match selected fragment
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    //Used to set current fragment to whatever tab was pressed
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
