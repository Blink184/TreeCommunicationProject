package com.blink.treecommunicationproject.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kirby on 4/12/2016.
 */
public class AllEmployeesFragmentListItemAdapter extends BaseAdapter implements Filterable{
    private LayoutInflater mInflater;
    private ArrayList<Employee> originalEmployees = null;
    private ArrayList<Employee> filteredEmployees = null;
    private EmployeeFilter employeeFilter;

    public AllEmployeesFragmentListItemAdapter(Context c, ArrayList<Employee> employees) {
        this.originalEmployees = employees;
        this.filteredEmployees  = new ArrayList<>(employees);
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return filteredEmployees.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredEmployees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_all_employees_list_item, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tvName);
            holder.type = (TextView) convertView.findViewById(R.id.tvType);
            holder.phone = (TextView) convertView.findViewById(R.id.tvPhone);

            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // If weren't re-ordering this you could rely on what you set last time
        holder.name.setText(filteredEmployees.get(position).getFullName());
        holder.type.setText(filteredEmployees.get(position).getEmployeeType().toString());
        holder.phone.setText(filteredEmployees.get(position).getFullName());

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView type;
        TextView phone;
    }

    @Override
    public Filter getFilter() {
        if (employeeFilter == null)
            employeeFilter = new EmployeeFilter();
        return employeeFilter;
    }

    private class EmployeeFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            filteredEmployees.clear();

            for(Employee emp : originalEmployees){
                if(emp.getFullName().toLowerCase().contains(filterString)){
                    filteredEmployees.add(emp);
                }
            }

            results.values = filteredEmployees;
            results.count = filteredEmployees.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    }
}


