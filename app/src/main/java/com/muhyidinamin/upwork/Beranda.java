package com.muhyidinamin.upwork;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Beranda extends Fragment implements View.OnClickListener {

    private Button buttonCari;
    private Button buttonPosting;


    public Beranda() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Beranda");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        buttonCari = (Button) view.findViewById(R.id.btnCari);
        buttonPosting = (Button) view.findViewById(R.id.btnPosting);
        buttonCari.setOnClickListener(this);
        buttonPosting.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if(v == buttonCari){
           fragment = new CariKerja();
           replaceFragment(fragment);
        }
        if(v == buttonPosting){
            fragment = new PostingKerja();
            replaceFragment(fragment);
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_beranda, someFragment);
        getFragmentManager().executePendingTransactions();
        ft.addToBackStack(null);
        ft.commit();
    }
}
