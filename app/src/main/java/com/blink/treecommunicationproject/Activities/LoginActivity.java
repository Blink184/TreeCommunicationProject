package com.blink.treecommunicationproject.Activities;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blink.treecommunicationproject.Objects.Role;
import com.blink.treecommunicationproject.Objects.User;
import com.blink.treecommunicationproject.Objects.UserRole;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Preferences;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Aynur on 4/10/2016
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button btnLogin;
    private EditText etUsername, etPassword;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormValid()) {
                    login();
                }
            }
        });
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        preferences = new Preferences(getApplicationContext());
      //  ProgressBar.set(findViewById(R.id.circle_loading_view));
    }

    private void login() {
            HashMap<String, String> params = new HashMap<>();
            params.put("username", etUsername.getText().toString());
            params.put("password", etPassword.getText().toString());
            DatabaseMethods.validateUser(params, new Connection.OnCallFinish() {
                @Override
                public void processFinish(String output) throws JSONException {
                    JSONObject result = new JSONObject(output);
                    if (result.getString("s").equals("1")) {
                        JSONObject object = (result.getJSONObject("i"));
                        User user = new User();
                        user.setUsername(etUsername.getText().toString());
                        user.setPassword(etPassword.getText().toString());
                        user.setFirstName(object.getString("FirstName"));
                        user.setLastName(object.getString("LastName"));
                        user.setPhone(object.getString("Phone"));
                        user.setAddress(object.getString("Address"));
                        user.setEmail(object.getString("Email"));
                        user.setLastActiveDate(stringToDate(object.getString("LastActiveDate")));
                        user.setAdmin(intToBool(object.getInt("IsAdmin")));
                        user.setBanned(intToBool(object.getInt("IsBanned")));
                        user.setDeleted(intToBool(object.getInt("IsDeleted")));
//                    user.setImage((Bitmap) object.get("image"));
                        user.setLoggedIn(intToBool(object.getInt("IsLoggedIn")));
                        Role role = new Role();
                        role.setMaster(intToBool(object.getInt("IsMaster")));
                        UserRole userRole = new UserRole();
                        userRole.setId(object.getInt("UserRoleId"));
                        userRole.setUser(user);
                        userRole.setRole(role);
                        if (user.isBanned()) {
                            userIsBannedToast();
                        } else if (user.isDeleted()) {
                            userDoesNotExistToast();
                        } else if (user.isLoggedIn()) {
                            userIsAlreadyLoggedIn();
                        } else {
                            Global.userRole = userRole;
                            preferences.putString(Preferences.USER, user.toJson());
                            preferences.putBoolean(Preferences.ISLOGGEDIN, true);
                            goToMainPage();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Username or Password.", Toast.LENGTH_LONG).show();
                    }
                }
            }).execute();
    }

    private boolean intToBool(int i) {
        return i == 1;
    }

    private Date stringToDate(String s) {
        Date date = new Date();
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void userIsBannedToast() {
        Toast.makeText(getApplicationContext(), "This user is banned.", Toast.LENGTH_LONG).show();
    }

    private void userDoesNotExistToast() {
        Toast.makeText(getApplicationContext(), "This user not exist.", Toast.LENGTH_LONG).show();
    }

    private void userIsAlreadyLoggedIn() {
        Toast.makeText(getApplicationContext(), "This user is already logged in.", Toast.LENGTH_LONG).show();
    }

    private boolean isFormValid() {
        try {
            return etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
        return false;
    }

   private void goToMainPage() {
       try {
           startActivity(new Intent(getApplicationContext(), MainActivity.class));
           finish();
       } catch (Exception ex) {
           Log.e(TAG, ex.getMessage());
       }

    }
}
