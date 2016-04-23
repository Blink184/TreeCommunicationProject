package com.blink.treecommunicationproject.Dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.R;

/**
 * Created by ahmad hammoud on 4/23/2016.
 */
public class EmployeeProfileActivity extends FragmentActivity{
    private TextView tvName;
    private TextView tvType;
    private ImageView ivPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_employee_profile);
        initializeControls();
        initializeVariables();
    }

    private void initializeControls() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvType = (TextView) findViewById(R.id.tvType);
        ivPicture = (ImageView) findViewById(R.id.ivPicture);
    }

    private void initializeVariables() {
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");
        tvName.setText(name);
        tvType.setText(type);
    }
}
