/*
package com.blink.treecommunicationproject.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blink.treecommunicationproject.Objects.Broadcast;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;

import java.util.ArrayList;

*/
/**
 * Created by Kirby on 8/23/2016.
 *//*

public class BroadcastFragmentItemListAdapter extends BaseAdapter {
    private Context mContext;
    private final ArrayList<Broadcast> broadcasts;

    public BroadcastFragmentItemListAdapter(Context c, ArrayList<Broadcast> broadcasts) {
        mContext = c;
        this.broadcasts = broadcasts;
    }

    @Override
    public int getCount() {
        return broadcasts.size();
    }

    @Override
    public Object getItem(int position) {
        return broadcasts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return broadcasts.get(position).getId();
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View list;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            list = new View(mContext);

            list = inflater.inflate(R.layout.fragment_broadcast_list_item, parent, false);

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
        } else {
            list = (View) convertView;
        }

        return list;
    }


}
*/
