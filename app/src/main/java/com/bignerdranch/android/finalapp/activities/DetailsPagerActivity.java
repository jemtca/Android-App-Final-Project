package com.bignerdranch.android.finalapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.DetailsFragment;
import com.bignerdranch.android.finalapp.models.Details;
import com.bignerdranch.android.finalapp.models.DetailsArray;

import java.util.List;
import java.util.UUID;

public class DetailsPagerActivity extends AppCompatActivity {

    private static final String EXTRA_DETAILS_ID = "details_id";

    private ViewPager mViewPager;
    private List<Details> mDetails;

    //only this activity needs to know the implementation details of what expects as extras on its intent
    public static Intent newIntent(Context packageContext, UUID detailsId) {

        Intent intent = new Intent(packageContext, DetailsPagerActivity.class);
        intent.putExtra(EXTRA_DETAILS_ID, detailsId);

        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_pager);

        UUID detailsId = (UUID) getIntent().getSerializableExtra(EXTRA_DETAILS_ID);

        //getting the reference
        mViewPager = (ViewPager) findViewById(R.id.details_crime_pager);

        //getting the data from the singleton
        mDetails = DetailsArray.get(this).getDetails();

        //getting the activity's instance of FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //setting the adapter to be a unnamed instance of FragmentStatePagerAdapter
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            //return a DetailsFragment configured to display the details for the given position
            @Override
            public Fragment getItem(int position) {

                Details details = mDetails.get(position);

                return DetailsFragment.newInstance(details.getId());

            }

            //method that returns the number of items in the array list
            @Override
            public int getCount() {

                return mDetails.size();

            }
        });

        //finding the index of the details to display
        for (int i = 0; i < mDetails.size(); i++) {

            //when Details instance whose mId matches the detailsId in the intent extra
            //set the current item to the index of that details
            if (mDetails.get(i).getId().equals(detailsId)) {

                mViewPager.setCurrentItem(i);
                break;
            }

        }

    }

}
