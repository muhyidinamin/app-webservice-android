package com.muhyidinamin.upwork;

/**
 * Created by Agus on 03/12/2017.
 */
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

public class ViewProfile extends Fragment implements View.OnClickListener{

    private TextView etNama;
    private TextView etEmail;
    private Button btUpdate;

    private String id;

    @Nullable
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Profile");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //Initializing views
        etNama = (TextView) view.findViewById(R.id.etNama);
        etEmail = (TextView)view.findViewById(R.id.etEmail);
        btUpdate = (Button)view.findViewById(R.id.btUpdate);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(Config.USERNAME_SHARED_PREF, null);
        //Setting listeners to button

        getEmployee();
        btUpdate.setOnClickListener(this);
        return view;
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Fetching...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(Config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String depan = c.getString(Config.TAG_FIRST);
            String belakang = c.getString(Config.TAG_LAST);
            String email = c.getString(Config.TAG_EMAIL);
            //String username = c.getString(Config.TAG_USERNAME);
            //String password = c.getString(Config.TAG_PASSWORD);

            etNama.setText(depan+" "+belakang);
            etEmail.setText(email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_beranda, someFragment);
        getFragmentManager().executePendingTransactions();
        ft.addToBackStack(null);
        ft.commit();
    }
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v == btUpdate) {
            fragment = new UpdateProfile();
            replaceFragment(fragment);
        }
    }
}
