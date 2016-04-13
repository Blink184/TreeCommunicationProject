package com.blink.treecommunicationproject.Activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.blink.treecommunicationproject.Activities.Fragments.HomeFragment;
import com.blink.treecommunicationproject.Activities.Fragments.TaskFragment;
import com.blink.treecommunicationproject.Services.Drawer.NavigationDrawerCallbacks;
import com.blink.treecommunicationproject.Services.Drawer.NavigationDrawerFragment;
import com.blink.treecommunicationproject.Services.ProgressBar;
import com.blink.treecommunicationproject.R;

public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().hide();
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        mNavigationDrawerFragment.selectItem(0);
    }

    private void navigateTo(int position) {
        try {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new TaskFragment();
                    break;
                default:
                    break;
            }
            if(fragment != null) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                if(mNavigationDrawerFragment != null)
                    mNavigationDrawerFragment.closeDrawer();
            }
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(),"navigateTo + " + position + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void onNavigationDrawerItemSelected(int position) {
        navigateTo(position);
    }
}