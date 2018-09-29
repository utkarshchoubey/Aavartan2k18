package com.technocracy.app.aavartan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonArray;
import com.technocracy.app.aavartan.activity.LoginActivity;
import com.technocracy.app.aavartan.activity.UserActivity;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.ConnectivityReceiver;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ALFURQUAN on 03-09-2017.
 */
public class LoginFragment extends Fragment {

    public LoginFragment(){}
    private SessionManager session;
    private SQLiteHandler db;
    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private TextView forgotpassword;
    private ProgressDialog pDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login, container, false);// Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!ConnectivityReceiver.isConnected())
            Snackbar.make(getActivity().findViewById(R.id.login), getResources().getString(R.string.no_internet_error), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        db = new SQLiteHandler(getContext());
        session = new SessionManager(getContext());
        pDialog = new ProgressDialog(getContext());

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getContext());
            User user = sqLiteHandler.getUser();
            //Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            //startActivity(intent);
            //overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
            //finish();
        }
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        bLogin = (Button) view.findViewById(R.id.bLogin);
        forgotpassword = (TextView) view.findViewById(R.id.forgotpassword);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etUsername.getText().toString().trim();
                setForgotpassword();

            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String email = etUsername.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                loginUser(email, password);
            }
        });
    }

    private void loginUser(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "https://beta.aavartan.org/app.android.login", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("ayush", "Login Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    String message = jsonResponse.getString("message");
                    Log.d("ayush",String.valueOf(success));
                    if (success) {
                        Log.d("ayush","got response");
                        JSONObject user = jsonResponse.getJSONObject("user");
                        int user_id = user.getInt("id");
                        String last_name = user.getString("last_name");
                        String first_name = user.getString("first_name");
                        String email = user.getString("email");
                        String phone = user.getString("mobile");
                        String member_since = user.getString("created_at");
                        String college_name = user.getString("college_name");
                        JSONArray events_registered = jsonResponse.getJSONArray("events");
                        int count_event_registered=events_registered.length();

                        Log.d("ayush","Events register  "+String.valueOf(count_event_registered));
                        session = new SessionManager(getContext());
                        session.setLogin(true);
                        db.addUser(user_id, first_name, last_name, email, phone,college_name,member_since,count_event_registered);
                        registerToken(FirebaseInstanceId.getInstance().getToken(), String.valueOf(user_id));
                        Intent i= new Intent(getActivity(),UserActivity.class);
                        getActivity().startActivity(i);
                    } else
                        {
                            Snackbar.make(getActivity().findViewById(R.id.login), message, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.e(TAG, "Login Error: " + error.getMessage());
                Snackbar.make(getActivity().findViewById(R.id.login), getResources().getString(R.string.connection_error_try_again), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void setForgotpassword() {
        // Tag used to cancel the request

        AlertDialog.Builder homeScreenDialog = new AlertDialog.Builder(getContext());
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 20, 0);

        final EditText emailid = new EditText(getContext());
        emailid.setBackgroundColor(Color.parseColor("#ffffff"));
        emailid.setTextColor(Color.parseColor("#000000"));
        emailid.setHint("Email");
        emailid.setHintTextColor(Color.parseColor("#BDBDBD"));
        layout.addView(emailid, layoutParams);

        homeScreenDialog.setMessage("Enter Email Id :");
        homeScreenDialog.setTitle("Forgot Password");
        homeScreenDialog.setView(layout);
        homeScreenDialog.setCancelable(false);
        homeScreenDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String emailids = emailid.getText().toString().trim();

                Log.d("ayush",emailids);
                if (!emailids.isEmpty()) {
                    // login user
                    String tag_string_req = "req_forgotPassword";
                    StringRequest strReq = new StringRequest(Request.Method.POST,
                            "https://beta.aavartan.org/app.android.forgot", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("ayush", "Login Response: " + response.toString());
                            hideDialog();
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean error = jsonResponse.getBoolean("error");
                                if (!error) {
                                    Log.d("ayush", "no error");
                                    Snackbar.make(getActivity().findViewById(R.id.login), "Please check your e-mail for resetting password.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                } else {
                                    Log.d("ayush", "got error");
                                    Snackbar.make(getActivity().findViewById(R.id.login), jsonResponse.getString("error_msg"), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("ayush", "Login Error: here error response " + error.getMessage());
                            hideDialog();
                            Snackbar.make(getActivity().findViewById(R.id.login),"Please check your e-mail for resetting password.", Snackbar.LENGTH_LONG).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            // Posting parameters to login url
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", emailids);
                            return params;
                        }
                    };
                    // Adding request to request queue
                    AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
                } else {
                    // Prompt user to enter credentials
                    Snackbar.make(getActivity().findViewById(R.id.login), "Please enter your email address.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        homeScreenDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        homeScreenDialog.show();

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void registerToken(final String token, final String user_id) {
        // Tag used to cancel the request
        String tag_string_req = "req_fcm_token_reg";

        StringRequest strReq = new StringRequest(com.android.volley.Request.Method.POST,
                "https://beta.aavartan.org/app.android.token", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("ayush", "FCM Token Register Response: " + response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    // Check for error node in json
                    if (success) {
                        Log.d("ayush", " Successfully Registered");
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jsonObject.getString("error_msg");
                        Log.d("ayush", "Error : " + errorMsg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ayush", "JSON Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ayush", "Volley Error : " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);
                params.put("user_id", user_id);
                return params;
            }
        };
        // Adding request to request queue
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}
