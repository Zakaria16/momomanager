package com.mazitekgh.momorecords.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mazitekgh.momorecords.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Context c = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // ListView lv =v.findViewById(R.id.list_item);
        //  TextView tv =v.findViewById(R.id.amount_tv);
        // LinearLayout receivedActivitiesClick =v.findViewById(R.id.)

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}