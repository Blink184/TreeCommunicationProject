package com.blink.treecommunicationproject.Activities.Adapters.Holders;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.blink.treecommunicationproject.Activities.Fragments.AssignNewTaskFragment;
import com.blink.treecommunicationproject.Activities.Fragments.SendMessageFragment;
import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.UserRole;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.ImageLoader;
import com.joooonho.SelectableRoundedImageView;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by ahmad hammoud on 5/1/2016.
 */
public class EmployeeItem extends TreeNode.BaseNodeViewHolder<EmployeeItem.EmployeeTreeItem> {
    private TextView tvName;
    private TextView tvType;
    private TextView tvArrow;
    private Button ibSendMessage;
    private Button ibAssignTask;
    private ImageLoader imageLoader;
    private SelectableRoundedImageView ivRootEmployeeImage;

    public EmployeeItem(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, final EmployeeTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.fragment_home2_tree_node, null, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageLoader = new ImageLoader(view.getContext());

        tvName = (TextView) view.findViewById(R.id.tvName);
        tvType = (TextView) view.findViewById(R.id.tvType);
        tvArrow = (TextView) view.findViewById(R.id.tvArrow);
        ibSendMessage = (Button) view.findViewById(R.id.ibSendMessage);
        ibAssignTask = (Button) view.findViewById(R.id.ibAssignTask);
        ivRootEmployeeImage = (SelectableRoundedImageView) view.findViewById(R.id.ivRootEmployeeImage);

        tvName.setText(value.userRole.getUser().getFullName());
        tvType.setText(value.userRole.getRole().getDesription());

        ibSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).getFragmentManager().beginTransaction()
                        .replace(R.id.container, new SendMessageFragment(value.userRole))
                        .commit();
            }
        });
        ibAssignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).getFragmentManager().beginTransaction()
                        .replace(R.id.container, new AssignNewTaskFragment(value.userRole))
                        .commit();
            }
        });

        if(node.getChildren().size() == 0){
            tvArrow.setVisibility(View.INVISIBLE);
        }

        int leftPadding = 0;
        switch (node.getLevel()){
            case 1: break;
            case 2: leftPadding = 30; break;
            case 3: leftPadding = 60; break;
            case 4: leftPadding = 90; break;
        }
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
        ll.setPadding(leftPadding,5,5,5);

        imageLoader.DisplayImage(value.userRole.getUser().getImage(), ivRootEmployeeImage);

        return view;
    }

    @Override
    public void toggle(boolean active) {
        tvArrow.setText(active ? "↓" : "→");
    }

    public static class EmployeeTreeItem {
        public UserRole userRole;

        public EmployeeTreeItem(UserRole userRole) {
            this.userRole = userRole;
        }
    }
}
