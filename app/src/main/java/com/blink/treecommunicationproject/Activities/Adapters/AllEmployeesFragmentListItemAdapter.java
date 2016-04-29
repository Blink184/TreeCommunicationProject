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
    private Context mContext;
    private ArrayList<Employee> employees = null;
    private ArrayList<Employee> filteredEmployees = null;
    private EmployeeFilter employeeFilter = new EmployeeFilter();

    public AllEmployeesFragmentListItemAdapter(Context c, ArrayList<Employee> employees) {
        mContext = c;
        this.employees = employees;
        this.filteredEmployees  = employees;
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
        return filteredEmployees.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            list = inflater.inflate(R.layout.fragment_all_employees_list_item, parent, false);

            TextView tvName = (TextView) list.findViewById(R.id.tvName);
            TextView tvType = (TextView) list.findViewById(R.id.tvType);
            TextView tvPhone = (TextView) list.findViewById(R.id.tvPhone);
            tvName.setText(employees.get(position).getFullName());
            tvType.setText(employees.get(position).getEmployeeType().toString());
            tvPhone.setText(employees.get(position).getPhoneNumber());
        } else {
            list = convertView;
        }

        return list;

    }

    @Override
    public Filter getFilter() {
        return employeeFilter;
    }
    private class EmployeeFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            if(filterString.length() > 0) {
                final List<Employee> list = employees;

                int count = list.size();
                final ArrayList<Employee> nlist = new ArrayList<Employee>();

                Employee filteredEmployee;

                for (int i = 0; i < count; i++) {
                    filteredEmployee = list.get(i);
                    if (filteredEmployee.getFullName().toLowerCase().contains(filterString)) {
                        nlist.add(filteredEmployee);
                    }
                }

                results.values = nlist;
                results.count = nlist.size();
            } else{
                results.values = employees;
                results.count = employees.size();
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredEmployees.clear();
            filteredEmployees.addAll((Collection<? extends Employee>) results.values);
            notifyDataSetChanged();
        }
    }
}


