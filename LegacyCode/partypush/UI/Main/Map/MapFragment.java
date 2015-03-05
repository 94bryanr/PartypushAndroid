package csu.bryanreilly.partypush.UI.Main.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflates the login_fragment_login view layout
        View rootView = inflater.inflate(R.layout.main_map_fragment_map, container, false);
        return rootView;
    }
}