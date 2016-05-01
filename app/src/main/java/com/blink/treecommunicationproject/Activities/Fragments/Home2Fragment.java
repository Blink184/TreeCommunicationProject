package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.blink.treecommunicationproject.Activities.Adapters.Holders.EmployeeItem;
import com.blink.treecommunicationproject.Activities.Adapters.HomeFragmentGridItemAdapter;
import com.blink.treecommunicationproject.Dialogs.EmployeeProfileActivity;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.joooonho.SelectableRoundedImageView;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad hammoud on 4/12/2016.
 */
public class Home2Fragment extends Fragment {
    private View rootView;
    private List<Employee> employees = new ArrayList<>();
    private AndroidTreeView tView;
    private TextView tvRootEmployeeName;
    private TextView tvRootEmployeeType;
    private ImageButton ibBroadcast;
    private SelectableRoundedImageView ivRootEmployeeImage;

    public Home2Fragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home2, container, false);
        fillFakeData();
        Global.user.setChildrenEmployees(employees);
        initialize(Global.user);
        return rootView;
    }

    private void initialize(Employee employee) {

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
        ibBroadcast = (ImageButton) rootView.findViewById(R.id.ibBroadcast);
        ibBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new SendBroadcastFragment(employees))
                        .commit();
            }
        });

        tvRootEmployeeName.setText(employee.getFullName());
        tvRootEmployeeType.setText(employee.getEmployeeType().toString());


        TreeNode root = TreeNode.root();

        TreeNode parent1 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Aynur Ajami", "Salesman"));
        TreeNode child10 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Lizzy Smith", "Programmer Chief"));

        TreeNode child101 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Amir Smith", "Programmer"));
        TreeNode child102 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Lara Stark", "Programmer"));
        child10.addChildren(child101, child102);

        TreeNode child11 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Johnny Lorence", "Designer"));
        parent1.addChildren(child10, child11);

        TreeNode parent2 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Azzam Mourad", "Auditor"));
        TreeNode child20 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Lizzy Fold", "Programmer"));
        TreeNode child21 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Johnny McDonald", "Designer"));
        TreeNode child22 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Smith Lorence", "Designer"));
        parent2.addChildren(child20, child21, child22);

        TreeNode parent3 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Ahmad Hammoud", "Developer"));
        TreeNode child30 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Lizzy Fold", "Programmer"));
        TreeNode child31 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Johnny McDonald", "Designer"));
        TreeNode child32 = new TreeNode(new EmployeeItem.EmployeeTreeItem("Smith Lorence", "Designer"));
        parent3.addChildren(child30, child31, child32);

        root.addChildren(parent1, parent2, parent3);


        ViewGroup container = (ViewGroup) rootView.findViewById(R.id.ll);


        tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultViewHolder(EmployeeItem.class);
        tView.setUse2dScroll(false);

        //tView.setDefaultNodeClickListener(nodeClickListener);
        //tView.setDefaultNodeLongClickListener(nodeLongClickListener);
        container.addView(tView.getView());
        //tView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //loadEmployee(employee);
    }

    private void loadEmployee(final Employee employee) {

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
