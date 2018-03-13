package com.bignerdranch.android.finalapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.DetailsFragment;

import java.util.UUID;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_DETAILS_ID = "details_id";

    //only this activity needs to know the implementation details of what expects as extras on its intent
    public static Intent newIntent(Context packageContext, UUID detailsId) {

        Intent intent = new Intent(packageContext, DetailsActivity.class);
        intent.putExtra(EXTRA_DETAILS_ID, detailsId);

        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        UUID detailsId = (UUID) getIntent().getSerializableExtra(EXTRA_DETAILS_ID);

        //this condition is to avoid to create a new fragment when the user rotates the screen
        if (savedInstanceState == null) {

            Fragment sf = DetailsFragment.newInstance(detailsId);

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
