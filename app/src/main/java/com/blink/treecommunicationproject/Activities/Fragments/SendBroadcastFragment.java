package com.blink.treecommunicationproject.Activities.Fragments;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Adapters.ObjectAdapters.AutoCompleteTVItemAdapter;
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
import java.util.HashMap;
import java.util.List;


public class SendBroadcastFragment extends Fragment {

    private View rootView;
    private ImageButton send;
    private MultiAutoCompleteTextView mactvToEmployee;
    private ArrayList<UserRole> employees = new ArrayList<UserRole>();
    private Spinner spinner;
    private LinearLayout llCustom;
    private String toType;
    private ArrayList<UserRole> allUserRoles = new ArrayList<>();
    private ArrayList<UserRole> selectedUserRoles = new ArrayList<>();
    private EditText etTitle;
    private EditText etDescription;

    public SendBroadcastFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_broadcast_old, container, false);
        initialize();
        return rootView;

    }

    public void initialize() {

        selectedUserRoles.clear();

        send = (ImageButton) rootView.findViewById(R.id.btnSendMessage);
        mactvToEmployee = (MultiAutoCompleteTextView) rootView.findViewById(R.id.mactvToEmployee);


        llCustom = (LinearLayout) rootView.findViewById(R.id.llCustom);
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);

        spinner = (Spinner) rootView.findViewById(R.id.spnToType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.Broadcast_To_Type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selectedText = (TextView) parent.getChildAt(0);
                if (selectedText != null) {
                    selectedText.setTextColor(Color.BLACK);
                }
                toType = selectedText.getText().toString().toLowerCase();
                if(position == 2){
                    llCustom.setVisibility(View.VISIBLE);
                }else{
                    llCustom.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
                    AutoCompleteTVItemAdapter actvAdapter = new AutoCompleteTVItemAdapter(getActivity().getApplicationContext(), allUserRoles);
                    mactvToEmployee.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                    mactvToEmployee.setAdapter(actvAdapter);
                    mactvToEmployee.setThreshold(1);
                    mactvToEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UserRole ur = ((AutoCompleteTVItemAdapter)parent.getAdapter()).getSuggestions().get(position);

                            if(!selectedUserRoles.contains(ur))
                                selectedUserRoles.add(ur);
                        }
                    });
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

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFormValid()){
                    String toUserRoleIds = "";
                    if(toType.equals("custom")){
                        for(int i = 0; i < selectedUserRoles.size(); i++){
                            if(i == selectedUserRoles.size() - 1){
                                toUserRoleIds += selectedUserRoles.get(i).getId();
                            }else{
                                toUserRoleIds += selectedUserRoles.get(i).getId() + ",";
                            }
                        }
                    }
                    String title = etTitle.getText().toString();
                    String desc = etDescription.getText().toString();

                    DatabaseMethods.insertBroadcast(Global.userRole.getId(), toType, toUserRoleIds, title, desc, new Connection.OnCallFinish() {
                        @Override
                        public void processFinish(String output) throws JSONException {
                            JSONObject result = new JSONObject(output);
                            if (result.getString("s").equals("1")) {
                                Toast.makeText(getActivity(), "Your broadcast has been sent", Toast.LENGTH_SHORT).show();

                                Fragment fragment = new EmployeesFragment();
                                FragmentManager fragmentManager = getActivity().getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }else{
                                makeToast(output);
                            }
                        }
                    }).execute();
                }


//                Fragment fragment = new EmployeesFragment();
//                FragmentManager fragmentManager = getActivity().getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });
    }

    private boolean isFormValid() {
        if(toType == "custom" && selectedUserRoles.size() == 0){
            makeToast("Please select the users");
            return false;
        }
        if(etDescription.getText().length() == 0 || etTitle.getText().length() == 0){
            makeToast("Missing fields");
            return false;
        }
        return true;
    }
    private void makeToast(String s){
        Toast.makeText(rootView.getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
