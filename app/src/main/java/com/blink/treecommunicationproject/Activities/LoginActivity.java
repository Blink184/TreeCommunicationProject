package com.blink.treecommunicationproject.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blink.treecommunicationproject.R;
import com.blink.treecommunicationproject.Web.DatabaseMethods;

/**
 * Created by Aynur on 4/10/2016
 */
public class LoginActivity extends AppCompatActivity {

    private Button btnToRegister;
    private Button btnLogin;
    private EditText etUsername, etPassword;
  //  private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //btnToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
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
      //  preferences = new Preferences(getApplicationContext());
      //  ProgressBar.set(findViewById(R.id.circle_loading_view));
    }

    private void login() {
        DatabaseMethods.getEmployee(etUsername.getText().toString(), etPassword.getText().toString());
        goToMainPage();
//        HashMap<String, String> params = new HashMap<>();
//        params.put("phone", etPhone.getText().toString());
//        params.put("password", etPassword.getText().toString());
//        DatabaseMethods.getUser(params, new Connection.OnCallFinish() {
//            @Override
//            public void processFinish(String output) throws JSONException {
//                JSONObject object = new JSONObject(output);
//                if(object.getString("s").equals("1")) {
//                    User user = new User();
//                    user.phone = etPhone.getText().toString();
//                    user.password = etPassword.getText().toString();
//                    user.firstname = object.getString("firstname");
//                    user.lastname = object.getString("lastname");
//                    user.id = Integer.parseInt(object.getString("id"));
//                    Global.user = user;
//                    preferences.putString(Preferences.USER, user.toJson());
//                    preferences.putBoolean(Preferences.ISLOGGEDIN ,true);
//                    goToMainPage();
//                }
//                else{
//
//                    Toast.makeText(getApplicationContext(), "Wrong Phone or Password.", Toast.LENGTH_LONG).show();
//                }
//            }
//        }).execute();

    }

    private boolean isFormValid() {
        return etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0;
    }
   private void goToMainPage() {
       startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("UserStatus",1));
       finish();
    }
}
