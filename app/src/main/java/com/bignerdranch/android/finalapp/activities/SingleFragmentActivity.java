package com.bignerdranch.android.finalapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.MainFragment;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    //abstract method to instantiate the fragment

    //subclasses of SingleFragmentActivity will implement this method to return
    //an instance of the fragment that the activity is hosting
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        //getting the fragment manager
        FragmentManager fm = getSupportFragmentManager();
        //retrieve the fragment from the FragmentManager (ask for the container view id)
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){

            fragment = new MainFragment();
            //create a new fragment transaction, include one add operation and then commit it
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();

        }

    }

}
