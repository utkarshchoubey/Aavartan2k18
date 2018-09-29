package com.technocracy.app.aavartan.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private SessionManager session;
    private EditText phoneEditText;
    private EditText collegeEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText password0EditText;
    private ImageView captcha;
    private EditText password1EditText;
    private EditText emailEditText;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Registration");
        toolbar.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }
        phoneEditText = (EditText) findViewById(R.id.phone);
        collegeEditText = (EditText) findViewById(R.id.college);
        firstNameEditText = (EditText) findViewById(R.id.first_name);
        lastNameEditText = (EditText) findViewById(R.id.last_name);
        password0EditText = (EditText) findViewById(R.id.password0);
        password1EditText = (EditText) findViewById(R.id.password1);
        emailEditText = (EditText) findViewById(R.id.email);


        btnRegister = (Button) findViewById(R.id.bRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String first_name = firstNameEditText.getText().toString().trim();
                final String last_name = lastNameEditText.getText().toString().trim();
                final String password0 = password0EditText.getText().toString().trim();
                final String password1 = password1EditText.getText().toString().trim();
                final String email = emailEditText.getText().toString().trim();
                final String college = collegeEditText.getText().toString().trim();
                final String phone = phoneEditText.getText().toString().trim();

                if (!first_name.isEmpty() && !last_name.isEmpty() && !password0.isEmpty()
                        && !password1.isEmpty() && !email.isEmpty() && !college.isEmpty() && !phone.isEmpty()) {
                    if (password0.equals(password1)) {
                        if (password0.length() > 5) {
                            if (phone.length() == 10) {
                                registerUser(first_name, last_name, email, password0, password1, phone, college);
                            } else {
                                Snackbar.make(findViewById(R.id.drawer_layout), "Enter a valid 10 digit phone number.", Snackbar.LENGTH_LONG).show();
                            }
                        } else {
                            Snackbar.make(findViewById(R.id.drawer_layout), "Password should be atleast 6 character long.", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(findViewById(R.id.drawer_layout), "Passwords are not same.", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(findViewById(R.id.drawer_layout), "All fields are necessary.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void registerUser(final String firstName, final String lastName, final String email,
                              final String password0, final String password1, final String phone,
                              final String college) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                App.REGISTER_USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean error = jsonResponse.getBoolean("error");
                    if (!error) {
                        new AlertDialog.Builder(RegisterActivity.this).setIcon(R.drawable.ic_dialog_alert).setTitle("Verification")
                                .setMessage("Please verify your account within one day from the verification link sent to your email.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(toLogin);
                                        finish();
                                    }
                                }).show();

                    } else {
                        Snackbar.make(findViewById(R.id.drawer_layout), jsonResponse.getString("error_msg"), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Snackbar.make(findViewById(R.id.drawer_layout), getResources().getString(R.string.connection_error_try_again), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("first_name", firstName);
                params.put("last_name", lastName);
                params.put("email", email);
                params.put("password0", password0);
                params.put("password1", password1);
                params.put("phone", phone);
                params.put("college", college);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}