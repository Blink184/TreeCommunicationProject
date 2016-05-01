package com.blink.treecommunicationproject.Activities.Adapters.Holders;

import android.content.Context;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.blink.treecommunicationproject.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by ahmad hammoud on 5/1/2016.
 */
public class EmployeeItem extends TreeNode.BaseNodeViewHolder<EmployeeItem.EmployeeTreeItem> {
    private TextView tvName;
    private TextView tvType;
    private TextView tvArrow;

    public EmployeeItem(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, EmployeeTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.fragment_home2_tree_node, null, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvType = (TextView) view.findViewById(R.id.tvType);
        tvArrow = (TextView) view.findViewById(R.id.tvArrow);
        tvName.setText(value.name);
        tvType.setText(value.type);

        if(node.getChildren().size() == 0){
            tvArrow.setVisibility(View.INVISIBLE);
        }

        int leftPadding = 0;
        switch (node.getLevel()){
            case 1: break;
            case 2: leftPadding = 120; break;
            case 3: leftPadding = 240; break;
            case 4: leftPadding = 480; break;
        }
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
        ll.setPadding(leftPadding,5,5,5);

//        view.findViewById(R.id.btn_addFolder).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TreeNode newFolder = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "New Folder"));
//                getTreeView().addNode(node, newFolder);
//            }
//        });

//        view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getTreeView().removeNode(node);
//            }
//        });

//        //if My computer
//        if (node.getLevel() == 1) {
//            view.findViewById(R.id.btn_delete).setVisibility(View.GONE);
//        }

        return view;
    }

    @Override
    public void toggle(boolean active) {
        tvArrow.setText(active ? "↓" : "→");
    }

    public static class EmployeeTreeItem {
        public String name;
        public String type;

        public EmployeeTreeItem(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }
}
