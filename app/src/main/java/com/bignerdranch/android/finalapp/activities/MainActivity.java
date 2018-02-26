package com.bignerdranch.android.finalapp.activities;

import android.support.v4.app.Fragment;

import com.bignerdranch.android.finalapp.fragments.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){

        return new MainFragment();

    }

}
