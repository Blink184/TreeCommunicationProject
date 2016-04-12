package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.HomeFragmentGridItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad hammoud on 4/12/2016.
 */
public class HomeFragment extends Fragment {
    private GridView grid;
    private View rootView;
    private List<Employee> employees = new ArrayList<>();


    public HomeFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initialize();
        return rootView;
    }

    private void initialize() {
        fillFakeData();
        grid = (GridView) rootView.findViewById(R.id.grid);
        HomeFragmentGridItemAdapter adapter = new HomeFragmentGridItemAdapter(getActivity(), employees);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked on " + employees.get(position).getFullName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fillFakeData() {
        Bitmap b = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.person);
        employees.add(new Employee(1, "un", "pw", "Aynur", "Ajami", "70/122322", b, Employee.EmployeeType.President, null, null));
        employees.add(new Employee(1, "un", "pw", "Ahmad", "Hammoud", "70/122322", b, Employee.EmployeeType.Advisor, null, null));
        employees.add(new Employee(1, "un", "pw", "Azzam", "Mourad", "70/122322", b, Employee.EmployeeType.Responsible, null, null));
        employees.add(new Employee(1, "un", "pw", "Jesse", "Pinkman", "70/122322", b, Employee.EmployeeType.Member, null, null));
        employees.add(new Employee(1, "un", "pw", "Walter", "White", "70/122322", b, Employee.EmployeeType.Director, null, null));
        employees.add(new Employee(1, "un", "pw", "John", "Snow", "70/122322", b, Employee.EmployeeType.Person, null, null));
        employees.add(new Employee(1, "un", "pw", "Lara", "Croft", "70/122322", b, Employee.EmployeeType.Responsible, null, null));
        employees.add(new Employee(1, "un", "pw", "Amy", "Lee", "70/122322", b, Employee.EmployeeType.Person, null, null));
        employees.add(new Employee(1, "un", "pw", "Roy", "Fallon", "70/122322", b, Employee.EmployeeType.Advisor, null, null));
        employees.add(new Employee(1, "un", "pw", "John", "Smith", "70/122322", b, Employee.EmployeeType.Person, null, null));
        employees.add(new Employee(1, "un", "pw", "Jared", "Leto", "70/122322", b, Employee.EmployeeType.Advisor, null, null));
        employees.add(new Employee(1, "un", "pw", "Samir", "Mounir", "70/122322", b, Employee.EmployeeType.Advisor, null, null));
        employees.add(new Employee(1, "un", "pw", "Mike", "Stanley", "70/122322", b, Employee.EmployeeType.Advisor, null, null));
        employees.add(new Employee(1, "un", "pw", "Tyrion", "Lannister", "70/122322", b, Employee.EmployeeType.Advisor, null, null));
        employees.add(new Employee(1, "un", "pw", "Jaime", "Lannister", "70/122322", b, Employee.EmployeeType.Advisor, null, null));
    }
}
