package com.blink.treecommunicationproject.Activities.Fragments;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.TaskFragmentListItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.Objects.UserRole;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class TaskFragment extends Fragment {

    private ListView listOfTasks;
    private ImageButton assignNewTask;
    private Button btnMyTasks;
    private Button btnReceivedRequests;
    private Button btnSentRequests;
    private View rootView;
    public TaskFragment(){}

    private ArrayList<Task> tasks = new ArrayList<Task>();
    private ArrayList<Task> allTasks = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_task, container, false);

        initialize();

        return rootView;
    }

    private void filterTasks(int type) {
        tasks.clear();
        if (type == 1) {
            for (Task task : allTasks) {
                if (task.getToUserRoleId() == Global.userRole.getId()) {
                    tasks.add(task);
                }
            }
            btnMyTasks.setTextColor(getResources().getColor(R.color.orangeWeb));
            btnReceivedRequests.setTextColor(getResources().getColor(R.color.white));
            btnSentRequests.setTextColor(getResources().getColor(R.color.white));
        }
        else if (type == 2) {
            for (Task task : allTasks) {
                if (task.getToUserRoleId() == Global.userRole.getId() && task.getFromUserRoleId() != Global.userRole.getId()) {
                    tasks.add(task);
                }
            }
            btnMyTasks.setTextColor(getResources().getColor(R.color.white));
            btnReceivedRequests.setTextColor(getResources().getColor(R.color.orangeWeb));
            btnSentRequests.setTextColor(getResources().getColor(R.color.white));
        }
        else if (type == 3) {
            for (Task task : allTasks) {
                if (task.getFromUserRoleId() == Global.userRole.getId() && task.getToUserRoleId() != Global.userRole.getId()) {
                    tasks.add(task);
                }
            }
            btnMyTasks.setTextColor(getResources().getColor(R.color.white));
            btnReceivedRequests.setTextColor(getResources().getColor(R.color.white));
            btnSentRequests.setTextColor(getResources().getColor(R.color.orangeWeb));
        }
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
                    filterTasks(1);
                    TaskFragmentListItemAdapter adapter = new TaskFragmentListItemAdapter(getActivity(), tasks);
                    listOfTasks.setAdapter(adapter);
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

/*            private Date stringToDate(String s) {
                Date date = null;
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    date = format.parse(s);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }*/
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
                Fragment fragment = new AssignNewTaskFragment(false);
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

}
