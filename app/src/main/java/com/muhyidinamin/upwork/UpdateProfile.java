package com.muhyidinamin.upwork;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfile extends Fragment implements View.OnClickListener {

    private EditText editTextDepan;
    private EditText editTextBelakang;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private String id;

    private Button btnUpdate;


    public UpdateProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);
        //Initializing views
        editTextDepan = (EditText) view.findViewById(R.id.etDepan);
        editTextBelakang = (EditText) view.findViewById(R.id.etBelakang);
        editTextEmail = (EditText) view.findViewById(R.id.etEmail);
        editTextUsername = (EditText) view.findViewById(R.id.etUsername);
        editTextPassword = (EditText) view.findViewById(R.id.etPassword);

        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);

        //Setting listeners to button
        btnUpdate.setOnClickListener(this);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(Config.USERNAME_SHARED_PREF, null);
        getEmployee();
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update Profile");
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_EMP, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String depan = c.getString(Config.TAG_FIRST);
            String belakang = c.getString(Config.TAG_LAST);
            String email = c.getString(Config.TAG_EMAIL);
            String username = c.getString(Config.TAG_USERNAME);
            String password = c.getString(Config.TAG_PASSWORD);

            editTextDepan.setText(depan);
            editTextBelakang.setText(belakang);
            editTextEmail.setText(email);
            editTextUsername.setText(username);
            editTextPassword.setText(password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee() {
        final String depan = editTextDepan.getText().toString().trim();
        final String belakang = editTextBelakang.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_WORKER_FIRST, depan);
                hashMap.put(Config.KEY_WORKER_LAST, belakang);
                hashMap.put(Config.KEY_WORKER_EMAIL, email);
                hashMap.put(Config.KEY_WORKER_USERNAME, username);
                hashMap.put(Config.KEY_WORKER_PASSWORD, password);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == btnUpdate) {
            updateEmployee();
        }
    }
}
