package com.muhyidinamin.upwork;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostingKerja extends Fragment implements View.OnClickListener {

    private EditText editTextJudul;
    private EditText editTextBudget;
    private EditText editTextDesc;

    private Button buttonPost;

    public PostingKerja() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Posting Pekerjaan");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posting_kerja, container, false);
        //Initializing views
        editTextJudul = (EditText)view.findViewById(R.id.etJudul);
        editTextBudget = (EditText)view.findViewById(R.id.etBudget);
        editTextDesc = (EditText)view.findViewById(R.id.etDeskripsi);

        buttonPost = (Button)view.findViewById(R.id.btnPost);

        //Setting listeners to button
        buttonPost.setOnClickListener(this);
        return view;
    }
    //Adding an employee
    private void addJob(){

        final String judul = editTextJudul.getText().toString().trim();
        final String budget = editTextBudget.getText().toString().trim();
        final String desc = editTextDesc.getText().toString().trim();

        class AddJob extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_JOB_JUDUL,judul);
                params.put(Config.KEY_JOB_BUDGET,budget);
                params.put(Config.KEY_JOB_DESC,desc);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_JOB, params);
                return res;
            }
        }

        AddJob ae = new AddJob();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonPost){
            addJob();
        }
    }
}
