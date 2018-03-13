package com.bignerdranch.android.finalapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.models.ProvinceTerritory;

public class SecondFragment extends Fragment {

    public static final String EXTRA_PROVINCE_INDEX = "province_index";
    public static final String EXTRA_PROVINCE = "province";
    public static final String KEY_INPUT = "input";

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

    private float amount;


    public static SecondFragment newInstance(int provinceIndex, ProvinceTerritory province) {

        //create a bundle object to save two objects (Integer and ProvinceTerritory)
        Bundle args = new Bundle();

        args.putSerializable(EXTRA_PROVINCE_INDEX, provinceIndex);
        args.putSerializable(EXTRA_PROVINCE, province);

        //create a fragment object and add the bundle
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);

        return fragment;

    }

    //method to configure the fragment instance
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //retrieve one of the object from the bundle
        mProvinceTerritory = (ProvinceTerritory) getArguments().getSerializable(EXTRA_PROVINCE);

        //condition to update amount value when the user rotates the screen
        if (savedInstanceState != null) {

            amount = savedInstanceState.getFloat(KEY_INPUT, 0);

        }

    }

    //method to create and configure the fragment's view
    //method to inflate fragment's view and return the inflate view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedOnInstance) {

        View v = inflater.inflate(R.layout.fragment_second, container, false);

        //getting references and setting text
        mNameTextView = (TextView) v.findViewById(R.id.province_territory);
        mNameTextView.setText(mProvinceTerritory.getName());

        mPstTextView = (TextView) v.findViewById(R.id.pst);
        mPstTextView.setText(String.valueOf(mProvinceTerritory.getPst()));
        //mPstTextView.setText("" + mProvinceTerritory.getPst()); //another option

        mGstTextView = (TextView) v.findViewById(R.id.gts);
        mGstTextView.setText(String.valueOf(mProvinceTerritory.getGst()));

        mHstTextView = (TextView) v.findViewById(R.id.hts);
        mHstTextView.setText(String.valueOf(mProvinceTerritory.getHst()));

        mLiveGstTextView = (TextView) v.findViewById(R.id.live_gst);
        mLivePstTextView = (TextView) v.findViewById(R.id.live_pst);
        mLiveHstTextView = (TextView) v.findViewById(R.id.live_hst);
        mLiveTotalTextView = (TextView) v.findViewById(R.id.total);

        mEditText = (EditText) v.findViewById(R.id.amount);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //no need to implement anything

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!s.toString().isEmpty()) {

                    amount = Float.parseFloat(s.toString());

                    if (mProvinceTerritory.getKindOfTaxes() == 1) { //showing gst and pst

                        float gst = getGTS(amount);
                        mLiveGstTextView.setText(String.valueOf(gst));

                        float pst = getPST(amount);
                        mLivePstTextView.setText(String.valueOf(pst));

                        mLiveHstTextView.setText("0.0");

                    } else if (mProvinceTerritory.getKindOfTaxes() == 2) { //only showing gst

                        mLivePstTextView.setText("0.0");

                        float gst = getGTS(amount);
                        mLiveGstTextView.setText(String.valueOf(gst));

                        mLiveHstTextView.setText("0.0");

                    } else if (mProvinceTerritory.getKindOfTaxes() == 3) { //only showing hst

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

        return v;

    }

    //method to save the input when the user rotates the screen
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {

        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putFloat(KEY_INPUT, amount);

    }

    private float getPST(float amount) {

        float pst = (amount * mProvinceTerritory.getPst()) / 100;

        return Float.parseFloat(String.format("%.2f", pst));

    }

    private float getGTS(float amount) {

        float gst = (amount * mProvinceTerritory.getGst()) / 100;

        return Float.parseFloat(String.format("%.2f", gst));

    }

    private float getHST(float amount) {

        float hst = (amount * mProvinceTerritory.getHst()) / 100;

        return Float.parseFloat(String.format("%.2f", hst));

    }

    private float getTotal(float amount) {

        float total = -1;

        if (mProvinceTerritory.getKindOfTaxes() == 1) { //provinces/territories with gst and pst

            total = amount + getGTS(amount) + getPST(amount);

        } else if (mProvinceTerritory.getKindOfTaxes() == 2) { //provinces/territories with only gst

            total = amount + getGTS(amount);

        } else if (mProvinceTerritory.getKindOfTaxes() == 3) { //provinces/territories with hst

            total = amount + getHST(amount);

        }

        return Float.parseFloat(String.format("%.2f", total));

    }

}
