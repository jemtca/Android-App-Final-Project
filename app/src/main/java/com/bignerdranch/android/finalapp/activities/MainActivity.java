package com.bignerdranch.android.finalapp.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        //getting the fragment manager
        FragmentManager fm = getSupportFragmentManager();
        //retrieve the fragment from the FragmentManager (ask for the container view id)
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_first_screen);

        if(fragment == null){

            fragment = new MainFragment();
            //create a new fragment transaction, include one add operation and then commit it
            fm.beginTransaction().add(R.id.fragment_container_first_screen, fragment).commit();

        }

    }

}
