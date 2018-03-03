package com.bignerdranch.android.finalapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.fragments.SecondFragment;
import com.bignerdranch.android.finalapp.models.ProvinceTerritory;
import com.bignerdranch.android.finalapp.models.ProvincesTerritoriesArray;

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
        mProvinceTerritory = (ProvinceTerritory) getIntent().getSerializableExtra(EXTRA_PROVINCE);

        return SecondFragment.newInstance(provinceIndex, mProvinceTerritory);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        mProvincesTerritoriesArray = new ProvincesTerritoriesArray();

        //retrieve the extra from the intent (index)
        int index = (int) getIntent().getSerializableExtra(EXTRA_GET_TAXES);
        mProvinceTerritory = findProvinceTerritory(index);

        Fragment sf = SecondFragment.newInstance(index, mProvinceTerritory);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, sf);
        //ft.addToBackStack(null);
        ft.commit();

    }

    private ProvinceTerritory findProvinceTerritory(int index) {

        return mProvincesTerritoriesArray.getProvincesTerritories()[index];

    }

}
