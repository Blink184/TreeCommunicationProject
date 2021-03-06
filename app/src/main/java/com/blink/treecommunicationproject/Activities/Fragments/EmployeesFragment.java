
package com.blink.treecommunicationproject.Activities.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blink.treecommunicationproject.Activities.Adapters.Holders.EmployeeItem;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.Role;
import com.blink.treecommunicationproject.Objects.User;
import com.blink.treecommunicationproject.Objects.UserRole;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Drawer.NavigationDrawerFragment;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;
import com.blink.treecommunicationproject.Web.Links;
import com.joooonho.SelectableRoundedImageView;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by aynur ajami on 4/12/2016.
 */

public class EmployeesFragment extends Fragment {
    private View rootView;
    private AndroidTreeView tView;

    public EmployeesFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home2, container, false);
        initialize();
        return rootView;
    }

    private void initialize() {

        DatabaseMethods.getTree(Global.userRole.getId(), new Connection.OnCallFinish() {
            @Override
            public void processFinish(String output) throws JSONException {
                JSONObject result = new JSONObject(output);
                if (result.getString("s").equals("1")) {
                    JSONObject object = (result.getJSONObject("i"));
                    TreeNode currentUserRoleTreeNode = getTreeNodes(object);
                    TreeNode root = TreeNode.root();
                    root.addChildren(currentUserRoleTreeNode);
                    ViewGroup container = (ViewGroup) rootView.findViewById(R.id.ll);


                    tView = new AndroidTreeView(getActivity(), root);
                    tView.setDefaultAnimation(true);
                    tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
                    tView.setDefaultViewHolder(EmployeeItem.class);
                    tView.setUse2dScroll(false);


                    container.addView(tView.getView());
                    tView.expandAll();
                }
            }

            private TreeNode getTreeNodes(JSONObject obj) throws JSONException {
                if(obj == null){
                    return new TreeNode(new EmployeeItem.EmployeeTreeItem(Global.userRole));
                }
                return _getTreeNodes(obj.getJSONArray("Children"), new TreeNode(new EmployeeItem.EmployeeTreeItem(extractUserRole(obj))));
            }
            private TreeNode _getTreeNodes(JSONArray obj, TreeNode res) throws JSONException {
                if(obj == null || obj.length() == 0){
                    return res;
                }
                for(int i = 0; i < obj.length(); i++){
                    TreeNode tn = new TreeNode(new EmployeeItem.EmployeeTreeItem(extractUserRole(obj.optJSONObject(i))));
                    _getTreeNodes(obj.optJSONObject(i).getJSONArray("Children"), tn);
                    res.addChild(tn);
                }
                return res;
            }
        }).execute();
    }

    private UserRole extractUserRole(JSONObject obj) throws JSONException {
        User u = new User();
        u.setFirstName(obj.get("FirstName").toString());
        u.setLastName(obj.get("LastName").toString());
        u.setImage(Links.PROFILEPICTURESFOLDER + obj.get("Image").toString());
        Role r = new Role();
        r.setDesription(obj.get("Role").toString());
        UserRole ur = new UserRole();
        ur.setId(obj.getInt("UserRoleId"));
        ur.setUser(u);
        ur.setRole(r);
        return ur;
    }

    private void loadEmployee(final Employee employee) {

    }
}
