package csu.bryanreilly.partypush.UI.Main.Parties;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ParceableAddressList implements Parcelable{
    private List<Address> addresses;

    public ParceableAddressList(List<Address> addresses){
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(addresses);
    }

    public static final Parcelable.Creator<ParceableAddressList> CREATOR
            = new Parcelable.Creator<ParceableAddressList>() {
        public ParceableAddressList createFromParcel(Parcel in) {
            return new ParceableAddressList(in);
        }

        public ParceableAddressList[] newArray(int size) {
            return new ParceableAddressList[size];
        }
    };

    private ParceableAddressList(Parcel in) {
        in.readList(addresses, null);
    }
}
