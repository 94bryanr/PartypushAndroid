package csu.bryanreilly.partypush.Network.Facebook;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class FacebookFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookFragmentManager.onCreateUpdate(getActivity(), savedInstanceState);
    }
}
