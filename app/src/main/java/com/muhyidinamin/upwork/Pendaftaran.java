package com.muhyidinamin.upwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Pendaftaran extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextDepan;
    private EditText editTextBelakang;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);

        //Initializing views
        editTextDepan = (EditText) findViewById(R.id.etDepan);
        editTextBelakang = (EditText) findViewById(R.id.etBelakang);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextUsername = (EditText) findViewById(R.id.etUsername);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        buttonAdd = (Button) findViewById(R.id.btnAdd);
        buttonView = (Button) findViewById(R.id.btnView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    //Adding an employee
    private void addEmployee(){

        final String depan = editTextDepan.getText().toString().trim();
        final String belakang = editTextBelakang.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pendaftaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Pendaftaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_WORKER_FIRST,depan);
                params.put(Config.KEY_WORKER_LAST,belakang);
                params.put(Config.KEY_WORKER_EMAIL,email);
                params.put(Config.KEY_WORKER_USERNAME,username);
                params.put(Config.KEY_WORKER_PASSWORD,password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,listworker.class));
        }
    }
}
