package csu.bryanreilly.partypush.UI.Settings;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import csu.bryanreilly.partypush.Network.Facebook.FacebookFragmentManager;
import csu.bryanreilly.partypush.R;

public class SettingsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookFragmentManager.onCreateUpdate(getActivity(), savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        FacebookFragmentManager.onResumeUpdate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookFragmentManager.onActivityResultUpdate(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        FacebookFragmentManager.onPauseUpdate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FacebookFragmentManager.onDestroyUpdate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FacebookFragmentManager.onSaveInstanceStateUpdate(outState);
    }
}
