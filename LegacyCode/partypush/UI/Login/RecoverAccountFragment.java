package csu.bryanreilly.partypush.UI.Login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import csu.bryanreilly.partypush.R;

public class RecoverAccountFragment extends Fragment implements OnClickListener {
    //Handles letting users recover a lost account
    //OnClickListener allows the fragment to react to button clicks.

    //Handles initialization of fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.login_fragment_recoveraccount, container, false);

        //Assigns each button on the view to the click listener.
        //This allows the buttons to trigger onClick() when they are pressed.
        Button recoverButton = (Button) rootView.findViewById(R.id.recoverButton);
        recoverButton.setOnClickListener(this);

        return rootView;
    }

    //Handles button clicks from views on fragment.
    //Runs the buttons specific method.
    public void onClick(View v){
        switch (v.getId()){
            case R.id.recoverButton:
                RecoverButtonClicked();
                break;
        }
    }

    //Handles recover button click
    private void RecoverButtonClicked() {
        getFragmentManager().popBackStack();
    }
}
