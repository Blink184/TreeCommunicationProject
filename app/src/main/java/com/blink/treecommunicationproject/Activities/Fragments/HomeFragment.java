package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.HomeFragmentGridItemAdapter;
import com.blink.treecommunicationproject.Dialogs.EmployeeProfileActivity;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.User;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad hammoud on 4/12/2016.
 */
public class HomeFragment extends Fragment {
    private GridView grid;
    private View rootView;
    private TextView tvRootEmployeeName;
    private ImageButton ibBack;
    private ImageButton ibBroadcast;
    private TextView tvRootEmployeeType;
    private List<Employee> employees = new ArrayList<>();
    private List<Employee> tmpDisplayedEmployees = new ArrayList<>();
    private Employee tmpSelectedEmployee;
    private SelectableRoundedImageView ivRootEmployeeImage;

    public HomeFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        fillFakeData();
        //Global.user.setChildrenEmployees(employees);
        initialize(Global.user);
        return rootView;
    }

    private void initialize(User user) {
        grid = (GridView) rootView.findViewById(R.id.grid);

        ivRootEmployeeImage = (SelectableRoundedImageView) rootView.findViewById(R.id.ivRootEmployeeImage);
        ivRootEmployeeImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new HomeFragment())
                        .commit();
                return false;
            }
        });

        tvRootEmployeeName = (TextView) rootView.findViewById(R.id.tvRootEmployeeName);
        tvRootEmployeeType = (TextView) rootView.findViewById(R.id.tvRootEmployeeType);
        ibBack = (ImageButton) rootView.findViewById(R.id.ibBack);
        ibBroadcast = (ImageButton) rootView.findViewById(R.id.ibBroadcast);

        loadUser(user);
    }

    private void loadUser(final User user) {
/*
        if(employee.hasParentEmployee()) {
            ibBroadcast.setVisibility(View.GONE);
            ibBack.setVisibility(View.VISIBLE);
            ibBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadEmployee(employee.getParentEmployee());
                }
            });
        }
        else {
            ibBack.setVisibility(View.GONE);
            ibBroadcast.setVisibility(View.VISIBLE);
            ibBroadcast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new SendBroadcastFragment(employees))
                            .commit();
                }
            });
        }


        tvRootEmployeeName.setText(employee.getFullName());
        tvRootEmployeeType.setText(employee.getEmployeeType().toString());

        tmpDisplayedEmployees = employee.getChildrenEmployees();
        HomeFragmentGridItemAdapter adapter = new HomeFragmentGridItemAdapter(getActivity(), employee.getChildrenEmployees());
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                parent.showContextMenuForChild(view);
            }
        });
        registerForContextMenu(grid);*/
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        tmpSelectedEmployee = tmpDisplayedEmployees.get(info.position);
        menu.setHeaderTitle(tmpSelectedEmployee.getFullName());
        //TODO : not hardcoding it
        menu.add(0, v.getId(), 0, "View Profile");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "View Employees");
        menu.add(0, v.getId(), 0, "Assign a Task");
        menu.add(0, v.getId(), 0, "Send a Message");
        menu.add(0, v.getId(), 0, "Add to Broadcast List");
    }

    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("View Profile")){
            viewProfile(tmpSelectedEmployee);
        }else if(item.getTitle().equals("View Employees")){
            //loadEmployee(tmpSelectedEmployee);
        }else if(item.getTitle().equals("Assign a Task")){
            assignTask(tmpSelectedEmployee);
        }else if(item.getTitle().equals("Send a Message")){
            sendMessage(tmpSelectedEmployee);
        }else if(item.getTitle().equals("Add to Broadcast List")){
        }else{
            return false;
        }
        return true;
    }

    private void sendMessage(Employee tmpSelectedEmployee) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new SendMessageFragment(tmpSelectedEmployee))
                .commit();
    }

    private void viewProfile(Employee tmpSelectedEmployee) {
        Intent i = new Intent(rootView.getContext(), EmployeeProfileActivity.class);
        i.putExtra("name", tmpSelectedEmployee.getFullName());
        i.putExtra("type", tmpSelectedEmployee.getEmployeeType().toString());
        startActivity(i);
    }

    private void assignTask(Employee tmpSelectedEmployee) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new AssignNewTaskFragment(tmpSelectedEmployee))
                .commit();
    }

    private void fillFakeData() {
/*

        List<Employee> aynurChildren = new ArrayList<>();
        List<Employee> tmp = new ArrayList<>();
        Employee aynur = new Employee(1, "un", "pw", "Aynur", "Ajami", "70/122322", Employee.EmployeeType.President, Global.user, aynurChildren);
        aynurChildren.add(new Employee(1, "un", "pw", "Jared", "Leto", "70/122322", Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Samir", "Mounir", "70/122322", Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Mike", "Stanley", "70/122322", Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Tyrion", "Lannister", "70/122322", Employee.EmployeeType.Advisor, aynur, tmp));
        aynurChildren.add(new Employee(1, "un", "pw", "Jaime", "Lannister", "70/122322", Employee.EmployeeType.Advisor, aynur, tmp));

        employees.add(aynur);
        employees.add(new Employee(1, "un", "pw", "Ahmad", "Hammoud", "70/122322", Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Azzam", "Mourad", "70/122322", Employee.EmployeeType.Responsible, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Jesse", "Pinkman", "70/122322", Employee.EmployeeType.Member, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Walter", "White", "70/122322", Employee.EmployeeType.Director, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "John", "Snow", "70/122322", Employee.EmployeeType.Person, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Lara", "Croft", "70/122322", Employee.EmployeeType.Responsible, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Amy", "Lee", "70/122322", Employee.EmployeeType.Person, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "Roy", "Fallon", "70/122322", Employee.EmployeeType.Advisor, Global.user, tmp));
        employees.add(new Employee(1, "un", "pw", "John", "Smith", "70/122322", Employee.EmployeeType.Person, Global.user, tmp));
*/

    }
}
