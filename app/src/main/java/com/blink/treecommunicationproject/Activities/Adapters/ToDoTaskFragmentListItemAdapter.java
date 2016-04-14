package com.blink.treecommunicationproject.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;

import java.util.ArrayList;

/**
 * Created by Kirby on 4/14/2016.
 */
public class ToDoTaskFragmentListItemAdapter extends BaseAdapter {

    private Context mContext;
    private final ArrayList<Task> tasks;

    public ToDoTaskFragmentListItemAdapter(Context c, ArrayList<Task> tasks) {
        mContext = c;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            list = new View(mContext);

            list = inflater.inflate(R.layout.fragment_to_do_task_list_item, parent, false);

            TextView from = (TextView) list.findViewById(R.id.tvFrom);
            TextView description = (TextView) list.findViewById(R.id.tvDescription);
            CheckBox checkBoxDone = (CheckBox) list.findViewById(R.id.cbDone);
            checkBoxDone.setChecked(false);
            from.setText(tasks.get(position).getFromEmployee().getFullName());
            description.setText(tasks.get(position).getDescription());
        } else {
            list = (View) convertView;
        }

        return list;

    }
}
