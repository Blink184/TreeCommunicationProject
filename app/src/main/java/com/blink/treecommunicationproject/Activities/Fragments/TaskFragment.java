package com.blink.treecommunicationproject.Activities.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

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

        /*tasks.add(new Task(0, Global.user, new Employee(0, "Susan", "J. Mordigan", "03555666", Employee.EmployeeType.Advisor), new Date(), "Meeting at 13:00."));
        tasks.add(new Task(1, Global.user, new Employee(0, "Jack", "Black", "78959565", Employee.EmployeeType.Responsible), new Date(), "Print the documents for Steve."));
        tasks.add(new Task(2, Global.user, new Employee(0, "Elie", "Nassar", "70112547", Employee.EmployeeType.Responsible), new Date(), "Send the documents"));
        tasks.add(new Task(3, Global.user, new Employee(0, "John", "Blue", "81235689", Employee.EmployeeType.Director), new Date(), "Assign tasks to people discussed in yesterday's meeting."));*/

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

        }
        else if (type == 2) {
            for (Task task : allTasks) {
                if (task.getToUserRoleId() == Global.userRole.getId() && task.getFromUserRoleId() != Global.userRole.getId()) {
                    tasks.add(task);
                }
            }
        }
        else if (type == 3) {
            for (Task task : allTasks) {
                if (task.getFromUserRoleId() == Global.userRole.getId() && task.getToUserRoleId() != Global.userRole.getId()) {
                    tasks.add(task);
                }
            }
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
/*        assignNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AssignNewTaskFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/

//        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(getActivity(), "You Clicked on " + ppl[+position], Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

}
