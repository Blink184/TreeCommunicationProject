package com.blink.treecommunicationproject.Activities.Adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blink.treecommunicationproject.Objects.Broadcast;
import com.blink.treecommunicationproject.Objects.Task;
import com.blink.treecommunicationproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*Created by Kirby on 8/23/2016.*/


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

            Broadcast broadcast = broadcasts.get(position);

            TextView from = (TextView) list.findViewById(R.id.tvFrom);
            TextView role = (TextView) list.findViewById(R.id.tvFromRole);
            TextView timeAgo = (TextView) list.findViewById(R.id.tvTimeAgo);
            TextView title = (TextView) list.findViewById(R.id.tvTitle);
            TextView desc = (TextView) list.findViewById(R.id.tvDescription);

            long timeInMillis = getDateInMillis(broadcast.getDateSent());
            String _timeAgo =  (DateUtils.getRelativeTimeSpanString(timeInMillis, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)).toString();

            if (broadcast.getBroadcastLine().isSender()) {
                from.setText(broadcast.getFromUserRole().getUser().getFullName());
                role.setText(broadcast.getFromUserRole().getRole().getDesription());
            } else {
                from.setText(broadcast.getBroadcastLine().getToUserRole().getUser().getFullName());
                role.setText(broadcast.getBroadcastLine().getToUserRole().getRole().getDesription());
            }
            timeAgo.setText(_timeAgo);
            title.setText(broadcast.getTitle());
            desc.setText(broadcast.getContent());

        } else {
            list = (View) convertView;
        }

        return list;
    }


    private long getDateInMillis(String srcDate) {
        SimpleDateFormat desiredFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");

        long dateInMillis = 0;
        try {
            Date date = desiredFormat.parse(srcDate);
            dateInMillis = date.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
