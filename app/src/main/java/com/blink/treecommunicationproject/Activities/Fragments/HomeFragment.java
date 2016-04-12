package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blink.treecommunicationproject.R;

/**
 * Created by ahmad hammoud on 4/12/2016.
 */
public class HomeFragment extends Fragment {
    public HomeFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }
}
