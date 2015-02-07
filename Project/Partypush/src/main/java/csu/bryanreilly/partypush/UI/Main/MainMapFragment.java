package csu.bryanreilly.partypush.UI.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import csu.bryanreilly.partypush.Network.NetworkFragment;
import csu.bryanreilly.partypush.R;

public class MainMapFragment extends NetworkFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return rootView;
    }
}
