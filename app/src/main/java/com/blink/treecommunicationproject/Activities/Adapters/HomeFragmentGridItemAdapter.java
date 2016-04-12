package com.blink.treecommunicationproject.Activities.Adapters;

/**
 * Created by ahmad hammoud on 4/12/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blink.treecommunicationproject.R;

public class HomeFragmentGridItemAdapter extends BaseAdapter{
    private Context mContext;
    private final String[] ppl;
    private final int[] imageId;

    public HomeFragmentGridItemAdapter(Context c,String[] web,int[] Imageid ) {
        mContext = c;
        this.imageId = Imageid;
        this.ppl = web;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ppl.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.fragment_home_grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(ppl[position]);
            imageView.setImageResource(imageId[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}