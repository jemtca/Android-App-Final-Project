package com.bignerdranch.android.finalapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.SecondFragment;
import com.bignerdranch.android.finalapp.models.ProvinceTerritory;
import com.bignerdranch.android.finalapp.models.ProvincesTerritoriesArray;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_PROVINCE = "province";

    private ProvincesTerritoriesArray mProvincesTerritoriesArray;
    private ProvinceTerritory mProvinceTerritory;

    //only this activity needs to know the implementation details of what expects as extras on its intent
    public static Intent newIntent(Context packageContext, int index) {

        Intent intent = new Intent(packageContext, SecondActivity.class);
        intent.putExtra(EXTRA_PROVINCE, index);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        mProvincesTerritoriesArray = new ProvincesTerritoriesArray();

        //retrieve the extra from the intent (index)
        int index = (int) getIntent().getSerializableExtra(EXTRA_PROVINCE);
        mProvinceTerritory = findProvinceTerritory(index);

        //this condition is to avoid to create a new fragment when the user rotates the screen
        if (savedInstanceState == null) {

            Fragment sf = SecondFragment.newInstance(index, mProvinceTerritory);

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

    private ProvinceTerritory findProvinceTerritory(int index) {

        return mProvincesTerritoriesArray.getProvincesTerritories()[index];

    }

}
