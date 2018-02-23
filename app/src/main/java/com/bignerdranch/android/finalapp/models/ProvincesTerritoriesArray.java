package com.bignerdranch.android.finalapp.models;

import com.bignerdranch.android.finalapp.models.ProvinceTerritory;

public class ProvincesTerritoriesArray {

    private ProvinceTerritory[] mProvincesTerritories;

    public ProvincesTerritoriesArray(){

        mProvincesTerritories = new ProvinceTerritory[]{

                new ProvinceTerritory("Alberta", 0.0f,0.0f),
                new ProvinceTerritory("British Columbia", 7.0f,0.0f),
                new ProvinceTerritory("Manitoba", 8.0f,0.0f),
                new ProvinceTerritory("New Brunswick", 0.0f,15.0f),
                new ProvinceTerritory("Newfoundland and Labrador", 0.0f, 15.0f),
                new ProvinceTerritory("Nova Scotia", 0.0f, 15.0f),
                new ProvinceTerritory("Ontario", 0.0f, 13.0f),
                new ProvinceTerritory("Prince Edward Island", 0.0f, 15.0f),
                new ProvinceTerritory("Quebec", 9.975f, 0.0f),
                new ProvinceTerritory("Saskatchewan", 6.0f, 0.0f),
                new ProvinceTerritory("Northwest Territories", 0.0f, 0.0f),
                new ProvinceTerritory("Nunavut", 0.0f, 0.0f),
                new ProvinceTerritory("Yukon", 0.0f, 0.0f)

        };

    }

    public ProvinceTerritory[] getProvincesTerritories() {

        return mProvincesTerritories;

    }

}
