package com.blink.treecommunicationproject.Activities.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.blink.treecommunicationproject.Activities.Adapters.AllEmployeesFragmentListItemAdapter;
import com.blink.treecommunicationproject.Activities.Adapters.TaskFragmentListItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AllEmployeesFragment extends Fragment {

    private ListView lvEmployees;
    private View rootView;
    private EditText etFilter;

    public AllEmployeesFragment(){}

    private ArrayList<Employee> employees = new ArrayList<Employee>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_all_employees, container, false);

        fillFakeData();
        initialize();

        return rootView;
    }

    private void initialize() {
        lvEmployees = (ListView) rootView.findViewById(R.id.lvEmployees);
        etFilter = (EditText) rootView.findViewById(R.id.etFilter);

        final AllEmployeesFragmentListItemAdapter adapter = new AllEmployeesFragmentListItemAdapter(getActivity(), employees);
        lvEmployees.setAdapter(adapter);
        lvEmployees.setTextFilterEnabled(true);

        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void fillFakeData() {
        Bitmap b = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.person);
        List<Employee> aynurChildren = new ArrayList<>();
        List<Employee> tmp = new ArrayList<>();
        Employee aynur = new Employee(1, "un", "pw", "Aynur", "Ajami", "70/122322", b, Employee.EmployeeType.President, Global.user, aynurChildren);
        aynurChildren.add(new Employee(1, "un", "pw", "Jared", "Leto", "70/122322", b, Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Samir", "Mounir", "70/122322", b, Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Mike", "Stanley", "70/122322", b, Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Tyrion", "Lannister", "70/122322", b, Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Jaime", "Lannister", "70/122322", b, Employee.EmployeeType.Advisor, aynur, tmp));
        employees.add(aynur);
        employees.add(new Employee(1, "un", "pw", "Ahmad", "Hammoud", "70/122322", b, Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Azzam", "Mourad", "70/122322", b, Employee.EmployeeType.Responsible, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Jesse", "Pinkman", "70/122322", b, Employee.EmployeeType.Member, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Walter", "White", "70/122322", b, Employee.EmployeeType.Director, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "John", "Snow", "70/122322", b, Employee.EmployeeType.Person, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Lara", "Croft", "70/122322", b, Employee.EmployeeType.Responsible, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Amy", "Lee", "70/122322", b, Employee.EmployeeType.Person, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Roy", "Fallon", "70/122322", b, Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "John", "Smith", "70/122322", b, Employee.EmployeeType.Person, Global.user, tmp));
    }

}
