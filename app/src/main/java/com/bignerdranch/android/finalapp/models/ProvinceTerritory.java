package com.bignerdranch.android.finalapp.models;

import java.io.Serializable;

public class ProvinceTerritory implements Serializable {

    private static final float GOOD_AND_SERVICES_TAX = 5.0f; //same pst for all provinces/territories
    private static final int GST_PST = 1; //provinces with GST and PST
    private static final int GST = 2; //provinces with GST
    private static final int HST = 3; //provinces with HST

    private String mName; //name of the province/territory
    private float mGst; //good and services tax
    private float mPst; //provincial sales tax
    private float mHst; //harmonized sales tax
    private int mKindOfTaxes;

    public ProvinceTerritory(String name, float pst, float hst){

        if(pst != 0 && hst == 0){

            setName(name);
            setPst(pst);
            setGst();
            setKindOfTaxes(GST_PST);

        }
        else if(pst == 0 && hst == 0){

            setName(name);
            setGst();
            setKindOfTaxes(GST);

        }
        else{

            setName(name);
            setHst(hst);
            setKindOfTaxes(HST);

        }

    }

    public final String getName(){

        return this.mName;

    }

    public final void setName(String name){

        this.mName = name;

    }

    public final float getGst(){

        return this.mGst;

    }

    public final void setGst(){

        this.mGst = GOOD_AND_SERVICES_TAX;

    }

    public final float getPst(){

        return this.mPst;

    }

    public final void setPst(float pst){

        this.mPst = pst;

    }


    public final float getHst(){

        return this.mHst;

    }

    public final void setHst(float hst){

        this.mHst = hst;

    }

    public int getKindOfTaxes() {

        return mKindOfTaxes;

    }

    public void setKindOfTaxes(int kindOfTaxes) {

        mKindOfTaxes = kindOfTaxes;

    }

}
