package com.blink.treecommunicationproject.Activities.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.blink.treecommunicationproject.Activities.Adapters.ToDoTaskFragmentListItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;

import java.util.ArrayList;
import java.util.Date;

public class ToDoTaskFragment extends Fragment {

    private ListView listOfTasks;
    private View rootView;
    public ToDoTaskFragment(){}

    private ArrayList<Task> tasks = new ArrayList<Task>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_to_do_task, container, false);

        /*tasks.add(new Task(0, Global.user, new Employee(0, "Susan", "J. Mordigan", "03555666", Employee.EmployeeType.Advisor), new Date(), "Meeting at 13:00."));
        tasks.add(new Task(1, Global.user, new Employee(0, "Jack", "Black", "78959565", Employee.EmployeeType.Responsible), new Date(), "Print the documents for Steve."));
        tasks.add(new Task(2, Global.user, new Employee(0, "Elie", "Nassar", "70112547", Employee.EmployeeType.Responsible), new Date(), "Send the documents"));
        tasks.add(new Task(3, Global.user, new Employee(0, "Jack", "Black", "81235689", Employee.EmployeeType.Director), new Date(), "Assign tasks to people discussed in yesterday's meeting."));*/

        initialize();

        return rootView;
    }

    private void initialize() {
        listOfTasks = (ListView) rootView.findViewById(R.id.lvTasks);

        ToDoTaskFragmentListItemAdapter adapter = new ToDoTaskFragmentListItemAdapter(getActivity(), tasks);
        listOfTasks.setAdapter(adapter);

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
