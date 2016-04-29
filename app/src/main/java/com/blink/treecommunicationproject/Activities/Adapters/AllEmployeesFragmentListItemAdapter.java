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
import java.util.List;

/**
 * Created by Kirby on 4/12/2016.
 */
public class AllEmployeesFragmentListItemAdapter extends BaseAdapter implements Filterable{
    private Context mContext;
    private ArrayList<Employee> employees = null;
    private ArrayList<Employee> filteredEmployees = null;
    private EmployeeFilter employeeFilter;

    public AllEmployeesFragmentListItemAdapter(Context c, ArrayList<Employee> employees) {
        mContext = c;
        this.employees = employees;
        this.filteredEmployees  = employees;
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
        return employees.get(position).getId();
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

            final List<Employee> list = employees;

            int count = list.size();
            final ArrayList<Employee> nlist = new ArrayList<Employee>(count);

            Employee filterableEmployee ;

            for (int i = 0; i < count; i++) {
                filterableEmployee = list.get(i);
                if (filterableEmployee.getFullName().toLowerCase().contains(filterString)) {
                    nlist.add(filterableEmployee);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredEmployees = (ArrayList<Employee>) results.values;
            notifyDataSetChanged();
        }

    }
}


