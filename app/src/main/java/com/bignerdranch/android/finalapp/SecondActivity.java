package com.bignerdranch.android.finalapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String EXTRA_GET_TAXES = "com.bignerdranch.android.midtermapp.get_taxes";

    private ProvincesTerritoriesArray mProvincesTerritoriesArray;
    private ProvinceTerritory mProvinceTerritory;

    private TextView mNameTextView;
    private EditText mEditText;
    private TextView mPstTextView;
    private TextView mGstTextView;
    private TextView mHstTextView;
    private TextView mLiveGstTextView;
    private TextView mLivePstTextView;
    private TextView mLiveHstTextView;
    private TextView mLiveTotalTextView;

    //only this activity needs to know the implementation details of what expects as extras on its intent
    public static Intent newIntent(Context packageContext, int index) {

        Intent intent = new Intent(packageContext, SecondActivity.class);
        intent.putExtra(EXTRA_GET_TAXES, index);

        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mProvincesTerritoriesArray = new ProvincesTerritoriesArray();

        //retrieve the extra from the intent
        int index = (int) getIntent().getSerializableExtra(EXTRA_GET_TAXES);
        mProvinceTerritory = findProvinceTerritory(index);

        //getting references and setting text
        mNameTextView = (TextView) findViewById(R.id.province_territory);
        mNameTextView.setText(mProvinceTerritory.getName());

        mPstTextView = (TextView) findViewById(R.id.pst);
        mPstTextView.setText(String.valueOf(mProvinceTerritory.getPst()));
        //mPstTextView.setText("" + mProvinceTerritory.getPst()); //another option

        mGstTextView = (TextView) findViewById(R.id.gts);
        mGstTextView.setText(String.valueOf(mProvinceTerritory.getGst()));

        mHstTextView = (TextView) findViewById(R.id.hts);
        mHstTextView.setText(String.valueOf(mProvinceTerritory.getHst()));

        mLiveGstTextView = (TextView) findViewById(R.id.live_gst);
        mLivePstTextView = (TextView) findViewById(R.id.live_pst);
        mLiveHstTextView = (TextView) findViewById(R.id.live_hst);
        mLiveTotalTextView = (TextView) findViewById(R.id.total);

        mEditText = (EditText) findViewById(R.id.amount);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //no need to implement anything

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!s.toString().isEmpty()) {

                    float amount = Float.parseFloat(s.toString());

                    if(mProvinceTerritory.getKindOfTaxes() == 1) { //showing gst and pst

                        float gst = getGTS(amount);
                        mLiveGstTextView.setText(String.valueOf(gst));

                        float pst = getPST(amount);
                        mLivePstTextView.setText(String.valueOf(pst));

                        mLiveHstTextView.setText("0.0");

                    }
                    else if(mProvinceTerritory.getKindOfTaxes() == 2){ //only showing gst

                        mLivePstTextView.setText("0.0");

                        float gst = getGTS(amount);
                        mLiveGstTextView.setText(String.valueOf(gst));

                        mLiveHstTextView.setText("0.0");

                    }

                    else if(mProvinceTerritory.getKindOfTaxes() == 3){ //only showing hst

                        mLivePstTextView.setText("0.0");
                        mLiveGstTextView.setText("0.0");

                        float hst = getHST(amount);
                        mLiveHstTextView.setText(String.valueOf(hst));

                    }

                    float taxes = getTotal(amount);
                    mLiveTotalTextView.setText(String.valueOf(taxes));

                } else {

                    mLivePstTextView.setText("");
                    mLiveGstTextView.setText("");
                    mLiveHstTextView.setText("");
                    mLiveTotalTextView.setText("");

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                //no need to implement anything

            }
        });


    }

    private ProvinceTerritory findProvinceTerritory(int index) {

        return mProvincesTerritoriesArray.getProvincesTerritories()[index];

    }

    private float getPST(float amount){

        float pst = (amount * mProvinceTerritory.getPst()) / 100;

        return Float.parseFloat(String.format("%.2f", pst));

    }

    private float getGTS(float amount){

        float gst = (amount * mProvinceTerritory.getGst()) / 100;

        return Float.parseFloat(String.format("%.2f", gst));

    }

    private float getHST(float amount){

        float hst = (amount * mProvinceTerritory.getHst()) / 100;

        return Float.parseFloat(String.format("%.2f", hst));

    }

    private float getTotal(float amount) {

        float total = -1;

        if(mProvinceTerritory.getKindOfTaxes() == 1){ //provinces/territories with gst and pst

            total = amount + getGTS(amount) + getPST(amount);

        }
        else if(mProvinceTerritory.getKindOfTaxes() == 2){ //provinces/territories with only gst

            total = amount + getGTS(amount);

        }

        else if(mProvinceTerritory.getKindOfTaxes() == 3){ //provinces/territories with hst

            total = amount + getHST(amount);

        }

        return Float.parseFloat(String.format("%.2f", total));

    }

}
