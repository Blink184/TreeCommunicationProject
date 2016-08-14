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

import com.blink.treecommunicationproject.Objects.Employee;
import com.blink.treecommunicationproject.Objects.User;
import com.blink.treecommunicationproject.Services.Global;
import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Services.Preferences;
import com.blink.treecommunicationproject.Web.Connection;
import com.blink.treecommunicationproject.Web.DatabaseMethods;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

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
                JSONObject object = new JSONObject(output);
                if(object.getString("s").equals("1")) {
                    User user = new User();
                    user.setUsername(etUsername.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.setFirstName(object.getString("firstName"));
                    user.setLastName(object.getString("lastName"));
                    user.setPhone(object.getString("phone"));
                    user.setAddress(object.getString("address"));
                    user.setEmail(object.getString("email"));
                    user.setLastActiveDate((Date) object.get("isLoggedIn"));
                    user.setAdmin(object.getBoolean("isAdmin"));
                    user.setBanned(object.getBoolean("isBanned"));
                    user.setDeleted(object.getBoolean("isDeleted"));
//                    user.setImage((Bitmap) object.get("image"));
                    user.setLoggedIn(object.getBoolean("isLoggedIn"));
                    if (user.isBanned()) {
                        userIsBannedToast();
                    } else if (user.isDeleted()) {
                        userDoesNotExistToast();
                    } else if (user.isLoggedIn()) {
                        userIsAlreadyLoggedIn();
                    } else {
                        Global.user = user;
                        preferences.putString(Preferences.USER, user.toJson());
                        preferences.putBoolean(Preferences.ISLOGGEDIN, true);
                        goToMainPage();
                    }
                }
                else{

                    Toast.makeText(getApplicationContext(), "Wrong Phone or Password.", Toast.LENGTH_LONG).show();
                }
            }
        }).execute();
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
