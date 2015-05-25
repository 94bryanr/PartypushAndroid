package csu.bryanreilly.partypush.UI.Main.Parties;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.location.Address;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import csu.bryanreilly.partypush.Network.TransactionManager;
import csu.bryanreilly.partypush.UI.UIManager;
import csu.bryanreilly.partypush.UserData.AccountManager;
import csu.bryanreilly.partypush.UserData.Party;

public class AddressChooserDialog extends DialogFragment {
    public static String ADDRESS_LIST_TAG = "Addresses";
    public static String LOCATION_TAG = "Location";
    public static String DESCRIPTION_TAG = "Description";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final List<Address> addresses =
                ((ParceableAddressList)getArguments().getParcelable(ADDRESS_LIST_TAG)).getAddresses();

        String[] addressesArray = buildStringAddresses(addresses);

        final String location = getArguments().getString(LOCATION_TAG);
        final String description = getArguments().getString(DESCRIPTION_TAG);

        builder.setTitle("Choose Address")
                .setItems(addressesArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Address chosenLocation = addresses.get(which);
                        LatLng absoluteLocation = new LatLng(chosenLocation.getLatitude(),
                                chosenLocation.getLongitude());
                        Party party = new Party(description, location,
                                AccountManager.getId(), absoluteLocation);
                        addParty(party);
                    }
                });
        return builder.create();
    }

    private String[] buildStringAddresses(List<Address> addresses){
        String[] addressesArray = new String[addresses.size()];
        for(int i = 0; i < addresses.size(); i++){
            Address address = addresses.get(i);
            addressesArray[i] = address.getFeatureName() +
                    " " + address.getThoroughfare() +
                    ": " + address.getLocality() +
                    ", " + address.getAdminArea();
        }

        return addressesArray;
    }

    private void addParty(Party party){
        TransactionManager.addParty(party);
        UIManager.returnToMain(getActivity());
    }
}