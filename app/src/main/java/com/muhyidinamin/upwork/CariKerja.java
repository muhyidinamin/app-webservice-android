package com.muhyidinamin.upwork;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class CariKerja extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;


    public CariKerja() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Cari Pekerjaan");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cari_kerja, container, false);
        listView = (ListView) view.findViewById(R.id.lsJob);
        listView.setOnItemClickListener(this);
        getJSON();
        return view;
    }
    private void showJob(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String judul = jo.getString(Config.TAG_JUDUL);
                String budget = jo.getString(Config.TAG_BUDGET);

                HashMap<String, String> job = new HashMap<>();
                job.put(Config.TAG_BUDGET,budget);
                job.put(Config.TAG_JUDUL, judul);
                list.add(job);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list, R.layout.list_item,
                new String[]{Config.TAG_JUDUL,Config.TAG_BUDGET},
                new int[]{R.id.tvJudul1, R.id.tvBudget1});
        listView.setAdapter(adapter);
    }
    @SuppressLint("NewApi")
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Fetching Data","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showJob();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL_JOB);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity().getBaseContext(), MenuUtama.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String jobId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.JOB_ID, jobId);
        getActivity().startActivity(intent);
    }
}