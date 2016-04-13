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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirby on 4/12/2016.
 */
public class TaskFragmentListItemAdapter extends BaseAdapter{
    private Context mContext;
    private final ArrayList<Task> tasks;

    public TaskFragmentListItemAdapter(Context c, ArrayList<Task> tasks) {
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
            list = inflater.inflate(R.layout.fragment_task_list_item, null);
            /*TextView to = (TextView) list.findViewById(R.id.tvTo);
            TextView description = (TextView) list.findViewById(R.id.tvDescription);
            CheckBox checkBoxDone = (CheckBox) list.findViewById(R.id.cbDone);
            checkBoxDone.setChecked(false);
            to.setText(tasks.get(position).getToEmployee().getFullName());
            description.setText(tasks.get(position).getDescription());*/
        } else {
            list = (View) convertView;
        }

        return list;

    }
}
