package com.blink.treecommunicationproject.Activities.Fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.ObjectAdapters.AutoCompleteTVItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;

import java.util.ArrayList;
import java.util.Date;


public class AssignNewTaskFragment extends Fragment {

    private View rootView;
    private ArrayList<Employee> toEmployees = new ArrayList<>();
    private ImageButton send;
    private AutoCompleteTextView actvToEmployee;
    private Employee selectedEmployee;

    @SuppressLint("ValidFragment")
    public AssignNewTaskFragment(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
    public AssignNewTaskFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_assign_new_task, container, false);

        toEmployees.add(new Employee(0, "Susan", "J. Mordigan", "03555666", Employee.EmployeeType.Advisor));
        toEmployees.add(new Employee(0, "Jack", "Black", "78959565", Employee.EmployeeType.Responsible));
        toEmployees.add(new Employee(0, "Elie", "Nassar", "70112547", Employee.EmployeeType.Responsible));
        toEmployees.add(new Employee(0, "John", "Blue", "81235689", Employee.EmployeeType.Director));

        initialize();
        return rootView;
    }

    public void initialize() {
        send = (ImageButton) rootView.findViewById(R.id.btnSendTask);
        actvToEmployee = (AutoCompleteTextView) rootView.findViewById(R.id.actvToEmployee);

        AutoCompleteTVItemAdapter actvAdapter = new AutoCompleteTVItemAdapter(getActivity().getApplicationContext(), toEmployees);

        actvToEmployee.setAdapter(actvAdapter);
        actvToEmployee.setThreshold(1);

        if(selectedEmployee != null){
            actvToEmployee.setText(selectedEmployee.getFullName());
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Your task has been sent", Toast.LENGTH_SHORT).show();

                Fragment fragment = new TaskFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

}
