package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.blink.treecommunicationproject.Activities.Adapters.BroadcastFragmentItemListAdapter;
import com.blink.treecommunicationproject.Activities.Adapters.TaskFragmentListItemAdapter;
import com.blink.treecommunicationproject.Objects.Broadcast;
import com.blink.treecommunicationproject.Objects.BroadcastLine;
import com.blink.treecommunicationproject.Objects.Role;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.Objects.User;
import com.blink.treecommunicationproject.Objects.UserRole;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.Services.Preferences;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;
import com.blink.treecommunicationproject.Web.Links;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BroadcastFragment extends Fragment {

    private ListView listOfBroadcasts;
    private ImageButton sendNewBct;
    private Button btnReceivedBct;
    private Button btnSentBct;


    private ArrayList<Broadcast> broadcasts = new ArrayList<Broadcast>();
    private ArrayList<Broadcast> allBroadcasts = new ArrayList<>();

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
        DatabaseMethods.getBroadcasts(Global.userRole.getId(), 18, new Connection.OnCallFinish() {
            @Override
            public void processFinish(String output) throws JSONException {
                JSONObject result = new JSONObject(output);
                if (result.getString("s").equals("1")) {
                    JSONArray array = (result.getJSONArray("i"));
                    for (int p = 0; p < array.length(); p++) {
                        JSONObject row = array.getJSONObject(p);
                        parseObject(row);
                    }
                    filterBroadcasts(1);
                    BroadcastFragmentItemListAdapter adapter = new BroadcastFragmentItemListAdapter(getActivity(), broadcasts);
                    listOfBroadcasts.setAdapter(adapter);
                }
            }

            private void parseObject(JSONObject jsO) throws JSONException {
                Broadcast broadcast = new Broadcast();
                BroadcastLine broadcastLine = new BroadcastLine();
                UserRole ur = new UserRole();
                User u = new User();
                Role r = new Role();

                broadcastLine.setSender(intToBool(jsO.getInt("IsSender")));

                u.setFirstName(jsO.getString("FirstName"));
                u.setLastName(jsO.getString("LastName"));
//                u.setImage(jsO.getString(Links.PROFILEPICTURESFOLDER + jsO.getString("Image")));
                r.setDesription(jsO.optString("Role"));
                ur.setUser(u);
                ur.setRole(r);
                ur.setId(jsO.getInt("UserRoleId"));

                if (broadcastLine.isSender()) {
                    broadcast.setFromUserRole(ur);
                } else {
                    broadcastLine.setToUserRole(ur);
                }

                broadcast.setId(jsO.getInt("BroadcastId"));
                broadcast.setTitle(jsO.getString("Title"));
                broadcast.setContent(jsO.getString("Content"));
                broadcast.setDateSent(jsO.getString("DateSent"));

                broadcast.setBroadcastLine(broadcastLine);

                allBroadcasts.add(broadcast);
            }

            private boolean intToBool(int i) {
                return i == 1;
            }

        }).execute();

        listOfBroadcasts = (ListView) rootView.findViewById(R.id.lvBroadcasts);
        sendNewBct = (ImageButton) rootView.findViewById(R.id.btnSendNewBroadcast);
        btnReceivedBct = (Button) rootView.findViewById(R.id.btnReceivedBct);
        btnSentBct = (Button) rootView.findViewById(R.id.btnSentBct);


        btnReceivedBct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcasts.clear();
                filterBroadcasts(1);
                BroadcastFragmentItemListAdapter adapter = new BroadcastFragmentItemListAdapter(getActivity(), broadcasts);
                listOfBroadcasts.setAdapter(adapter);
            }
        });


        btnSentBct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcasts.clear();
                filterBroadcasts(2);
                BroadcastFragmentItemListAdapter adapter = new BroadcastFragmentItemListAdapter(getActivity(), broadcasts);
                listOfBroadcasts.setAdapter(adapter);
            }
        });


        sendNewBct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.Fragment fragment = new SendBroadcastFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    private void filterBroadcasts(int i) {
        if (i == 1) {
            for (int j = 0; j < allBroadcasts.size(); j++) {
                if (!allBroadcasts.get(j).getBroadcastLine().isSender()) {
                    broadcasts.add(allBroadcasts.get(j));
                }
            }
            btnReceivedBct.setTextColor(getResources().getColor(R.color.orangeWeb));
            btnSentBct.setTextColor(getResources().getColor(R.color.white));
        } else {
            for (int j = 0; j < allBroadcasts.size(); j++) {
                if (allBroadcasts.get(j).getBroadcastLine().isSender()) {
                    broadcasts.add(allBroadcasts.get(j));
                }
            }
            btnReceivedBct.setTextColor(getResources().getColor(R.color.white));
            btnSentBct.setTextColor(getResources().getColor(R.color.orangeWeb));
        }
    }


}
