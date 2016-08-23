package com.blink.treecommunicationproject.Services;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;

import java.util.Calendar;

/**
 * Created by ahmad hammoud on 8/23/2016.
 */
public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView textView;
    public SelectDateFragment(TextView tv){
        textView = tv;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm+1, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        textView.setText(year + "-" + month + "-" + day);
    }

}

