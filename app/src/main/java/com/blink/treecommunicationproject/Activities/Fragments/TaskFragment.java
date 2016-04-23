package com.blink.treecommunicationproject.Activities.Fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.blink.treecommunicationproject.Activities.Adapters.TaskFragmentListItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;

import java.util.ArrayList;
import java.util.Date;


public class TaskFragment extends Fragment {

    private ListView listOfTasks;
    private ImageButton assignNewTask;
    private View rootView;
    public TaskFragment(){}

    private ArrayList<Task> tasks = new ArrayList<Task>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_task, container, false);

        tasks.add(new Task(0, Global.user, new Employee(0, "Susan", "J. Mordigan", "03555666", Employee.EmployeeType.Advisor), new Date(), "Meeting at 13:00."));
        tasks.add(new Task(1, Global.user, new Employee(0, "Jack", "Black", "78959565", Employee.EmployeeType.Responsible), new Date(), "Print the documents for Steve."));
        tasks.add(new Task(2, Global.user, new Employee(0, "Elie", "Nassar", "70112547", Employee.EmployeeType.Responsible), new Date(), "Send the documents"));
        tasks.add(new Task(3, Global.user, new Employee(0, "John", "Blue", "81235689", Employee.EmployeeType.Director), new Date(), "Assign tasks to people discussed in yesterday's meeting."));

        initialize();

        return rootView;
    }

    private void initialize() {
        listOfTasks = (ListView) rootView.findViewById(R.id.lvTasks);
        assignNewTask = (ImageButton) rootView.findViewById(R.id.btnAssignNewTask);

        TaskFragmentListItemAdapter adapter = new TaskFragmentListItemAdapter(getActivity(), tasks);
        listOfTasks.setAdapter(adapter);

        assignNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AssignNewTaskFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

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
