package csu.bryanreilly.partypush.UI.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csu.bryanreilly.partypush.R;
import csu.bryanreilly.partypush.UI.FacebookFragment;

public class SettingsFragment extends FacebookFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        return rootView;
    }
}
