package com.bignerdranch.android.finalapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.finalapp.R;

public class SecondFragment extends Fragment {

    //method to configure the fragment instance
    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);


    }

    //method to create and configure the fragment's view
    //method to inflate fragment's view and return the inflate view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedOnInstance){

        View v = inflater.inflate(R.layout.fragment_second, container, false);

        return v;

    }

}
