/*
package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import com.blink.treecommunicationproject.Activities.Adapters.BroadcastFragmentItemListAdapter;
import com.blink.treecommunicationproject.Activities.Adapters.TaskFragmentListItemAdapter;
import com.blink.treecommunicationproject.Objects.Broadcast;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;

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
        DatabaseMethods.getTasks(Global.userRole.getId(), 18,new Connection.OnCallFinish() {
            @Override
            public void processFinish(String output) throws JSONException {
                JSONObject result = new JSONObject(output);
                if (result.getString("s").equals("1")) {
                    JSONArray array = (result.getJSONArray("i"));
                    for (int p = 0; p < array.length(); p++) {
                        JSONObject row = array.getJSONObject(p);
                        parseObject(row);
                    }
                    BroadcastFragmentItemListAdapter adapter = new BroadcastFragmentItemListAdapter(getActivity(), broadcasts);
                    listOfBroadcasts.setAdapter(adapter);
                }
            }

            private void parseObject(JSONObject jsO) throws JSONException {
                Task task = new Task();
                task.setId(jsO.getInt("TaskId"));
                task.setFromUserRoleId(jsO.getInt("FromUserRoleId"));
                task.setDelegatedToUserRoleId(jsO.optInt("DelegatedToUserRoleId"));
                task.setToUserRoleId(jsO.getInt("ToUserRoleId"));
                task.setTaskState(jsO.getInt("TaskState"));
                task.setStartDate(jsO.getString("StartDate"));
                task.setDueDate(jsO.getString("DueDate"));
                task.setTitle(jsO.getString("Title"));
                task.setContent(jsO.getString("Content"));
                task.setFromUserRole(jsO.getString("FromUserRole"));
                task.setToUserRole(jsO.getString("ToUserRole"));
                task.setDelegatedToUserRole(jsO.optString("DelegatedToUserRole"));

                allTasks.add(task);
            }

*/
/*            private Date stringToDate(String s) {
                Date date = null;
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    date = format.parse(s);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }*//*

        }).execute();

        listOfTasks = (ListView) rootView.findViewById(R.id.lvTasks);
        assignNewTask = (ImageButton) rootView.findViewById(R.id.btnAssignNewTask);
        btnMyTasks = (Button) rootView.findViewById(R.id.btnMyTasks);
        btnReceivedRequests = (Button) rootView.findViewById(R.id.btnReceivedRequests);
        btnSentRequests = (Button) rootView.findViewById(R.id.btnSentRequests);


        btnMyTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTasks(1);
                TaskFragmentListItemAdapter adapter = new TaskFragmentListItemAdapter(getActivity(), tasks);
                listOfTasks.setAdapter(adapter);
            }
        });


        btnReceivedRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTasks(2);
                TaskFragmentListItemAdapter adapter = new TaskFragmentListItemAdapter(getActivity(), tasks);
                listOfTasks.setAdapter(adapter);
            }
        });

        btnSentRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTasks(3);
                TaskFragmentListItemAdapter adapter = new TaskFragmentListItemAdapter(getActivity(), tasks);
                listOfTasks.setAdapter(adapter);
            }
        });


        assignNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.Fragment fragment = new AssignNewTaskFragment(false);
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }


}
*/
