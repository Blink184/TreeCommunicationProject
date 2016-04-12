package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.HomeFragmentGridItemAdapter;
import com.blink.treecommunicationproject.R;

/**
 * Created by ahmad hammoud on 4/12/2016.
 */
public class HomeFragment extends Fragment {
    private GridView grid;
    private View rootView;


    String[] ppl = {
            "Aynur",
            "Ahmad",
            "Azzam",
            "Aynur",
            "Ahmad",
            "Azzam",
            "Aynur",
            "Ahmad",
            "Azzam"

    };
    int[] imageId = {
            R.drawable.person,
            R.drawable.person,
            R.drawable.person,
            R.drawable.person,
            R.drawable.person,
            R.drawable.person,
            R.drawable.person,
            R.drawable.person,
            R.drawable.person
    };


    public HomeFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initialize();

        return rootView;
    }

    private void initialize() {
        grid = (GridView) rootView.findViewById(R.id.grid);

        HomeFragmentGridItemAdapter adapter = new HomeFragmentGridItemAdapter(getActivity(), ppl, imageId);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " +ppl[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }
}
