package com.blink.treecommunicationproject.Activities.Adapters;

/**
 * Created by ahmad hammoud on 4/12/2016.
 */
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

public class HomeFragmentGridItemAdapter extends BaseAdapter{
    private Context mContext;
    private final List<Employee> employees;

    public HomeFragmentGridItemAdapter(Context c, List<Employee> employees) {
        mContext = c;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.fragment_home_grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            SelectableRoundedImageView imageView = (SelectableRoundedImageView )grid.findViewById(R.id.grid_image);
            /*imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:{
                            imageView.setBorderColor(Color.RED);
                            break;
                        }
                        case MotionEvent.ACTION_UP:{
                            imageView.setBorderColor(Color.BLUE);
                            break;
                        }
                    }
                    return false;
                }
            });*/
            textView.setText(employees.get(position).getFullName());
            imageView.setImageBitmap(employees.get(position).getPicture());
        } else {
            grid = convertView;
        }

        return grid;
    }
}