package com.blink.treecommunicationproject.Activities.Fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DialogFragment;
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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.ObjectAdapters.AutoCompleteTVItemAdapter;
import com.blink.treecommunicationproject.Activities.Adapters.TaskFragmentListItemAdapter;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.Role;
import com.blink.treecommunicationproject.Objects.User;
import com.blink.treecommunicationproject.Objects.UserRole;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class AssignNewTaskFragment extends Fragment {

    private View rootView;
    private ArrayList<UserRole> toEmployees = new ArrayList<>();
    private ArrayList<UserRole> allUserRoles = new ArrayList<>();
    private ImageButton send;
    private DatePicker dpDueDate;
    private AutoCompleteTextView actvToEmployee;
    private Employee selectedEmployee;
    private boolean isDelegated;

    @SuppressLint("ValidFragment")
    public AssignNewTaskFragment(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
    public AssignNewTaskFragment(boolean isDelegated) {
        this.isDelegated = isDelegated;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_assign_new_task, container, false);
        HashMap<String, String> params = new HashMap<>();
        DatabaseMethods.getUserRoles(params, new Connection.OnCallFinish() {
            @Override
            public void processFinish(String output) throws JSONException {
                JSONObject result = new JSONObject(output);
                if (result.getString("s").equals("1")) {
                    JSONArray array = (result.getJSONArray("i"));
                    for (int p = 0; p < array.length(); p++) {
                        JSONObject row = array.getJSONObject(p);
                        parseRow(row);
                    }
                    if (isDelegated) {
                        for (int i = 0; i < allUserRoles.size(); i++) {
                            if (allUserRoles.get(i).getId() != Global.userRole.getId()) {
                                toEmployees.add(allUserRoles.get(i));
                            }
                        }
                    } else {
                        for (int i = 0; i < allUserRoles.size(); i++) {
                            toEmployees.add(allUserRoles.get(i));
                        }
                    }
                }
            }

            private void parseRow(JSONObject row) throws JSONException {
                UserRole ur = new UserRole();
                ur.setId(row.getInt("UserRoleId"));
                User u = new User();
                u.setFirstName(row.getString("FirstName"));
                u.setLastName(row.getString("LastName"));
                Role r = new Role();
                r.setDesription(row.optString("Role"));
                ur.setUser(u);
                ur.setRole(r);
                allUserRoles.add(ur);
            }
        }).execute();

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
                if (paramsAreCorrect()) {
                    Toast.makeText(getActivity(), "Your task has been sent", Toast.LENGTH_SHORT).show();
                    Fragment fragment = new TaskFragment();
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

    }

    private boolean paramsAreCorrect() {
        return true;
    }
}
