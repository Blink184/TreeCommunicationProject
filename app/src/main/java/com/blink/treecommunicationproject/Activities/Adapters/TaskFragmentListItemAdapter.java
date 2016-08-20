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

            list = inflater.inflate(R.layout.fragment_task_list_item, parent, false);

            TextView to = (TextView) list.findViewById(R.id.tvTo);
            TextView title = (TextView) list.findViewById(R.id.tvTitle);
            TextView description = (TextView) list.findViewById(R.id.tvDescription);
            TextView startDate = (TextView) list.findViewById(R.id.tvStartDate);
            TextView dueDate = (TextView) list.findViewById(R.id.tvDueDate);

            to.setText(tasks.get(position).getToUserRole());
            title.setText(tasks.get(position).getTitle());
            description.setText(tasks.get(position).getContent().toString());
            startDate.setText(tasks.get(position).getStartDate().toString());
            dueDate.setText(tasks.get(position).getDueDate().toString());

            CheckBox cbProgress = (CheckBox) list.findViewById(R.id.cbProgress);
            cbProgress.setChecked(false);
            if (tasks.get(position).getTaskState() == 1) {
                cbProgress.setText("Accept");
            } else if (tasks.get(position).getTaskState() == 2) {
                cbProgress.setText("Finish");
            } else {
                cbProgress.setText("Finished");
                cbProgress.setChecked(true);
            }

        } else {
            list = (View) convertView;
        }

        return list;

    }
}
