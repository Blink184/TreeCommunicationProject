package com.blink.treecommunicationproject.Activities.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.blink.treecommunicationproject.R;


public class BroadcastFragment extends Fragment {

    private ListView listOfBroadcasts;
    private ImageButton sendNewBct;
    private Button btnReceivedBct;
    private Button btnSentBct;

    private View rootView;

    public BroadcastFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_broadcast, container, false);

        initialize();

        return rootView;
    }

    private void initialize() {

    }


}
