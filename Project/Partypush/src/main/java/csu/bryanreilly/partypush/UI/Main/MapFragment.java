package csu.bryanreilly.partypush.UI.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import csu.bryanreilly.partypush.Network.Facebook.FacebookFragment;
import csu.bryanreilly.partypush.R;

public class MapFragment extends FacebookFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return rootView;
    }
}
