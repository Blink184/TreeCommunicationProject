package com.blink.treecommunicationproject.Activities.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View list;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            list = new View(mContext);

            list = inflater.inflate(R.layout.fragment_task_list_item, parent, false);

            Task task = tasks.get(position);

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

            final CheckBox cbProgress = (CheckBox) list.findViewById(R.id.cbProgress);
            cbProgress.setChecked(false);

            if (task.getFromUserRoleId() == Global.userRole.getId() && task.getToUserRoleId() != Global.userRole.getId()) {
                cbProgress.setEnabled(false);
            }

            if (task.getTaskState() == 1) {
                cbProgress.setText("Accept");
            } else if (task.getTaskState() == 2) {
                cbProgress.setText("Finish");
            } else {
                cbProgress.setText("Finished");
                cbProgress.setChecked(true);
                cbProgress.setEnabled(false);
            }

            cbProgress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProgressTaskState(tasks.get(position), cbProgress);
                }
            });

        } else {
            list = (View) convertView;
        }

        return list;
    }

    private void ProgressTaskState(Task task, CheckBox cbProgress) {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();

        cbProgress.setEnabled(false);
        if (task.getTaskState() == 1) {
            //accept task
            cbProgress.setText("Accepted");
            DatabaseMethods.acceptTask(task.getId(), date, new Connection.OnCallFinish() {
                @Override
                public void processFinish(String output) throws JSONException {
                        JSONObject result = new JSONObject(output);
                        if (result.getString("s").equals("1")) {
                            //reload tasks
                        } else {
                            System.out.println("unsuccessful query");
                        }
                    }
                }).execute();

        } else if (task.getTaskState() == 2){
            // finish task
            cbProgress.setText("Finished");
            DatabaseMethods.finishTask(task.getId(), date, new Connection.OnCallFinish() {
                @Override
                public void processFinish(String output) throws JSONException {
                    JSONObject result = new JSONObject(output);
                    if (result.getString("s").equals("1")) {
                        //reload tasks
                    } else {
                        System.out.println("unsuccessful query");
                    }
                }
            }).execute();
        }
    }
}
