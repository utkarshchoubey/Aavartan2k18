package com.technocracy.app.aavartan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SignupFragment extends Fragment {

    private ProgressDialog pDialog;
    private SessionManager session;
    private EditText phoneEditText;
    private EditText collegeEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText password0EditText;
    //private EditText department;
    private ImageView capimg;
    //private EditText semester;
    private EditText captcha;
    private EditText password1EditText;
    private EditText emailEditText;
    private Button btnRegister;


    public SignupFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        btnRegister = (Button) getView().findViewById(R.id.bRegister);
        phoneEditText = (EditText) getView().findViewById(R.id.phone);
        collegeEditText = (EditText) getView().findViewById(R.id.college);
        firstNameEditText = (EditText) getView().findViewById(R.id.first_name);
        lastNameEditText = (EditText) getView().findViewById(R.id.last_name);
        password0EditText = (EditText) getView().findViewById(R.id.password0);
        password1EditText = (EditText) getView().findViewById(R.id.password1);
        emailEditText = (EditText) getView().findViewById(R.id.email);
    //    captcha = (EditText) getView().findViewById(R.id.captcha);
        capimg = (ImageView) getView().findViewById(R.id.capimg);
        Picasso.with(getContext())
                .load("https://beta.aavartan.org/captcha.show")
                .resize(200, 100)
                .centerCrop()
                .into(capimg);
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
//             final String captchastr = captcha.getText().toString().trim();

                if (!first_name.isEmpty() && !last_name.isEmpty() && !password0.isEmpty()
                        && !password1.isEmpty() && !email.isEmpty() && !college.isEmpty() && !phone.isEmpty()) {
                    if (password0.equals(password1)) {
                        if (password0.length() > 5) {
                            if (phone.length() == 10) {
                                registerUser(first_name, last_name, college, email, phone, password0, password1);
                            } else {
                                Snackbar.make(getActivity().findViewById(R.id.linear), "Enter a valid 10 digit phone number.", Snackbar.LENGTH_LONG).show();
                            }
                        } else {
                            Snackbar.make(getActivity().findViewById(R.id.linear), "Password should be atleast 6 character long.", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(getActivity().findViewById(R.id.linear), "Passwords are not same.", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(getActivity().findViewById(R.id.linear), "All fields are necessary.", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    private void registerUser(final String firstName, final String lastName, final String college,
                              final String email, final String mobile, final String password,
                              final String confirm_password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://aavartan.org:8000/app-android-register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ayush", "Register Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    String message = jsonResponse.getString("message");
                    Log.d("ayush", String.valueOf(success));
                    if (success) {
                        Snackbar.make(getActivity().findViewById(R.id.linear), "You have been registered successfully! Login Please.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(getActivity().findViewById(R.id.linear), message, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ayush", "Registration Error: " + error.getMessage());
                Snackbar.make(getActivity().findViewById(R.id.linear), getResources().getString(R.string.connection_error_try_again), Snackbar.LENGTH_LONG)
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
                params.put("college_name", college);
                params.put("email", email);
                params.put("mobile", mobile);
                params.put("password", password);
                params.put("confirm_password", confirm_password);
                //params.put("captcha",captcha);
                return params;
            }
        };
        // Adding request to request queue
        int socketTimeout = 300000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
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

