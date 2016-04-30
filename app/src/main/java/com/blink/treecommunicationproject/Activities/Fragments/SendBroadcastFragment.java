package com.blink.treecommunicationproject.Activities.Fragments;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.ObjectAdapters.AutoCompleteTVItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;

import java.util.ArrayList;
import java.util.List;


public class SendBroadcastFragment extends Fragment {

    private View rootView;
    private ImageButton send;
    private MultiAutoCompleteTextView mactvToEmployee;
    private List<Employee> selectedEmployees;
    ArrayList<Employee> employees = new ArrayList<Employee>();

    public SendBroadcastFragment() {
        selectedEmployees = null;
    }

    @SuppressLint("ValidFragment")
    public SendBroadcastFragment(List<Employee> selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_broadcast, container, false);
        initialize();
        return rootView;

    }

    public void initialize() {
        send = (ImageButton) rootView.findViewById(R.id.btnSendMessage);
        mactvToEmployee = (MultiAutoCompleteTextView) rootView.findViewById(R.id.mactvToEmployee);

        if (selectedEmployees != null) {
            for(Employee e : selectedEmployees){
                mactvToEmployee.setText(e.getFullName());
            }
        } else {
            fillFakeData();
            AutoCompleteTVItemAdapter actvAdapter = new AutoCompleteTVItemAdapter(getActivity().getApplicationContext(), employees);
            mactvToEmployee.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            mactvToEmployee.setAdapter(actvAdapter);
            mactvToEmployee.setThreshold(1);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Your broadcast has been sent", Toast.LENGTH_SHORT).show();

                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void fillFakeData() {
        Bitmap b = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.person);

        List<Employee> tmp = new ArrayList<>();
        employees.add(new Employee(1, "un", "pw", "Aynur", "Ajami", "70/122322", b, Employee.EmployeeType.President, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Jared", "Leto", "70/122322", b, Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Samir", "Mounir", "70/122322", b, Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Mike", "Stanley", "70/122322", b, Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Tyrion", "Lannister", "70/122322", b, Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Jaime", "Lannister", "70/122322", b, Employee.EmployeeType.Advisor, Global.user, tmp));
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
