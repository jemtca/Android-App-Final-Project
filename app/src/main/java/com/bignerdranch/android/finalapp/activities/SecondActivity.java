package com.bignerdranch.android.finalapp.activities;


import android.support.v4.app.Fragment;

import com.bignerdranch.android.finalapp.fragments.SecondFragment;

public class SecondActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){

        return new SecondFragment();

    }

}
