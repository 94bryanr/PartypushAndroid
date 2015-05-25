package csu.bryanreilly.partypush.UI.Main.Parties;

import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.Utilities.ErrorDialog;
import csu.bryanreilly.partypush.Utilities.LocationGeocoder;

public class PartyCreateActivity extends FragmentActivity {
    EditText descriptionBox;
    EditText locationBox;
    LocationGeocoder locationGeocoder;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_create);
        descriptionBox = (EditText)findViewById(R.id.editTextDescription);
        locationBox = (EditText)findViewById(R.id.editTextLocation);
    }

    public void createClicked(View view){
        String description = descriptionBox.getText().toString().trim();
        String location = locationBox.getText().toString().trim();

        if (entriesInRange(description, location) && locationIsValid(location)){
            List<Address> addresses = locationGeocoder.getMatchedAddresses();
            addBestLocation(addresses, location, description);
        }
    }

    private boolean entriesInRange(String description, String location){
        int minimumTextLength = 4;
        int maximumTextLength = 60;

        if(description.length() < minimumTextLength || location.length() < minimumTextLength){
            showError("Entries must be at least " + minimumTextLength + " characters in length.");
            return false;
        }
        if(description.length() > maximumTextLength || location.length() > maximumTextLength){
            showError("Entries must be shorter than " + maximumTextLength + " characters in length.");
            return false;
        }
        return true;
    }

    private boolean locationIsValid(String location){
        locationGeocoder = new LocationGeocoder(location);

        if(!locationGeocoder.foundMatch()) {
            showError("Invalid location. Try including State, City, and Street Address.");
            return false;
        }
        return true;
    }

    private void addBestLocation(List<Address> addresses, String location, String description){
        // Show user top locations
        AddressChooserDialog chooserDialog = new AddressChooserDialog();
        Bundle args = new Bundle();
        args.putParcelable(chooserDialog.ADDRESS_LIST_TAG, new ParceableAddressList(addresses));
        args.putString(chooserDialog.LOCATION_TAG, location);
        args.putString(chooserDialog.DESCRIPTION_TAG, description);
        chooserDialog.setArguments(args);
        chooserDialog.show(getFragmentManager(), "addressChooserDialog");
    }

    private void showError(String errorMessage){
        ErrorDialog errorDialog = new ErrorDialog();
        Bundle args = new Bundle();
        args.putString(ErrorDialog.ERROR_MESSAGE_KEY, errorMessage);
        errorDialog.setArguments(args);
        errorDialog.show(getFragmentManager(), "invalidPartyDialog");
    }
}