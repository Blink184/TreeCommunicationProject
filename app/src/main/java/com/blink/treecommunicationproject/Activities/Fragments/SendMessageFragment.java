package com.blink.treecommunicationproject.Activities.Fragments;


import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.ObjectAdapters.AutoCompleteTVItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendMessageFragment extends Fragment {

    private View rootView;
    private ImageButton send;
    private AutoCompleteTextView actvToEmployee;
    private Employee selectedEmployee;
    private ArrayList<Employee> toEmployee = new ArrayList<Employee>();

    public SendMessageFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public SendMessageFragment(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_send_message, container, false);

        toEmployee.add(new Employee(0, "Susan", "J. Mordigan", "03555666", Employee.EmployeeType.Advisor));
        toEmployee.add(new Employee(0, "Jack", "Black", "78959565", Employee.EmployeeType.Responsible));
        toEmployee.add(new Employee(0, "Elie", "Nassar", "70112547", Employee.EmployeeType.Responsible));
        toEmployee.add(new Employee(0, "John", "Blue", "81235689", Employee.EmployeeType.Director));

        initialize();
        return rootView;

    }

    public void initialize() {
        send = (ImageButton) rootView.findViewById(R.id.btnSendMessage);
        actvToEmployee = (AutoCompleteTextView) rootView.findViewById(R.id.actvToEmployee);

        AutoCompleteTVItemAdapter actvAdapter = new AutoCompleteTVItemAdapter(getActivity().getApplicationContext(), toEmployee);
        actvToEmployee.setAdapter(actvAdapter);
        actvToEmployee.setThreshold(1);
        if(selectedEmployee != null){
            actvToEmployee.setText(selectedEmployee.getFullName());
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Your message has been sent", Toast.LENGTH_SHORT).show();
/*
                Fragment fragment = new EmployeesFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });

    }

}
