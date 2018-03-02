package com.bignerdranch.android.finalapp.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.SecondFragment;
import com.bignerdranch.android.finalapp.models.ProvinceTerritory;
import com.bignerdranch.android.finalapp.models.ProvincesTerritoriesArray;

import java.util.UUID;

public class SecondActivity extends SingleFragmentActivity {

    public static final String EXTRA_GET_TAXES = "get_taxes";
    public static final String EXTRA_PROVINCE = "province";

    private ProvincesTerritoriesArray mProvincesTerritoriesArray;
    private ProvinceTerritory mProvinceTerritory;

    //only this activity needs to know the implementation details of what expects as extras on its intent
    public static Intent newIntent(Context packageContext, int index) {

        Intent intent = new Intent(packageContext, SecondActivity.class);
        intent.putExtra(EXTRA_GET_TAXES, index);

        return intent;
    }

    @Override
    protected Fragment createFragment(){

        int provinceIndex = (int) getIntent().getSerializableExtra(EXTRA_GET_TAXES);
        mProvinceTerritory = (ProvinceTerritory) getIntent().getSerializableExtra(EXTRA_PROVINCE); //*

        return SecondFragment.newInstance(provinceIndex, mProvinceTerritory); //*

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_second);

        mProvincesTerritoriesArray = new ProvincesTerritoriesArray();

        //retrieve the extra from the intent
        int index = (int) getIntent().getSerializableExtra(EXTRA_GET_TAXES);
        //int index = getActivity().getIntent().getIntExtra(PROVINCE_INDEX, 0);
        mProvinceTerritory = findProvinceTerritory(index);

    }

    private ProvinceTerritory findProvinceTerritory(int index) {

        return mProvincesTerritoriesArray.getProvincesTerritories()[index];

    }

}
