package com.bignerdranch.android.finalapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.DetailsListFragment;

public class DetailsListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){

        return new DetailsListFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        //this condition is to avoid to create a new fragment when the user rotates the screen
        if(savedInstanceState == null){

            Fragment sf = new DetailsListFragment(); // is it correct?
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, sf);
            //ft.addToBackStack(null);
            ft.commit();

        }

    }

}
