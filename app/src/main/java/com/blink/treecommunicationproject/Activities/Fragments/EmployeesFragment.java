
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
import com.blink.treecommunicationproject.Objects.UserRole;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;
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
    private List<Employee> employees = new ArrayList<>();
    private AndroidTreeView tView;
    private TextView tvRootEmployeeName;
    private TextView tvRootEmployeeType;
    private ImageButton ibBroadcast;
    private SelectableRoundedImageView ivRootEmployeeImage;

    public EmployeesFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home2, container, false);
        initialize();
        return rootView;
    }

    private void initialize() {

        ivRootEmployeeImage = (SelectableRoundedImageView) rootView.findViewById(R.id.ivRootEmployeeImage);
        ivRootEmployeeImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new EmployeesOldFragment())
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

        tvRootEmployeeName.setText(Global.userRole.getUser().getFullName());
        tvRootEmployeeType.setText(Global.userRole.getRole().getDesription().toString());

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

                    //tView.setDefaultNodeClickListener(nodeClickListener);
                    //tView.setDefaultNodeLongClickListener(nodeLongClickListener);
                    container.addView(tView.getView());
                    //tView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
            }

            private TreeNode getTreeNodes(JSONObject obj) throws JSONException {
                if(obj == null){
                    return new TreeNode(new EmployeeItem.EmployeeTreeItem(1, "You", ""));
                }
                return _getTreeNodes(obj.getJSONArray("Children"), new TreeNode(new EmployeeItem.EmployeeTreeItem(obj.getInt("UserRoleId"), obj.get("Name").toString(), obj.get("Role").toString())));
            }
            private TreeNode _getTreeNodes(JSONArray obj, TreeNode res) throws JSONException {
                if(obj == null || obj.length() == 0){
                    return res;
                }
                for(int i = 0; i < obj.length(); i++){
                    TreeNode tn = new TreeNode(new EmployeeItem.EmployeeTreeItem(obj.optJSONObject(i).getInt("UserRoleId"), obj.optJSONObject(i).get("Name").toString(), obj.optJSONObject(i).get("Role").toString()));
                    _getTreeNodes(obj.optJSONObject(i).getJSONArray("Children"), tn);
                    res.addChild(tn);
                }
                return res;
            }
        }).execute();
    }

    private void loadEmployee(final Employee employee) {

    }
}
