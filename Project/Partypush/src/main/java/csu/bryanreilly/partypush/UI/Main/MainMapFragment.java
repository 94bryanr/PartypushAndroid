package csu.bryanreilly.partypush.UI.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.FacebookFragment;

public class MainMapFragment extends FacebookFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        return rootView;
    }
}
