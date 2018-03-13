package com.bignerdranch.android.finalapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.DetailsListFragment;

public class DetailsListActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        //this condition is to avoid to create a new fragment when the user rotates the screen
        if (savedInstanceState == null) {

            Fragment sf = new DetailsListFragment();

            //getting the fragment manager
            FragmentManager fm = getSupportFragmentManager();
            //retrieve the fragment from the FragmentManager (ask for the container view id)
            FragmentTransaction ft = fm.beginTransaction();
            //create a new fragment transaction, include one add operation and then commit it
            ft.add(R.id.fragment_container, sf);
            //ft.addToBackStack(null);
            ft.commit();

        }

    }

}
