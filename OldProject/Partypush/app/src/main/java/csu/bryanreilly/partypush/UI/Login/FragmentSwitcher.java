package csu.bryanreilly.partypush.UI.Login;

//This interface allows an activity to define a method for switching fragments.
//Fragments can then call the method if the activity implements it.
//This keeps fragments decoupled from activities, while still allowing interaction.

//Parameters: Fragment to switch to, then boolean value based on whether or not fragment
//should be added to back stack


import android.support.v4.app.Fragment;

public interface FragmentSwitcher {
    public void ChangeCurrentFragmentTo(Fragment fragment, boolean addToBackStack);
}

//For fragments...
//    private FragmentSwitcher fragmentSwitcher;
//    //Grabs the fragment switcher from the host activity.
//    @Override
//    public void onAttach(Activity activity){
//        super.onAttach(activity);
//        try{
//            fragmentSwitcher = (FragmentSwitcher) activity;
//        } catch (ClassCastException castException){
//            //The activity does not implement a fragment switcher.
//            System.out.println("Activity does not implement fragment switcher");
//        }
//    }
